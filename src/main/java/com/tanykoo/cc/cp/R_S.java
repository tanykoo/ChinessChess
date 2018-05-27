package com.tanykoo.cc.cp;

import com.tanykoo.cc.Camp;
import com.tanykoo.cc.ChessStyle;
import com.tanykoo.cc.Point;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class R_S extends ChessPiece {

    private static Logger logger = LoggerFactory.getLogger(R_S.class);


    public R_S(ChessStyle chessStyle) {
        super(chessStyle);
        this.name = "仕";
        this.camp = Camp.RED;
    }

    protected String getImage(ChessStyle chessStyle){
        return chessStyle.getR_s();
    }

    @Override
    public void clicked(MouseEvent event) {
        logger.debug(getName() + " is clicked");

        requeParentClicked();
    }


    @Override
    public List<Point> getEnablePoints(int[][] table) {
        return Rule.getEnablePointS(table,point);
    }

    @Override
    public List<Point> getEatablePoints(int[][] table) {
        return Rule.getEatAblePointS(table,point);
    }

    @Override
    public String getStepDesc() {
        if(lastPoint == null || point == null){
            return "";
        }
        String desc = getName();

        desc = desc + " " + R_LOCATION.charAt(8-(int) lastPoint.getY());


        if(point.getX() > lastPoint.getX()){
            desc = desc + " " + BACKOFF;
            desc = desc + " " + R_LOCATION.charAt(8-(int) (point.getY()));
        }else{
            desc = desc + " " + FORWARD;
            desc = desc + " " + R_LOCATION.charAt(8-(int) point.getY());
        }

        return desc;
    }
}