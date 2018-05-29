package com.tanykoo.cc.cp;

import com.tanykoo.cc.Camp;
import com.tanykoo.cc.ChessStyle;
import com.tanykoo.cc.Point;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class B_P extends ChessPiece {

    private static Logger logger = LoggerFactory.getLogger(B_P.class);


    public B_P(ChessStyle chessStyle) {
        super(chessStyle);
        this.name = "炮";
        this.camp = Camp.BLACK;
    }
    public B_P(ChessStyle chessStyle,Camp boardCamp) {
        this(chessStyle);
        setBoardCamp(boardCamp);
    }

    protected String getImage(ChessStyle chessStyle){
        return chessStyle.getB_p();
    }

    @Override
    public void clicked(MouseEvent event) {
        logger.debug(getName() + " is clicked");

        requeParentClicked();
    }


    @Override
    public List<Point> getEnablePoints(int[][] table) {
        return Rule.getEnablePointC_P(table,point);
    }
    @Override
    public List<Point> getEatablePoints(int[][] table) {
        return Rule.getEatAblePointP(table,point);
    }
    @Override
    public String getStepDesc() {
        if(lastPoint == null || point == null){
            return "";
        }
        String desc = getName();

        desc = desc + " " + B_LOCATION.charAt((int) lastPoint.getY());

        if(point.getX() == lastPoint.getX()){
            desc = desc + " " + TRANSLATE ;
            desc = desc + " " + B_LOCATION.charAt((int)point.getY());
        }else{
            if(point.getX() > lastPoint.getX()){
                desc = desc + " " + FORWARD;
                desc = desc + " " + B_LOCATION.charAt((int) (point.getX() - lastPoint.getX()) -1);
            }else{
                desc = desc + " " + BACKOFF;
                desc = desc + " " + B_LOCATION.charAt((int) (lastPoint.getX() - point.getX()) -1);
            }

        }

        return desc;
    }
}