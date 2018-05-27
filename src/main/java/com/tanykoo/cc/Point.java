package com.tanykoo.cc;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 12:23
 * @Since
 */
public class Point {
    private double x;
    private double y;

    public Point (){
        this(0,0);
    }

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            return this.getX() == ((Point) obj).getX() && this.getY() == ((Point) obj).getY();
        }
        return false;
    }
}
