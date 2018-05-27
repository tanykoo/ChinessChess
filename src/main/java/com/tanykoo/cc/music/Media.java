package com.tanykoo.cc.music;

import com.tanykoo.utils.Utils;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tany
 * @createtime 2018-05-27 下午10:22
 * @since
 */
public class Media {

    private static Logger logger = LoggerFactory.getLogger(Media.class);

    public static void click(){
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(Utils.getClassResourceStr("/music/CLICK.WAV")));
        mediaPlayer.play();
    }

    public static void eatedPiece(){
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(Utils.getClassResourceStr("/music/CAPTURE.WAV")));
        mediaPlayer.play();
    }
    public static void illegal(){
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(Utils.getClassResourceStr("/music/ILLEGAL.WAV")));
        mediaPlayer.play();
    }
    public static void move(){
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(Utils.getClassResourceStr("/music/MOVE.WAV")));
        mediaPlayer.play();
    }


}
