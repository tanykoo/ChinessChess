package com.tanykoo.cc.listener;

import com.tanykoo.cc.ChessBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author ThinkPad
 * Created : 2018-05-29 10:31
 * @Since
 */
public class ServerModelListener implements ModelListener{
    private static Logger logger = LoggerFactory.getLogger(ServerModelListener.class);

    public ServerModelListener(){

    }
    @Override
    public void clicked(ModelEvent event) {
        ChessBoard chessBoard = (ChessBoard) event.getSource();
        if(chessBoard.isMoniClick()){
            return;
        }else{
            chessBoard.setEnable(true);
            logger.debug("通知服务器");
        }
    }

    @Override
    public void placedPiece(ModelEvent event) {
        ChessBoard chessBoard = (ChessBoard) event.getSource();
        chessBoard.setEnable(!chessBoard.isEnable());
        chessBoard.setEnable(true);
    }
}
