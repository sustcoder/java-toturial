package com.sustcoder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class StringDemo {
    static  Logger log = LoggerFactory.getLogger(StringDemo.class);
    public static void main(String[] args) {
        String str1="abc";
        String str2="def";
        log.info(str1+str2);
    }

}
