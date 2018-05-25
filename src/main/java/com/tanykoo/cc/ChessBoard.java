package com.tanykoo.cc;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @Author ThinkPad
 * Created : 2018-05-23 14:31
 * @Since
 */
public class ChessBoard extends Group {
    private static Logger logger = LoggerFactory.getLogger(ChessBoard.class);

    private ChessStyle chessStyle = ChessStyle.STYLE_DEFAULT;

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
    private int i = 0;

    public ChessBoard(){
        init();
    }




    private void init(){
        board = new Board(new Image(chessStyle.getBoard()));
        grids = new Grids(chessStyle.getPoint00(), chessStyle.getWidth(), chessStyle.getHeight());
        this.getChildren().add(board);
        this.getChildren().add(grids);
    }

    class Board extends Canvas {
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

    class Grids extends BaseControl{

        private Point point00 ;
        private double width;
        private double height;

        private Point clickedPoint;

        public Grids(Point point00,double width, double height){
            super();
            this.point00 = point00;
            this.width = width;
            this.height = height;
            paint();
        }
        public Grids(ChessStyle chessStyle){
            this(chessStyle.getPoint00(),chessStyle.getWidth(),chessStyle.getHeight());
        }

        @Override
        public void setChessStyle(ChessStyle style){
            this.point00 = style.getPoint00();
            this.width = style.getWidth();
            this.height = style.getHeight();
            repaint();
        }



        @Override
        public void paint() {
            setLocation(point00.getX() - width/2,point00.getY() - height/2);
            setWidth(width*9);
            setHeight(height*10);
        }

        @Override
        public void repaint() {
            paint();
        }

        @Override
        public void setLocation(double x, double y) {
            Grids.this.setTranslateX(x);
            Grids.this.setTranslateY(y);
        }

        public Point getPoint00(){
            return point00;
        }
        @Override
        public void clicked(MouseEvent event) {
            if(clickedPoint == null){
                clickedPoint = new Point();
            }
            clickedPoint.setX((int)(event.getX()/width));
            clickedPoint.setY((int)(event.getY()/height));
            logger.debug("clicked point x=" + event.getX()
                    + "  y=" + event.getY()
                    + "  grids point x=" + clickedPoint.getX()
                    + "   y=" + clickedPoint.getY());
        }
        private Point getAbsloutePoint(Point point){
            Point point1 = new Point();
            point1.setX(point00.getX() + ((int)point.getX()) * width);
            point1.setY(point00.getY() + ((int)point.getY()) * height);
            return point;
        }
    }

    public Point getAbsloutePoint(Point point){
        return grids.getAbsloutePoint(point);
    }



}
