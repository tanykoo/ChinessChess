package com.tanykoo.cc.listener;

/**
 * @Author ThinkPad
 * Created : 2018-05-29 9:54
 * @Since
 */
public interface ModelListener {
    /**
     * 点击point
     */
    void clicked(ModelEvent event);

    void placedPiece(ModelEvent event);


}
