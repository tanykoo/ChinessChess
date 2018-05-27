package com.tanykoo.cc;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 17:04
 * @Since
 */
public class ChessManual {

    /**
     *
     */
    public static final char R_C = '1';
    public static final char R_M = '2';
    public static final char R_X = '3';
    public static final char R_S = '4';
    public static final char R_J = '5';
    public static final char R_P = '6';
    public static final char R_Z = '7';

    public static final char B_C = 'A';
    public static final char B_M = 'B';
    public static final char B_X = 'C';
    public static final char B_S = 'D';
    public static final char B_J = 'E';
    public static final char B_P = 'F';
    public static final char B_Z = 'G';

    public static final char NULL = '0';
    public static final char BORDOR_R = 'R';
    public static final char BORDOR_B = 'H';
    public static final char DOT = '.';

    public static final char FIRST_R = 'r';
    public static final char FIRST_B = 'b';

    private static final String manualStrPatten = "^[r,b]{1}[0-7,A-G]{90}[0,R,H,.]{0,90}$";



    private static final String INITALITASTR = "r"
            + "ABCDEDCBA"
            + "000000000"
            + "0F00000F0"
            + "G0G0G0G0G"
            + "000000000"
            + "000000000"
            + "707070707"
            + "060000060"
            + "000000000"
            + "123454321";

    public static final ChessManual INIT_MANUAL = createManual(INITALITASTR);

    /**
     * 当前先手方
     */
    private Camp camp;

    /**
     * 棋盘棋子信息
     */
    private int [][]table = new int[10][9];

    /**
     * 旗子信息
     */
    private Map<Point,Character> pieceMap = new Hashtable<>();

    private Map<Point,Character> boxMap = new Hashtable<>();

    private ChessManual(){

    }

    public ChessManual(Camp camp, Map<Point,Character> pieceMap,Map<Point,Character> boxMap){
        this.camp = camp;
        this.pieceMap = pieceMap==null? this.pieceMap: pieceMap;
        this.boxMap = boxMap==null? this.boxMap: boxMap;

    }

    public static ChessManual createManual(String manualStr){
        if(!manualStr.matches(manualStrPatten)){
            return null;
        }
        ChessManual chessManual = new ChessManual();
        char camp = manualStr.charAt(0);
        switch (camp){
            case  FIRST_B:
                chessManual.camp = Camp.BLACK;
                break;
            case FIRST_R:
                chessManual.camp = Camp.RED;
                break;
        }
        String mapStr = manualStr.substring(1,91);
        for(int i=0; i<90;i++){
            char tmp = mapStr.charAt(i);
            if(! (tmp == NULL)){
                if(tmp == B_J || tmp == R_J) {
                    chessManual.table[i / 9][i % 9 % 10] = 2;
                }else
                    chessManual.table[i / 9][i % 9 % 10] = 1;
                chessManual.pieceMap.put(new Point(i/9,i%9%10), tmp);
            }
        }
        if(manualStr.length()>91){
            String boxStr = manualStr.substring(91);
            for(int i=0; i<boxStr.length(); i++){
                char tmp = boxStr.charAt(i);
                if(!(tmp ==NULL)){
                    chessManual.boxMap.put(new Point(i/9,i%9%10), tmp);
                }
            }
        }
        return chessManual;
    }


    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public Map<Point, Character> getPieceMap() {
        return pieceMap;
    }

    public void setPieceMap(Map<Point, Character> pieceMap) {
        this.pieceMap = pieceMap;
    }

    public Map<Point, Character> getBoxMap() {
        return boxMap;
    }

    public void setBoxMap(Map<Point, Character> boxMap) {
        this.boxMap = boxMap;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0 ; i< (boxMap.size()==0?91:181); i++){
            stringBuilder.append(NULL);
        }
        switch (camp){
            case BLACK:
                stringBuilder.setCharAt(0,FIRST_B);
                break;
            case RED:
                stringBuilder.setCharAt(0,FIRST_R);
                break;
        }
        for (Point point:pieceMap.keySet()) {
            stringBuilder.setCharAt(((int)(point.getX()) * 9 + (int)(point.getY()) + 1), pieceMap.get(point));
        }
        for (Point point:boxMap.keySet()){
            stringBuilder.setCharAt(((int)(point.getX()) * 9 + (int)(point.getY()) + 91), boxMap.get(point));
        }




        return stringBuilder.toString();
    }

    /**
     * 清空点
     */
    public void clearDot(){
        Map<Point,Character> map = new Hashtable<>();
        for(Point point : boxMap.keySet()){
            if(!boxMap.get(point).equals(DOT)){
                map.put(point,boxMap.get(point));
            }
        }
        boxMap = map;
    }
    /**
     * 清空边框
     */
    public void clearBox(){
        Map<Point,Character> map = new Hashtable<>();
        for(Point point : boxMap.keySet()){
            if(boxMap.get(point).equals(DOT)){
                map.put(point,boxMap.get(point));
            }
        }
        boxMap = map;
    }

    public void addBoxs(Camp camp,List<Point> points){
        for(Point point : points){
            addBox(camp,point);
        }
    }
    public void addBox(Camp camp,Point point){
        if (Camp.BLACK.equals(camp)) {
            boxMap.put(point, BORDOR_B);
        } else {
            boxMap.put(point, BORDOR_R);
        }
    }

    public void addDots(List<Point> points){
        for (Point point : points) {
            boxMap.put(point, DOT);
        }
    }

    public void place(Point lastPoint, Point point){
        table[(int)point.getX()][(int)point.getY()] = table[(int)lastPoint.getX()][(int)lastPoint.getY()];
        table[(int)lastPoint.getX()][(int)lastPoint.getY()] = 0 ;
    }

    public void removePiece(Point point){
        for(Point point1 : pieceMap.keySet()){
            if(point1.equals(point)){
                pieceMap.remove(point1);
                table[(int)point.getX()][(int)point.getY()] = 0;
                break;
            }
        }
    }


}
