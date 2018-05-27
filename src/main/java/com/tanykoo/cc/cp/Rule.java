package com.tanykoo.cc.cp;

import com.tanykoo.cc.Camp;
import com.tanykoo.cc.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tany
 * @createtime 2018-05-26 下午11:21
 * @since
 */
public class Rule {

    private static Logger logger = LoggerFactory.getLogger(Rule.class);

    public static List<Point> getEnablePointC_P(int [][] table,Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point = null;

        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() + 1; i < 10; i++){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && !hasPiece(table,point)){
                points.add(point);
            }else {
                break;
            }
        }
        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() - 1; i >= 0; i--){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && !hasPiece(table,point)){
                points.add(point);
            }else {
                break;
            }
        }
        //横向向右可移动点
        for(int i = (int) currentPoint.getY() + 1 ; i<9; i++){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && !hasPiece(table,point)){
                points.add(point);
            }else {
                break;
            }
        }
        //横向向左可移动点
        for(int i = (int) currentPoint.getY() - 1 ; i>=0; i--){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && !hasPiece(table,point)){
                points.add(point);
            }else {
                break;
            }
        }

        return points;
    }

    public static List<Point> getEnablePointM(int [][] table,Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()+1,currentPoint.getY()-2);
        Point point2 = new Point(currentPoint.getX()+2,currentPoint.getY()-1);
        Point point3 = new Point(currentPoint.getX()+2,currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX()+1,currentPoint.getY()+2);
        Point point5 = new Point(currentPoint.getX()-1,currentPoint.getY()+2);
        Point point6 = new Point(currentPoint.getX()-2,currentPoint.getY()+1);
        Point point7 = new Point(currentPoint.getX()-2,currentPoint.getY()-1);
        Point point8 = new Point(currentPoint.getX()-1,currentPoint.getY()-2);

        if(valiPointM_X(table,point1,currentPoint)){
            points.add(point1);
        }
        if(valiPointM_X(table,point2,currentPoint)){
            points.add(point2);
        }
        if(valiPointM_X(table,point3,currentPoint)){
            points.add(point3);
        }
        if(valiPointM_X(table,point4,currentPoint)){
            points.add(point4);
        }
        if(valiPointM_X(table,point5,currentPoint)){
            points.add(point5);
        }
        if(valiPointM_X(table,point6,currentPoint)){
            points.add(point6);
        }
        if(valiPointM_X(table,point7,currentPoint)){
            points.add(point7);
        }
        if(valiPointM_X(table,point8,currentPoint)){
            points.add(point8);
        }


        return points;
    }

    private static boolean valiPointM_X(int[][]table, Point point, Point currentPoint){
        //检查点是否在棋盘上
        boolean flag = isOnGrids(point);
        if(flag){
            //检查点上是否有棋子
            flag = !hasPiece(table,point);
            if(flag){
                //检查是否憋马蹄
                flag = !lockFooter(table,point,currentPoint);
            }
        }
        return flag;
    }

    private static boolean hasPiece(int[][] table, Point point){
        return table[(int)point.getX()][(int)point.getY()] != 0;
    }

    /**
     * 检查点是否在棋盘上
     * @param point
     * @return
     */
    private static boolean isOnGrids(Point point){
        boolean flag = point.getX()>=0 && point.getX()<10 && point.getY() >=0 && point.getY()<9;
        logger.debug("Point["+point.getX()+","+point.getY()+ "] isOnGrid is " + flag);

        return flag;

    }

    public static List<Point> getEnablePointX(int [][] table,Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-2,currentPoint.getY()-2);
        Point point2 = new Point(currentPoint.getX()+2,currentPoint.getY()-2);
        Point point3 = new Point(currentPoint.getX()-2,currentPoint.getY()+2);
        Point point4 = new Point(currentPoint.getX()+2,currentPoint.getY()+2);

        if(valiPointM_X(table,point1,currentPoint) && valiPointX(point1,currentPoint)){
            points.add(point1);
        }
        if(valiPointM_X(table,point2,currentPoint) && valiPointX(point2,currentPoint)){
            points.add(point2);
        }
        if(valiPointM_X(table,point3,currentPoint) && valiPointX(point3,currentPoint)){
            points.add(point3);
        }
        if(valiPointM_X(table,point4,currentPoint) && valiPointX(point4,currentPoint)){
            points.add(point4);
        }

        return points;
    }

    public static List<Point> getEnablePointS(int [][] table,Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY()-1);
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY()-1);
        Point point3 = new Point(currentPoint.getX()-1,currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX()+1,currentPoint.getY()+1);


        if(valiPointJ_S(table,point1)){
            points.add(point1);
        }
        if(valiPointJ_S(table,point2)){
            points.add(point2);
        }
        if(valiPointJ_S(table,point3)){
            points.add(point3);
        }
        if(valiPointJ_S(table,point4)){
            points.add(point4);
        }



        return points;
    }
    public static List<Point> getEnablePointJ(int [][] table,Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY());
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY());
        Point point3 = new Point(currentPoint.getX(),currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX(),currentPoint.getY()-1);


        if(valiPointJ_S(table,point1)){
            points.add(point1);
        }
        if(valiPointJ_S(table,point2)){
            points.add(point2);
        }
        if(valiPointJ_S(table,point3)){
            points.add(point3);
        }
        if(valiPointJ_S(table,point4)){
            points.add(point4);
        }

        return points;
    }

    public static List<Point> getEnablePointZ(int[][]table,Point currentPoint,Camp camp){
        List<Point> points = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY());
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY());
        Point point3 = new Point(currentPoint.getX(),currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX(),currentPoint.getY()-1);

        if(isOnGrids(point1) && camp==Camp.RED && !hasPiece(table,point1)){
            points.add(point1);
        }
        if(isOnGrids(point2) && camp==Camp.BLACK && !hasPiece(table,point2)){
            points.add(point2);
        }
        if(isOnGrids(point3) && isCrossing(currentPoint,camp) && !hasPiece(table,point3)){
            points.add(point3);
        }
        if(isOnGrids(point4) && isCrossing(currentPoint,camp) && !hasPiece(table,point4)){
            points.add(point4);
        }


        return points;

    }


    /**
     * 兵过河检测
     * @param currentPoint
     * @param camp
     * @return
     */
    private static boolean isCrossing(Point currentPoint,Camp camp){

        boolean flag = false;

        switch (camp){
            case RED:
                flag = currentPoint.getX()<=4;
                break;
            case BLACK:
                flag = currentPoint.getX()>4;
                break;
        }

        return flag;
    }

    /**
     * 象过河检测
     * @param point
     * @param currentPoint
     * @return true:没过河  false：过河
     */
    private static boolean valiPointX(Point point, Point currentPoint){

        boolean flag = (currentPoint.getX()<5 == point.getX()<5);

        logger.debug(currentPoint.getX() + "    " + point.getX());

        return flag;
    }


    private static boolean valiPointJ_S(int[][] table, Point point){
        return isOnGrids(point) && !hasPiece(table,point) && isInHeadquarters(point);
    }
    /**
     * 将 士出界检测
     * @param point
     * @return
     */
    private static boolean isInHeadquarters(Point point){


        boolean flag = point.getY() >=3 && point.getY()<=5 && ((point.getX()<=2)||(point.getX()>=7));

        logger.debug("Point["+point.getX()+","+point.getY()+ "] is isInHeadquarters is " + flag);

        return flag;
    }

    /**
     * 蹩脚检测
     * @param table
     * @param point
     * @param currentPoint
     * @return true: 蹩脚   false: 不蹩脚
     */
    private static boolean lockFooter(int[][]table, Point point, Point currentPoint){

        return table[(int) (currentPoint.getX() + ((int)point.getX() - (int)currentPoint.getX())/2)][(int) (currentPoint.getY() + ((int)point.getY() - (int)currentPoint.getY())/2)] == 1;
    }

    /**
     * 车可吃点统计
     * @param table
     * @param currentPoint
     * @return
     */
    public static List<Point> getEatAblePointC(int[][] table, Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point = null;

        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() + 1; i < 10; i++){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && hasPiece(table,point)){
                points.add(point);
                break;
            }
        }
        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() - 1; i >= 0; i--){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && hasPiece(table,point)){
                points.add(point);
                break;
            }
        }
        //横向向右可移动点
        for(int i = (int) currentPoint.getY() + 1 ; i<9; i++){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && hasPiece(table,point)){
                points.add(point);
                break;
            }
        }
        //横向向左可移动点
        for(int i = (int) currentPoint.getY() - 1 ; i>=0; i--){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && hasPiece(table,point)){
                points.add(point);
                break;
            }
        }

        return points;
    }

    /**
     * 炮可吃点统计
     * @param table
     * @param currentPoint
     * @return
     */
    public static List<Point> getEatAblePointP(int[][] table, Point currentPoint){
        List<Point> points = new ArrayList<>();

        Point point = null;

        boolean flag = false;
        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() + 1; i < 10; i++){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && hasPiece(table,point)){
                if(flag) {
                    points.add(point);
                    break;
                }else {
                    flag = true;
                }
            }
        }
        flag = false;
        //纵向向下可移动点
        for(int i = (int) currentPoint.getX() - 1; i >= 0; i--){
            point = new Point(i, currentPoint.getY());
            if(isOnGrids(point) && hasPiece(table,point)){
                if(flag) {
                    points.add(point);
                    break;
                }else {
                    flag = true;
                }
            }
        }
        flag = false;
        //横向向右可移动点
        for(int i = (int) currentPoint.getY() + 1 ; i<9; i++){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && hasPiece(table,point)){
                if(flag) {
                    points.add(point);
                    break;
                }else {
                    flag = true;
                }
            }
        }
        flag = false;
        //横向向左可移动点
        for(int i = (int) currentPoint.getY() - 1 ; i>=0; i--){
            point = new Point(currentPoint.getX(), i);
            if(isOnGrids(point) && hasPiece(table,point)){
                if(flag) {
                    points.add(point);
                    break;
                }else {
                    flag = true;
                }
            }
        }

        return points;
    }

    public static List<Point> getEatAblePointM(int[][] table, Point currentPoint){
        List<Point> pointList = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()+1,currentPoint.getY()-2);
        Point point2 = new Point(currentPoint.getX()+2,currentPoint.getY()-1);
        Point point3 = new Point(currentPoint.getX()+2,currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX()+1,currentPoint.getY()+2);
        Point point5 = new Point(currentPoint.getX()-1,currentPoint.getY()+2);
        Point point6 = new Point(currentPoint.getX()-2,currentPoint.getY()+1);
        Point point7 = new Point(currentPoint.getX()-2,currentPoint.getY()-1);
        Point point8 = new Point(currentPoint.getX()-1,currentPoint.getY()-2);
        if(isOnGrids(point1) && hasPiece(table,point1) && !lockFooter(table,point1,currentPoint)){
            pointList.add(point1);
        }
        if(isOnGrids(point2) && hasPiece(table,point2) && !lockFooter(table,point2,currentPoint)){
            pointList.add(point2);
        }
        if(isOnGrids(point3) && hasPiece(table,point3) && !lockFooter(table,point3,currentPoint)){
            pointList.add(point3);
        }
        if(isOnGrids(point4) && hasPiece(table,point4) && !lockFooter(table,point4,currentPoint)){
            pointList.add(point4);
        }
        if(isOnGrids(point5) && hasPiece(table,point5) && !lockFooter(table,point5,currentPoint)){
            pointList.add(point5);
        }
        if(isOnGrids(point6) && hasPiece(table,point6) && !lockFooter(table,point6,currentPoint)){
            pointList.add(point6);
        }
        if(isOnGrids(point7) && hasPiece(table,point7) && !lockFooter(table,point7,currentPoint)){
            pointList.add(point7);
        }
        if(isOnGrids(point8) && hasPiece(table,point8) && !lockFooter(table,point8,currentPoint)){
            pointList.add(point8);
        }


        return pointList;

    }

    public static List<Point> getEatAblePointX(int[][] table, Point currentPoint){
        List<Point> pointList = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-2,currentPoint.getY()-2);
        Point point2 = new Point(currentPoint.getX()+2,currentPoint.getY()-2);
        Point point3 = new Point(currentPoint.getX()-2,currentPoint.getY()+2);
        Point point4 = new Point(currentPoint.getX()+2,currentPoint.getY()+2);
        if(isOnGrids(point1) && hasPiece(table,point1) && !lockFooter(table,point1,currentPoint) && valiPointX(point1,currentPoint)){
            pointList.add(point1);
        }
        if(isOnGrids(point2) && hasPiece(table,point2) && !lockFooter(table,point2,currentPoint ) && valiPointX(point2,currentPoint)){
            pointList.add(point2);
        }
        if(isOnGrids(point3) && hasPiece(table,point3) && !lockFooter(table,point3,currentPoint)  && valiPointX(point3,currentPoint)){
            pointList.add(point3);
        }
        if(isOnGrids(point4) && hasPiece(table,point4) && !lockFooter(table,point4,currentPoint)  && valiPointX(point4,currentPoint)){
            pointList.add(point4);
        }


        return pointList;

    }

    public static List<Point> getEatAblePointS(int[][] table, Point currentPoint){
        List<Point> pointList = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY()-1);
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY()-1);
        Point point3 = new Point(currentPoint.getX()-1,currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX()+1,currentPoint.getY()+1);
        if(isOnGrids(point1) && hasPiece(table,point1) && isInHeadquarters(point1)){
            pointList.add(point1);
        }
        if(isOnGrids(point2) && hasPiece(table,point2) && isInHeadquarters(point2)){
            pointList.add(point2);
        }
        if(isOnGrids(point3) && hasPiece(table,point3) && isInHeadquarters(point3)){
            pointList.add(point3);
        }
        if(isOnGrids(point4) && hasPiece(table,point4) && isInHeadquarters(point4)){
            pointList.add(point4);
        }


        return pointList;

    }

    public static List<Point> getEatAblePointJ(int[][] table, Point currentPoint){
        List<Point> pointList = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY());
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY());
        Point point3 = new Point(currentPoint.getX(),currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX(),currentPoint.getY()-1);

        Point point5 = new Point(currentPoint.getX() > 2 ? 0:7,currentPoint.getY());
        Point point6 = new Point(currentPoint.getX() > 2 ? 1:8,currentPoint.getY());
        Point point7 = new Point(currentPoint.getX() > 2 ? 2:9,currentPoint.getY());

        if(isOnGrids(point1) && hasPiece(table,point1) && isInHeadquarters(point1)){
            pointList.add(point1);
        }
        if(isOnGrids(point2) && hasPiece(table,point2) && isInHeadquarters(point2)){
            pointList.add(point2);
        }
        if(isOnGrids(point3) && hasPiece(table,point3) && isInHeadquarters(point3)){
            pointList.add(point3);
        }
        if(isOnGrids(point4) && hasPiece(table,point4) && isInHeadquarters(point4)){
            pointList.add(point4);
        }

        if(isHeader(table,point5) && !hasPieceBetweenTwoPoint(table,point5,currentPoint)){
            pointList.add(point5);
        }
        if(isHeader(table,point6) && !hasPieceBetweenTwoPoint(table,point6,currentPoint)){
            pointList.add(point6);
        }
        if(isHeader(table,point7) && !hasPieceBetweenTwoPoint(table,point6,currentPoint)){
            pointList.add(point7);
        }


        return pointList;

    }

    /**
     *
     * @param table
     * @param point
     * @return
     */
    private static boolean isHeader(int [][] table, Point point){
        return table[(int)point.getX()][(int) point.getY()] == 2;

    }

    /**
     * 检查两点之间是否有棋子
     * 验证将是否照面
     * @param table
     * @param point1
     * @param point2
     * @return
     */
    private static boolean hasPieceBetweenTwoPoint(int[][]table, Point point1, Point point2){
        boolean flag = false;
        if(point1.getY() != point2.getY()){
            flag = false;
        }else{
            for (int i = (int) Math.min(point1.getX(),point2.getX()) + 1; i < Math.max(point1.getX(),point2.getX()); i++) {
                if(table[i][(int)point1.getY()] !=0){
                    flag = true;
                    break;
                }
            }
        }
        return flag;

    }



    /**
     *
     * @param table
     * @param currentPoint
     * @param camp
     * @return
     */
    public static List<Point> getEatAblePointZ(int[][] table, Point currentPoint,Camp camp){
        List<Point> pointList = new ArrayList<>();

        Point point1 = new Point(currentPoint.getX()-1,currentPoint.getY());
        Point point2 = new Point(currentPoint.getX()+1,currentPoint.getY());
        Point point3 = new Point(currentPoint.getX(),currentPoint.getY()+1);
        Point point4 = new Point(currentPoint.getX(),currentPoint.getY()-1);

        if(isOnGrids(point1) && camp==Camp.RED && hasPiece(table,point1)){
            pointList.add(point1);
        }
        if(isOnGrids(point2) && camp==Camp.BLACK && hasPiece(table,point2)){
            pointList.add(point2);
        }
        if(isOnGrids(point3) && isCrossing(currentPoint,camp) && hasPiece(table,point3)){
            pointList.add(point3);
        }
        if(isOnGrids(point4) && isCrossing(currentPoint,camp) && hasPiece(table,point4)){
            pointList.add(point4);
        }


        return pointList;

    }



}
