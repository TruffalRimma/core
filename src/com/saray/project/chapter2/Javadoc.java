package com.saray.project.chapter2;

import com.saray.project.chapter2.Task1;

/**
 * This is my com.saray.project.chapter2.Javadoc class, here i will write notes about <strong>javadoc</strong>
 * @author Me
 * @version 1.1
 * @since 1.0
 */

public class Javadoc {

    /**
     * This is my description about some random field myField
     */
    int myField;

    /**
     * Here start point of the program
     * @param args command line values
     *
     */
    public static void main(String[] args) {

    }

    /**
     * This method will return i
     * @param i some int value
     * @return i from param
     */
    int getMyField(int i) {
        return i;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    String throwE() throws Exception {
        return "hi";
    }

    /**
     * @deprecated please use newMethod() - тег заменен аналогичной аннотацией
     * @see Task1#otherMethod()
     */
    void oldMethod() {}
}
