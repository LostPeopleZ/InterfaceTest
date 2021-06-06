package com.zhicall.util;

/**
 * 艺术字展示
 *
 */
public class WordartDisplayer {
    /**
     * wordart
     */
    private static final String wordart =
            "    **         *******     ***" + "\n" +
            "   *  *        *     *      *" + "\n" +
            "  ******       *******      *" + "\n" +
            " *      *      *            *" + "\n" +
            "*        *     *           ***" + "\n" +
            "\n" +
            "*******  ******  ******  ******* " + "\n" +
            "   *     *       *          *    " + "\n" +
            "   *     *****   ******     *    " + "\n" +
            "   *     *            *     *    " + "\n" +
            "   *     ******  ******     *    ";

    /**
     * 不允许外部构造
     */
    private WordartDisplayer() {
    }

    /**
     * display
     */
    public static void display() {
        System.out.println(wordart);
    }
}
