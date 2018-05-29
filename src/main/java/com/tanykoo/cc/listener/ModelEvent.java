package com.tanykoo.cc.listener;

import com.tanykoo.cc.ChessBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventObject;

/**
 * @Author ThinkPad
 * Created : 2018-05-29 10:38
 * @Since
 */
public class ModelEvent extends EventObject {
    private static Logger logger = LoggerFactory.getLogger(ModelEvent.class);

    public ModelEvent(ChessBoard source) {
        super(source);
    }
}
