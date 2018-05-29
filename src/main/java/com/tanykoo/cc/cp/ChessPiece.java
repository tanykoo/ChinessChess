package com.tanykoo.cc.cp;

import com.tanykoo.cc.*;
import com.tanykoo.cc.music.Media;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Tany
 * @createtime 2018-05-25 下午7:16
 * @since
 */
public abstract class ChessPiece extends BaseControl {

    private static Logger logger = LoggerFactory.getLogger(ChessPiece.class);

    protected static final String B_LOCATION = "123456789";

    protected static final String R_LOCATION = "一二三四五六七八九";

    protected static final String FORWARD = "进";

    protected static final String BACKOFF = "退";

    protected static final String TRANSLATE = "平";

    /**
     * 当前落点
     */
    protected Point point;

    /**
     * 前一步的落点
     */
    protected Point lastPoint;

    /**
     * 棋子阵营
     */
    protected Camp camp;

    protected String name;

    /**
     *棋盘阵营,调整棋子位置
     */
    protected Camp boardCamp;

    public Camp getBoardCamp() {
        return boardCamp;
    }

    protected void setBoardCamp(Camp boardCamp) {
        this.boardCamp = boardCamp;
        if(!camp.equals(boardCamp)){
            setRotate(180F);
        }
    }

    public Camp getCamp(){
        return camp;
    }


    public ChessPiece(ChessStyle chessStyle){
        setImage(new Image(getImage(chessStyle)));
        paint();
    }

    @Override
    public void paint() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
        graphicsContext.drawImage(getImage(),0,0);
        graphicsContext.drawImage(getImage(),0,0);
    }

    @Override
    public void repaint() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0,0,getWidth(),getHeight());
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
        graphicsContext.drawImage(getImage(),0,0);
        setLocation(point);
    }

    @Override
    public void setLocation(double x, double y) {
        point = new Point(x,y);
        ChessBoard chessBoard = (ChessBoard) getParent();
        Point point1 = ChessBoard.getGridsPoint(boardCamp,point);
        Point point = chessBoard.getAbsloutePoint(point1);
        logger.debug(getName() + " Location x=" + point.getX() + "  y=" + point.getY());
        setTranslateX(point.getX() - getWidth()/2);
        setTranslateY(point.getY() - getHeight()/2);
    }

    /**
     * 通知棋盘 该棋子被点击
     */
    protected void requeParentClicked(){
        ChessBoard chessBoard = (ChessBoard) getParent();
        chessBoard.clickedPiece(this);
    }

    //可落子点
    public abstract List<Point> getEnablePoints(int[][] table);
    //可吃子点
    public abstract List<Point> getEatablePoints(int[][] table);

    public Point getPoint(){
        return point;
    }

    public String getName(){
        return name;
    }

    protected abstract String getImage(ChessStyle chessStyle);

    @Override
    public void setChessStyle(ChessStyle style) {
        setImage(new Image(getImage(style)));
        repaint();
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    /**
     * 落子
     * @param point
     */
    public void palcePiece(int[][] table,Point point){
        List<Point> points = getEnablePoints(table);
        boolean flag = false;
        for(Point point1 : points){
            flag = point1.equals(point);
            if(flag){
                break;
            }
        }
        if(flag){
            placePieceWithNoVility(point);
        }
    }

    private void placePieceWithNoVility(Point point){
        ChessBoard chessBoard = (ChessBoard) getParent();
        this.lastPoint = this.point;
        this.point = point;
        chessBoard.placePiece(this);
        logger.debug(getStepDesc());
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    /**
     * 获取落子步走描述
     * @return
     */
    public abstract String getStepDesc();

    public void eatPiece(ChessPiece eatedPiece,int [][] table){
        List<Point> points = getEatablePoints(table);
        for(Point point : points){
            if(point.equals(eatedPiece.point)){
                Media.eatedPiece();
                eatedPiece.eated();
                placePieceWithNoVility(eatedPiece.getPoint());
                return;
            }
        }
        Media.illegal();

    }

    /**
     * 棋子被吃
     */
    public void eated(){
        ChessBoard chessBoard = (ChessBoard) getParent();
        chessBoard.eated(this);
    }
}
