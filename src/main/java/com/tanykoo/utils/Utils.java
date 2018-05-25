package com.tanykoo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 8:22
 * @Since
 */
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static final String  SEPARATOR = "/";

    public static String getClassResourceStr(String cPath){
        return getClassResource(cPath).toString();
    }
    public static URL getClassResource(String cpath){
        return Utils.class.getResource(cpath);
    }

}
