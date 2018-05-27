package com.tanykoo.cc.cp;

import com.tanykoo.cc.Camp;
import com.tanykoo.cc.ChessStyle;
import com.tanykoo.cc.Point;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class B_X extends ChessPiece {

    private static Logger logger = LoggerFactory.getLogger(B_X.class);


    public B_X(ChessStyle chessStyle) {
        super(chessStyle);
        this.name = "象";
        this.camp = Camp.BLACK;
    }

    protected String getImage(ChessStyle chessStyle){
        return chessStyle.getB_x();
    }

    @Override
    public void clicked(MouseEvent event) {
        logger.debug(getName() + " is clicked");

        requeParentClicked();
    }


    @Override
    public List<Point> getEnablePoints(int[][] table) {
        return Rule.getEnablePointX(table,point);
    }
    @Override
    public List<Point> getEatablePoints(int[][] table) {
        return Rule.getEatAblePointX(table,point);
    }

    @Override
    public String getStepDesc() {
        if(lastPoint == null || point == null){
            return "";
        }
        String desc = getName();

        desc = desc + " " + B_LOCATION.charAt((int) lastPoint.getY());


        if(point.getX() > lastPoint.getX()){
            desc = desc + " " + FORWARD;
            desc = desc + " " + B_LOCATION.charAt((int) (point.getY()));
        }else{
            desc = desc + " " + BACKOFF;
            desc = desc + " " + B_LOCATION.charAt((int) point.getY());
        }

        return desc;
    }
}