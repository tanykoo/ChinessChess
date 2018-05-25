package com.tanykoo.cc;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 12:03
 * @Since
 */
public abstract class BaseControl extends Canvas {

    private Image image;

    {addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {clicked(event);});}

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    public abstract void paint();

    public abstract void repaint();

    public abstract void setLocation(double x, double y);

    public abstract void clicked(MouseEvent event);

    public abstract void setChessStyle(ChessStyle style);

}
