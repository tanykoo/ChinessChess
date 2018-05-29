package com.tanykoo.cc;

import com.tanykoo.cc.cp.*;
import com.tanykoo.cc.listener.ModelEvent;
import com.tanykoo.cc.listener.ModelListener;
import com.tanykoo.cc.music.Media;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * 棋盘
 * @Author ThinkPad
 * Created : 2018-05-23 14:31
 * @Since
 */
public class ChessBoard extends Group {
    private static Logger logger = LoggerFactory.getLogger(ChessBoard.class);

    private ChessStyle chessStyle;

    private boolean enable = false;

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    private ModelListener modelListener;

    private boolean gameOver = false;

    public void setModelListener(ModelListener modelListener){
        this.modelListener = modelListener;
    }

    /**
     * 设置象棋的棋盘样式
     * @param style
     */
    public void setChessStyle(ChessStyle style){
        if(style != null){
            this.chessStyle = style;
        }
        ObservableList<Node> nodes = getChildren();
        for (Node node: nodes) {
            if(node instanceof Board){
                ((Board)node).setBoard(style.getBoard());
            }else if(node instanceof BaseControl){
                ((BaseControl) node).setChessStyle(style);
            }
        }
    }

    public ChessStyle getChessStyle() {
        return chessStyle;
    }

    private Board board ;
    private Grids grids ;

    public ChessBoard(){
        this(ChessStyle.STYLE_DEFAULT);
    }

    public ChessBoard(ChessStyle chessStyle){
        this.chessStyle = chessStyle;
        init();
    }


    /**
     * 初始化棋盘
     */
    private void init(){
        board = new Board(new Image(chessStyle.getBoard()));
        grids = new Grids(chessStyle);
        this.getChildren().add(board);
        this.getChildren().add(grids);
    }

    /**
     * 初始化游戏
     */
    public void initGame(ChessManual chessManual){
        this.getChildren().removeAll(grids.chessPieces);
        grids.setChessManual(chessManual);
        this.getChildren().addAll(grids.chessPieces);
        for (ChessPiece chessPiece: grids.chessPieces) {
            chessPiece.setLocation(chessPiece.getPoint());
        }
        if(chessManual.getBoardCamp().equals(Camp.RED)) {
            this.setEnable(true);
        }
    }

    /**
     *
     * 棋板
     */
    class Board extends Canvas {
        /**
         * 棋板背景图片
         */
        private Image board;
        public Board(Image image){
            this.board = image;
            setHeight(board.getHeight());
            setWidth(board.getWidth());
            paint();
        }

       public void setBoard(Image board){
            this.board = board;
            rePaint();
       }
       public void setBoard(String pngUrl){
            setBoard(new Image(pngUrl));
       }

        public void paint(){
            getGraphicsContext2D().drawImage(board,0,0);
        }

        public void rePaint(){
            getGraphicsContext2D().clearRect(0,0,Board.this.getWidth(),Board.this.getHeight());
            Board.this.setWidth(board.getWidth());
            Board.this.setHeight(board.getHeight());
            getGraphicsContext2D().drawImage(board,0,0);
        }
    }

    /**
     * 棋盘网格
     */
    class Grids extends BaseControl{

        /**
         * 棋盘左上角位置点坐标
         */
        private Point point00 ;
        /**
         * 棋盘网格宽度
         */
        private double width;
        /**
         * 棋盘网格高度
         */
        private double height;

        /**
         * 选中后红方棋子边框
         */
        private Image rBox;

        /**
         * 选中后黑方棋子边框
         */
        private Image bBox;

        /**
         * 可落子点图片
         */
        private Image dot;

        private List<Point> rBoxPoints = new ArrayList<>();

        private List<Point> bBoxPoints = new ArrayList<>();

        private List<Point> dotPoints = new ArrayList<>();

        /**
         * 棋盘所有棋子
         */
        private List<ChessPiece> chessPieces = new ArrayList<>();

        private GraphicsContext gc = getGraphicsContext2D();

        private ChessManual chessManual;

        private Point clickedPoint;

        /**
         * 被选中的棋子
         */
        private ChessPiece selectedPiece;

        private boolean moniClick;



        private void setChessManual(ChessManual chessManual){
            gc.clearRect(0,0,Grids.this.getWidth(),Grids.this.getHeight());
            chessPieces.removeAll(chessPieces);
            rBoxPoints.removeAll(rBoxPoints);
            bBoxPoints.removeAll(bBoxPoints);
            dotPoints.removeAll(dotPoints);
            for (Point point: chessManual.getPieceMap().keySet()) {
                logger.debug(chessManual.getPieceMap().get(point) + "");
                switch (chessManual.getPieceMap().get(point)){
                    case ChessManual.B_C:
                        ChessPiece b_c = new B_C(chessStyle,chessManual.getBoardCamp());
                        b_c.setPoint(point);
                        chessPieces.add(b_c);
                        break;
                    case ChessManual.B_J:
                        ChessPiece b_j = new B_J(chessStyle,chessManual.getBoardCamp());
                        b_j.setPoint(point);
                        chessPieces.add(b_j);
                        break;
                    case ChessManual.B_M:
                        ChessPiece b_m = new B_M(chessStyle,chessManual.getBoardCamp());
                        b_m.setPoint(point);
                        chessPieces.add(b_m);
                        break;
                    case ChessManual.B_X:
                        ChessPiece b_x = new B_X(chessStyle,chessManual.getBoardCamp());
                        b_x.setPoint(point);
                        chessPieces.add(b_x);
                        break;
                    case ChessManual.B_S:
                        ChessPiece b_s = new B_S(chessStyle,chessManual.getBoardCamp());
                        b_s.setPoint(point);
                        chessPieces.add(b_s);
                        break;
                    case ChessManual.B_P:
                        ChessPiece b_p = new B_P(chessStyle,chessManual.getBoardCamp());
                        b_p.setPoint(point);
                        chessPieces.add(b_p);
                        break;
                    case ChessManual.B_Z:
                        ChessPiece b_z = new B_Z(chessStyle,chessManual.getBoardCamp());
                        b_z.setPoint(point);
                        chessPieces.add(b_z);
                        break;

                    case ChessManual.R_C:
                        ChessPiece r_c = new R_C(chessStyle,chessManual.getBoardCamp());
                        r_c.setPoint(point);
                        chessPieces.add(r_c);
                        break;
                    case ChessManual.R_J:
                        ChessPiece r_j = new R_J(chessStyle,chessManual.getBoardCamp());
                        r_j.setPoint(point);
                        chessPieces.add(r_j);
                        break;
                    case ChessManual.R_M:
                        ChessPiece r_m = new R_M(chessStyle,chessManual.getBoardCamp());
                        r_m.setPoint(point);
                        chessPieces.add(r_m);
                        break;
                    case ChessManual.R_X:
                        ChessPiece r_x = new R_X(chessStyle,chessManual.getBoardCamp());
                        r_x.setPoint(point);
                        chessPieces.add(r_x);
                        break;
                    case ChessManual.R_S:
                        ChessPiece r_s = new R_S(chessStyle,chessManual.getBoardCamp());
                        r_s.setPoint(point);
                        chessPieces.add(r_s);
                        break;
                    case ChessManual.R_P:
                        ChessPiece r_p = new R_P(chessStyle,chessManual.getBoardCamp());
                        r_p.setPoint(point);
                        chessPieces.add(r_p);
                        break;
                    case ChessManual.R_Z:
                        ChessPiece r_z = new R_Z(chessStyle,chessManual.getBoardCamp());
                        r_z.setPoint(point);
                        chessPieces.add(r_z);
                        break;

                }
            }
            for (Point point: chessManual.getBoxMap().keySet()) {
                switch (chessManual.getBoxMap().get(point)){
                    case ChessManual.BORDOR_B:
                        bBoxPoints.add(point);
                        break;
                    case ChessManual.BORDOR_R:
                        rBoxPoints.add(point);
                        break;
                    case ChessManual.DOT:
                        dotPoints.add(point);
                }
            }
            Grids.this.chessManual = chessManual;
            paint();

        }


        public Grids(ChessStyle chessStyle){
            point00 = chessStyle.getPoint00();
            width = chessStyle.getWidth();
            height = chessStyle.getHeight();
            rBox = new Image(chessStyle.getR_box());
            bBox = new Image(chessStyle.getB_box());
            dot = new Image(chessStyle.getDot());
            paint();
        }

        @Override
        public void setChessStyle(ChessStyle chessStyle){
            gc.clearRect(0,0 , Grids.this.getWidth(),Grids.this.getHeight());
            point00 = chessStyle.getPoint00();
            width = chessStyle.getWidth();
            height = chessStyle.getHeight();
            rBox = new Image(chessStyle.getR_box());
            bBox = new Image(chessStyle.getB_box());
            dot = new Image(chessStyle.getDot());
            repaint();
        }



        @Override
        public void paint() {
            setLocation(point00.getX() - width/2,point00.getY() - height/2);
            setWidth(width*9);
            setHeight(height*10);
            for(Point point : rBoxPoints){
                Point point1 = getGridsPoint(chessManual.getBoardCamp(),point);
                Point abPoint = Grids.this.getGridPoint(point1);
                gc.drawImage(rBox,abPoint.getX() - rBox.getWidth()/2, abPoint.getY() - rBox.getHeight()/2);
            }
            for(Point point : bBoxPoints){
                Point point1 = getGridsPoint(chessManual.getBoardCamp(),point);
                Point abPoint = Grids.this.getGridPoint(point1);
                gc.drawImage(bBox,abPoint.getX() - bBox.getWidth()/2, abPoint.getY() - bBox.getHeight()/2);
            }
            for (Point point: dotPoints) {
                Point point1 = getGridsPoint(chessManual.getBoardCamp(),point);
                Point abPoint = Grids.this.getGridPoint(point1);
                gc.drawImage(dot,abPoint.getX() - dot.getWidth()/2 , abPoint.getY() - dot.getHeight()/2-1);
            }
        }

        private Point getGridPoint(Point point){
            Point point1 = new Point();

            point1.setX(point.getY() * width + width/2);
            point1.setY(point.getX() * height + height/2);


            return point1;
        }


        @Override
        public void repaint() {
            gc.clearRect(0,0,Grids.this.getWidth(),Grids.this.getHeight());
            paint();
        }

        @Override
        public void setLocation(double x, double y) {
            Grids.this.setTranslateX(x);
            Grids.this.setTranslateY(y);
        }

        /**
         * 棋盘被点击
         * 事件有两个
         * 1：落子
         * 2：什么都不干
         * @param event
         */
        @Override
        public void clicked(MouseEvent event) {
            if((!enable) || gameOver){
                return;
            }
            Point clickedPoint = new Point(event.getX(),event.getY());
            if(!clickEneft(clickedPoint)){
                logger.debug("cliked is not eneft");
                Media.click();
                return;
            }
            clickedPoint.setX((int)(event.getY()/width));
            clickedPoint.setY((int)(event.getX()/height));

            logger.debug("clicked point x=" + event.getX()
                    + "  y=" + event.getY());
            clickedPoint = getGridsPoint(chessManual.getBoardCamp(),clickedPoint);
            Grids.this.clicked(clickedPoint);
        }


        private boolean clickEneft(Point clickedPoint){
            Point gridsPoint = new Point();
            gridsPoint.setX((int)(clickedPoint.getY()/width));
            gridsPoint.setY((int)(clickedPoint.getX()/height));

            logger.debug(gridsPoint.getX() + " " + gridsPoint.getY());
            Point centerPoint = getGridPoint(gridsPoint);
            logger.debug(centerPoint.getX() + " " + centerPoint.getY());
            logger.debug(clickedPoint.getX() + " " + clickedPoint.getY());

            return clickedPoint.getY() > centerPoint.getY() - height/4  && clickedPoint.getY() < centerPoint.getY() + height/4
                    && clickedPoint.getX() > centerPoint.getX() - width/4 && centerPoint.getX() < centerPoint.getX() + width/4;
        }

        private Point getAbsloutePoint(Point point){
            Point point1 = new Point();
            point1.setX(point00.getX() + ((int)point.getY()) * width);
            point1.setY(point00.getY() + ((int)point.getX()) * height);
            logger.debug("abPoint x=" + point1.getX() + "  y=" + point1.getY());
            return point1;
        }

        /**
         * 棋子被点击
         * 事件
         * 1：选子
         * 2：被吃
         * 3:点击失误
         * @param piece
         */
        private void clickedPiece(ChessPiece piece){
            clickedPiece(piece,false);
        }
        /**
         * 棋子被点击
         * 事件
         * 1：选子
         * 2：被吃
         * 3:点击失误
         * @param piece 被点击棋子
         * @param flag 是否是模拟点击 true:是模拟点击  false:实际点击
         */
        private void clickedPiece(ChessPiece piece,boolean flag){
            if((!enable && !flag)|| gameOver){
                return;
            }
            this.clickedPoint = piece.getPoint();
            this.moniClick = flag;
            if(modelListener != null){
                modelListener.clicked(new ModelEvent(ChessBoard.this));
            }
            //选中棋子
            if(chessManual.getCamp().equals(piece.getCamp())){
                Media.click();
                if(!piece.equals(selectedPiece)){
                    selectedPiece = piece;
                    chessManual.clearDot();
                    chessManual.clearBox();
                    chessManual.addBox(selectedPiece.getCamp(),selectedPiece.getPoint());
                    chessManual.addDots(selectedPiece.getEnablePoints(chessManual.getTable()));
                    rBoxPoints.removeAll(rBoxPoints);
                    bBoxPoints.removeAll(bBoxPoints);
                    dotPoints.removeAll(dotPoints);
                    if(selectedPiece.getCamp().equals(Camp.BLACK)){
                        bBoxPoints.add(selectedPiece.getPoint());
                    }else{
                        rBoxPoints.add(selectedPiece.getPoint());
                    }
                    dotPoints.addAll(selectedPiece.getEnablePoints(chessManual.getTable()));
                    repaint();
                }
            }else{//吃子
                if(selectedPiece!= null) {
                    logger.debug(selectedPiece.getName() + " is trying to eat " + piece.getName());
                    selectedPiece.eatPiece(piece, chessManual.getTable());
                }else
                    Media.illegal();
            }
        }

        /**
         * 落子后更新棋谱
         * @param chessPiece
         */
        private void placePiece(ChessPiece chessPiece){
            if(modelListener != null){
                modelListener.placedPiece(new ModelEvent(ChessBoard.this));
            }
            if(chessPiece.equals(selectedPiece)){
                chessPiece.setLocation(chessPiece.getPoint());
                /**
                 *更新棋谱
                 * 1：清空可落子点
                 * 2：增加落点边框
                 * 3：修改table
                 * 4: 修改先手方
                 */
                chessManual.clearDot();
                chessManual.addBox(selectedPiece.getCamp(),selectedPiece.getPoint());
                chessManual.place(selectedPiece.getLastPoint(),selectedPiece.getPoint());
                chessManual.setCamp(selectedPiece.getCamp().equals(Camp.BLACK) ? Camp.RED : Camp.BLACK);

                dotPoints.removeAll(dotPoints);
                if(selectedPiece.getCamp().equals(Camp.BLACK)){
                    bBoxPoints.add(selectedPiece.getPoint());
                }else{
                    rBoxPoints.add(selectedPiece.getPoint());
                }
                //落子后清空被选择棋子
                selectedPiece = null;

            }
            repaint();
        }

        /**
         * 棋子被吃 更新棋谱
         * @param eatedPiece
         */
        private void eated(ChessPiece eatedPiece){
            chessManual.removePiece(eatedPiece.getPoint());
            chessPieces.remove(eatedPiece);
        }


        private void clicked(Point point){
            clicked(point,false);
        }

        /**
         *
         *
         * @param point
         * @param flag true:模拟点击  false:实际点击
         */
        private void clicked(Point point,boolean flag){
            if(gameOver){
                return;
            }
            this.clickedPoint = point;
            this.moniClick = flag;
            if(modelListener != null){
                modelListener.clicked(new ModelEvent(ChessBoard.this));
            }
            for(ChessPiece chessPiece : chessPieces){
                if(chessPiece.getPoint().equals(point)){
                    clickedPiece(chessPiece,flag);
                    return;
                }
            }
            logger.debug("clicked  grids point x=" + point.getX()
                    + "   y=" + point.getY());
            if(selectedPiece != null && selectedPiece.getCamp().equals(chessManual.getCamp())){
                selectedPiece.palcePiece(chessManual.getTable(),point);
                Media.move();
            }else{
                Media.click();
            }
        }


    }

    public Point getAbsloutePoint(Point point){
        return grids.getAbsloutePoint(point);
    }

    public void clickedPiece(ChessPiece piece){
        grids.clickedPiece(piece);
    }

    public void placePiece(ChessPiece chessPiece){
        grids.placePiece(chessPiece);
    }

    public void eated(ChessPiece eatedPiece){
        if(grids.selectedPiece == null || grids.selectedPiece.getCamp().equals(eatedPiece.getCamp())){
            return;
        }
        this.getChildren().removeAll(eatedPiece);
        grids.eated(eatedPiece);
    }

    /**
     * 模拟点击棋盘上的某个点
     * @param point
     */
    public void clicked(Point point){
        grids.clicked(point,true);
    }

    public static Point getGridsPoint(Camp boardCamp,Point point){
        Point point1 = new Point();
        switch (boardCamp){
            case BLACK:
                point1.setX(9 - point.getX());
                point1.setY(8 - point.getY());
                break;
            case RED:
                point1.setX(point.getX());
                point1.setY(point.getY());
                break;
        }
        return point1;
    }

    public Point getClickedPoint(){
        return grids.clickedPoint;
    }
    public boolean isMoniClick(){
        return grids.moniClick;
    }

    public void gameOver(){
        gameOver(grids.chessManual.getCamp());
    }
    public void gameOver(Camp winner){
        gameOver = true;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(winner.getName() + "is winner");
        alert.show();
    }
}
