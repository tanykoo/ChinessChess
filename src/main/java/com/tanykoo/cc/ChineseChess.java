package com.tanykoo.cc;

import com.tanykoo.cc.listener.ServerModelListener;
import com.tanykoo.utils.Utils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChineseChess extends Application {
    private static Logger logger = LoggerFactory.getLogger(ChineseChess.class);

    @FXML ChessBoard chessBoard;
    @FXML VBox panel;
    @FXML Button startGame;
    @FXML
    TextField t_x;
    @FXML
    TextField t_y;
    @FXML Button click;


    ChessStyle style1 ;
    ChessStyle style2 ;
    public static void main(String[] args) {
        logger.debug(Utils.getClassResource("/styles/style1").toString());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws  Exception{

        Parent parent = FXMLLoader.load(ChineseChess.class.getResource("/layout/chinesschess.fxml"));

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public void chengeStyele(ActionEvent event){
        if(style1 == null){
            style1 = new ChessStyle(Utils.getClassResource("/styles/style1"));
        }
        if(style2 == null) {
            style2 = new ChessStyle(Utils.getClassResource("/styles/style2"));
        }
        if(chessBoard.getChessStyle().equals(style2)){
            chessBoard.setChessStyle(style1);
            panel.setStyle("-fx-background-image: url(' "+ style1.getBg() + "')");
        }else {
            chessBoard.setChessStyle(style2);
            panel.setStyle("-fx-background-image: url(' "+ style2.getBg() + "')");
        }
    }

    public void startGame(){
        chessBoard.initGame(ChessManual.INIT_MANUAL_FIRST);
        startGame.setDisable(true);
        chessBoard.setModelListener(new ServerModelListener());
    }

    public void click(){
        int x = Integer.parseInt(t_x.getText());
        int y = Integer.parseInt(t_y.getText());
        chessBoard.clicked(new Point(x,y));
    }
}
