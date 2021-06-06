package com.zhicall.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 类名：AccessDemoUtil.java <br/>
 * 功能说明：入口校验Demo工具类 <br/>
 * 修改历史： <br/>
 */
public class AccessToolUtil {
    /**
     * 功能说明：此方法用于byte[]转String
     * @param data
     * @return <br/>
     *         修改历史：<br/>
     */
    public static String binaryEncode(byte[] data) {
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        StringBuilder builder = new StringBuilder();

        for (byte i : data) {
            builder.append(hexDigits[i >>> 4 & 0xf]);
            builder.append(hexDigits[i & 0xf]);
        }

        return builder.toString();
    }

    /**
     * 功能说明：此方法用于String转byte[]
     * @param data
     * @return <br/>
     *         修改历史：<br/>
     */
    public static byte[] binaryDecode(String data) throws Exception {

        if (StringUtils.isEmpty(data)) {
            return new byte[] {};
        }

        if (0 != data.length() % 2) {
            data = '0' + data;
        }

        byte[] output = new byte[data.length() / 2];
        for (int i = 0, j = 0; i < data.length() - 1; i += 2) {
            output[j++] = (byte) Integer.parseInt(data.substring(i, i + 2), 16);
        }

        return output;
    }

    // Base64工具类
    public static class Base64 {

        static private final int BASELENGTH = 255;
        static private final int LOOKUPLENGTH = 64;
        static private final int TWENTYFOURBITGROUP = 24;
        static private final int EIGHTBIT = 8;
        static private final int SIXTEENBIT = 16;
        static private final int FOURBYTE = 4;
        static private final int SIGN = -128;
        static private final char PAD = '=';
        static private final boolean fDebug = false;
        static final private byte[] base64Alphabet = new byte[BASELENGTH];
        static final private char[] lookUpBase64Alphabet = new char[LOOKUPLENGTH];

        static {

            for (int i = 0; i < BASELENGTH; i++) {
                base64Alphabet[i] = -1;
            }
            for (int i = 'Z'; i >= 'A'; i--) {
                base64Alphabet[i] = (byte) (i - 'A');
            }
            for (int i = 'z'; i >= 'a'; i--) {
                base64Alphabet[i] = (byte) (i - 'a' + 26);
            }
            for (int i = '9'; i >= '0'; i--) {
                base64Alphabet[i] = (byte) (i - '0' + 52);
            }

            base64Alphabet['+'] = 62;
            base64Alphabet['/'] = 63;

            for (int i = 0; i <= 25; i++)
                lookUpBase64Alphabet[i] = (char) ('A' + i);

            for (int i = 26, j = 0; i <= 51; i++, j++)
                lookUpBase64Alphabet[i] = (char) ('a' + j);

            for (int i = 52, j = 0; i <= 61; i++, j++)
                lookUpBase64Alphabet[i] = (char) ('0' + j);
            lookUpBase64Alphabet[62] = '+';
            lookUpBase64Alphabet[63] = '/';

        }

        protected static boolean isWhiteSpace(char octect) {
            return (octect == 0x20 || octect == 0xd || octect == 0xa
                    || octect == 0x9);
        }

        protected static boolean isPad(char octect) {
            return (octect == PAD);
        }

        protected static boolean isData(char octect) {
            return (base64Alphabet[octect] != -1);
        }

        protected static boolean isBase64(char octect) {
            return (isWhiteSpace(octect) || isPad(octect) || isData(octect));
        }

        public static String encode(byte[] binaryData) {

            if (binaryData == null)
                return null;

            int lengthDataBits = binaryData.length * EIGHTBIT;
            if (lengthDataBits == 0) {
                return "";
            }

            int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
            int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
            int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1
                    : numberTriplets;
            int numberLines = 1;
            char encodedData[] = null;

            encodedData = new char[numberQuartet * 4 + numberLines];

            byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;

            int encodedIndex = 0;
            int dataIndex = 0;
            int i = 0;
            if (fDebug) {
                System.out.println("number of triplets = " + numberTriplets);
            }

            for (int line = 0; line < numberLines - 1; line++) {
                for (int quartet = 0; quartet < 16; quartet++) {
                    b1 = binaryData[dataIndex++];
                    b2 = binaryData[dataIndex++];
                    b3 = binaryData[dataIndex++];
                    if (fDebug) {
                        System.out.println(
                                "b1= " + b1 + ", b2= " + b2 + ", b3= " + b3);
                    }

                    l = (byte) (b2 & 0x0f);
                    k = (byte) (b1 & 0x03);

                    byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2)
                            : (byte) ((b1) >> 2 ^ 0xc0);

                    byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4)
                            : (byte) ((b2) >> 4 ^ 0xf0);
                    byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6)
                            : (byte) ((b3) >> 6 ^ 0xfc);
                    if (fDebug) {
                        System.out.println("val2 = " + val2);
                        System.out.println("k4 = " + (k << 4));
                        System.out.println("vak = " + (val2 | (k << 4)));
                    }

                    encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
                    encodedData[encodedIndex++] = lookUpBase64Alphabet[val2
                            | (k << 4)];
                    encodedData[encodedIndex++] = lookUpBase64Alphabet[(l << 2)
                            | val3];
                    encodedData[encodedIndex++] = lookUpBase64Alphabet[b3
                            & 0x3f];

                    i++;
                }
                encodedData[encodedIndex++] = 0xa;
            }

            for (; i < numberTriplets; i++) {
                b1 = binaryData[dataIndex++];
                b2 = binaryData[dataIndex++];
                b3 = binaryData[dataIndex++];

                if (fDebug) {
                    System.out.println(
                            "b1= " + b1 + ", b2= " + b2 + ", b3= " + b3);
                }

                l = (byte) (b2 & 0x0f);
                k = (byte) (b1 & 0x03);

                byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2)
                        : (byte) ((b1) >> 2 ^ 0xc0);

                byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4)
                        : (byte) ((b2) >> 4 ^ 0xf0);
                byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6)
                        : (byte) ((b3) >> 6 ^ 0xfc);
                if (fDebug) {
                    System.out.println("val2 = " + val2);
                    System.out.println("k4 = " + (k << 4));
                    System.out.println("vak = " + (val2 | (k << 4)));
                }

                encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[val2
                        | (k << 4)];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[(l << 2)
                        | val3];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3f];
            }

            if (fewerThan24bits == EIGHTBIT) {
                b1 = binaryData[dataIndex];
                k = (byte) (b1 & 0x03);
                if (fDebug) {
                    System.out.println("b1=" + b1);
                    System.out.println("b1<<2 = " + (b1 >> 2));
                }
                byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2)
                        : (byte) ((b1) >> 2 ^ 0xc0);
                encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];
                encodedData[encodedIndex++] = PAD;
                encodedData[encodedIndex++] = PAD;
            } else if (fewerThan24bits == SIXTEENBIT) {
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                l = (byte) (b2 & 0x0f);
                k = (byte) (b1 & 0x03);

                byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2)
                        : (byte) ((b1) >> 2 ^ 0xc0);
                byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4)
                        : (byte) ((b2) >> 4 ^ 0xf0);

                encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[val2
                        | (k << 4)];
                encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];
                encodedData[encodedIndex++] = PAD;
            }

            encodedData[encodedIndex] = 0xa;

            return new String(encodedData);
        }

        public static byte[] decode(String encoded) {

            if (encoded == null)
                return null;

            char[] base64Data = encoded.toCharArray();
            int len = removeWhiteSpace(base64Data);

            if (len % FOURBYTE != 0) {
                return null;
            }

            int numberQuadruple = (len / FOURBYTE);

            if (numberQuadruple == 0)
                return new byte[0];

            byte decodedData[] = null;
            byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            char d1 = 0, d2 = 0, d3 = 0, d4 = 0;

            int i = 0;
            int encodedIndex = 0;
            int dataIndex = 0;
            decodedData = new byte[(numberQuadruple) * 3];

            for (; i < numberQuadruple - 1; i++) {

                if (!isData((d1 = base64Data[dataIndex++]))
                        || !isData((d2 = base64Data[dataIndex++]))
                        || !isData((d3 = base64Data[dataIndex++]))
                        || !isData((d4 = base64Data[dataIndex++])))
                    return null;

                b1 = base64Alphabet[d1];
                b2 = base64Alphabet[d2];
                b3 = base64Alphabet[d3];
                b4 = base64Alphabet[d4];

                decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4)
                        | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);
            }

            if (!isData((d1 = base64Data[dataIndex++]))
                    || !isData((d2 = base64Data[dataIndex++]))) {
                return null;
            }

            b1 = base64Alphabet[d1];
            b2 = base64Alphabet[d2];

            d3 = base64Data[dataIndex++];
            d4 = base64Data[dataIndex++];
            if (!isData((d3)) || !isData((d4))) {
                if (isPad(d3) && isPad(d4)) {
                    if ((b2 & 0xf) != 0)
                        return null;
                    byte[] tmp = new byte[i * 3 + 1];
                    System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                    tmp[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                    return tmp;
                } else if (!isPad(d3) && isPad(d4)) {
                    b3 = base64Alphabet[d3];
                    if ((b3 & 0x3) != 0)
                        return null;
                    byte[] tmp = new byte[i * 3 + 2];
                    System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                    tmp[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
                    tmp[encodedIndex] = (byte) (((b2 & 0xf) << 4)
                            | ((b3 >> 2) & 0xf));
                    return tmp;
                } else {
                    return null;

                }
            } else {
                b3 = base64Alphabet[d3];
                b4 = base64Alphabet[d4];
                decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4)
                        | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);

            }

            return decodedData;
        }

        protected static int removeWhiteSpace(char[] data) {
            if (data == null)
                return 0;

            int newSize = 0;
            int len = data.length;
            for (int i = 0; i < len; i++) {
                if (!isWhiteSpace(data[i]))
                    data[newSize++] = data[i];
            }
            return newSize;
        }
    }

    // json工具类
    public static abstract class JsonHelper {

        public static <T> T fromString(String json, Class<T> cls) {
            if (null == json) {
                return null;
            }

            return new Gson().fromJson(json, cls);
        }

        public static <T> String toJson(T obj) {
            if (null == obj) {
                return "";
            }

            return new Gson().toJson(obj);
        }

        public static <T> T fromJson(JsonObject json, Class<T> cls) {
            if (null == json) {
                return null;
            }

            return new Gson().fromJson(json, cls);
        }

        public static <T> T fromJson(Map<?, ?> json, Class<T> cls) {
            if (null == json) {
                return null;
            }

            Gson tool = new Gson();
            return tool.fromJson(tool.toJson(json), cls);
        }

        public static <T> JsonElement toGsonElement(T obj) {

            return new Gson().toJsonTree(obj);
        }

        public static JsonElement fromString(String json) {
            JsonParser parser = new JsonParser();
            return parser.parse(json);
        }

        public static <T> String sortEntityToString(T t,
                                                    Comparator<Entry<String, JsonElement>> compare,
                                                    IStreamalbe<String, JsonElement> streamable) {

            return sortToString((JsonObject) toGsonElement(t), compare,
                    streamable);
        }

        public static <K, V> String sortToString(Map<K, V> map,
                                                 Comparator<Entry<K, V>> compare, IStreamalbe<K, V> streamable) {

            Set<Entry<K, V>> set = sort(map, compare);

            StringBuilder sb = new StringBuilder();

            Iterator<Entry<K, V>> iter = set.iterator();
            while (iter.hasNext()) {
                sb.append(streamable.stream(iter.next()));
            }

            return sb.toString();
        }

        public static String sortToString(JsonObject json,
                                          Comparator<Entry<String, JsonElement>> compare,
                                          IStreamalbe<String, JsonElement> streamable) {
            Set<Entry<String, JsonElement>> set = sort(json, compare);

            StringBuilder sb = new StringBuilder();

            Iterator<Entry<String, JsonElement>> iter = set.iterator();
            while (iter.hasNext()) {
                sb.append(streamable.stream(iter.next()));
            }

            return sb.toString();
        }

        public static <T> Set<Entry<String, JsonElement>> sortEntity(T t,
                                                                     Comparator<Entry<String, JsonElement>> compare) {

            return sort((JsonObject) toGsonElement(t), compare);
        }

        public static <K, V> Set<Entry<K, V>> sort(Map<K, V> map,
                                                   Comparator<Entry<K, V>> compare) {

            Set<Entry<K, V>> set = new TreeSet<Entry<K, V>>(compare);

            set.addAll(map.entrySet());

            return set;
        }

        public static Set<Entry<String, JsonElement>> sort(JsonObject json,
                                                           Comparator<Entry<String, JsonElement>> compare) {

            if (null == compare) {
                compare = DefaultJsonComparator.INSTANCE;
            }

            Set<Entry<String, JsonElement>> set = new TreeSet<Entry<String, JsonElement>>(
                    compare);

            set.addAll(json.entrySet());

            return set;
        }

        public static interface IStreamalbe<K, V> {
            String stream(Entry<K, V> item);
        }

        private static enum DefaultJsonComparator
            implements Comparator<Entry<String, JsonElement>> {

            INSTANCE;

            @Override
            public int compare(Entry<String, JsonElement> o1,
                    Entry<String, JsonElement> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

        }
    }

    // IOUtil工具类
    public static class IOUtil {

        public static ByteArrayOutputStream readStreamAsByteStream(
                InputStream in) throws IOException {

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            if (in == null) {
                return output;
            }

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            return output;
        }

    }

    public static String readStreamAsString(InputStream in, String charset)
            throws IOException {

        if (in == null) {
            return "";
        }

        Reader reader = null;
        Writer writer = new StringWriter();
        String result;

        char[] buffer = new char[1024];
        try {
            int n = -1;
            reader = new BufferedReader(new InputStreamReader(in, charset));
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            result = writer.toString();
        } finally {
            in.close();
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

        return result;
    }

    public static byte[] readStream(InputStream in) throws IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024 * 10];
        try {

            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }

            return output.toByteArray();

        } finally {
            in.close();
            output.close();
        }
    }
}
