package com.tanykoo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * @author Tany
 * @createtime 2018-05-26 上午10:28
 * @since 1，0
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        logger.info(locale.getLanguage());

        logger.info(locale.getCountry());
        logger.info(locale.toString());

        logger.info(locale.getDisplayLanguage());
        logger.info(locale.getDisplayLanguage());

        locale = Locale.TAIWAN;
        logger.info(locale.getLanguage());
        logger.info(locale.getDisplayCountry());

        logger.info(locale.getCountry());
        logger.info(locale.toString());

        locale = Locale.FRENCH;

        logger.info(locale.toString());
        logger.info(locale.getDisplayLanguage());

        locale = Locale.US;

        logger.info(locale.getDisplayCountry());

    }

}
