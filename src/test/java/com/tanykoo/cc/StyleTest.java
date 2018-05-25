package com.tanykoo.cc;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 12:33
 * @Since
 */
public class StyleTest {
    private static Logger logger = LoggerFactory.getLogger(StyleTest.class);

    public static void main(String[] args) {
        System.out.println(System.getProperties());
    }

    @Test
    public void testStyle(){
         Pattern URL_QUICKMATCH = Pattern.compile("^\\p{Alpha}[\\p{Alnum}+.-]*:.*$");
        System.out.println("file:/E:/other/android/ChinessChess/out/production/classes/images".matches("^\\p{Alpha}[\\p{Alnum}+.-]*:.*$"));;
        Assert.assertNotNull(ChessStyle.STYLE_DEFAULT);


    }
}
