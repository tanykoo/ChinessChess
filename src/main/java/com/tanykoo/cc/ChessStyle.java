package com.tanykoo.cc;

import com.tanykoo.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 12:10
 * @Since
 */
public class ChessStyle implements Serializable {
    public static final ChessStyle STYLE_DEFAULT = new ChessStyle(Utils.getClassResource("/images"));

    private static final String STYLE_PROPERTIES = "style.properties";
    private static final String STYLE_BOARD = "bg.png";
    private static final String STYLE_DOT = "dot.png";
    private static final String STYLE_BBOX = "b_box.png";
    private static final String STYLE_BC = "b_c.png";
    private static final String STYLE_BJ = "b_j.png";
    private static final String STYLE_BM = "b_m.png";
    private static final String STYLE_BP = "b_p.png";
    private static final String STYLE_BS = "b_s.png";
    private static final String STYLE_BX = "b_x.png";
    private static final String STYLE_BZ = "b_z.png";

    private static final String STYLE_RBOX = "r_box.png";
    private static final String STYLE_RC = "r_c.png";
    private static final String STYLE_RJ = "r_j.png";
    private static final String STYLE_RM = "r_m.png";
    private static final String STYLE_RP = "r_p.png";
    private static final String STYLE_RS = "r_s.png";
    private static final String STYLE_RX = "r_x.png";
    private static final String STYLE_RZ = "r_z.png";


    public ChessStyle(URL styleSource) throws StyleException {
        if(!assertIsStyleRource(styleSource)){
            throw new StyleException("this is not a style source [" + styleSource.toString() + "]");
        }
        setBoard(styleSource.toString() + Utils.SEPARATOR + STYLE_BOARD);
        setB_box(styleSource.toString() + Utils.SEPARATOR + STYLE_BBOX);
        setB_c(styleSource.toString() + Utils.SEPARATOR + STYLE_BC);
        setB_m(styleSource.toString() + Utils.SEPARATOR + STYLE_BM);
        setB_x(styleSource.toString() + Utils.SEPARATOR + STYLE_BX);
        setB_s(styleSource.toString() + Utils.SEPARATOR + STYLE_BS);
        setB_j(styleSource.toString() + Utils.SEPARATOR + STYLE_BJ);
        setB_p(styleSource.toString() + Utils.SEPARATOR + STYLE_BP);
        setB_z(styleSource.toString() + Utils.SEPARATOR + STYLE_BZ);

        setR_box(styleSource.toString() + Utils.SEPARATOR + STYLE_RBOX);
        setR_c(styleSource.toString() + Utils.SEPARATOR + STYLE_RC);
        setR_m(styleSource.toString() + Utils.SEPARATOR + STYLE_RM);
        setR_x(styleSource.toString() + Utils.SEPARATOR + STYLE_RX);
        setR_s(styleSource.toString() + Utils.SEPARATOR + STYLE_RS);
        setR_j(styleSource.toString() + Utils.SEPARATOR + STYLE_RJ);
        setR_p(styleSource.toString() + Utils.SEPARATOR + STYLE_RP);
        setR_z(styleSource.toString() + Utils.SEPARATOR + STYLE_RZ);

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(styleSource.getPath() + Utils.SEPARATOR + STYLE_PROPERTIES)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Point sPoint00 = new Point();

        sPoint00.setX(Double.parseDouble((String) properties.get("point00_x")));
        sPoint00.setY(Double.parseDouble((String) properties.get("point00_y")));
        setPoint00(sPoint00);

        setWidth(Double.parseDouble((String) properties.get("width")));
        setHeight(Double.parseDouble((String) properties.get("height")));
    }

    private boolean assertIsStyleRource(URL styleSource){
        File file = new File(styleSource.getPath());
        boolean flag = file.isDirectory();
        if(file.isDirectory()){

            File f_bbox = new File(file.getPath() + Utils.SEPARATOR + STYLE_BBOX);
            flag = f_bbox.isFile() && flag;
            File f_bc = new File(file.getPath() + Utils.SEPARATOR + STYLE_BC);
            flag = f_bc.isFile() && flag;
            File f_bj = new File(file.getPath() + Utils.SEPARATOR + STYLE_BJ);
            flag = f_bj.isFile() && flag;
            File f_bm = new File(file.getPath() + Utils.SEPARATOR + STYLE_BM);
            flag = f_bm.isFile() && flag;
            File f_bp = new File(file.getPath() + Utils.SEPARATOR + STYLE_BP);
            flag = f_bp.isFile() && flag;
            File f_bs = new File(file.getPath() + Utils.SEPARATOR + STYLE_BS);
            flag = f_bs.isFile() && flag;
            File f_bx = new File(file.getPath() + Utils.SEPARATOR + STYLE_BX);
            flag = f_bx.isFile() && flag;
            File f_bz = new File(file.getPath() + Utils.SEPARATOR + STYLE_BZ);
            flag = f_bz.isFile() && flag;

            File f_rbox = new File(file.getPath() + Utils.SEPARATOR + STYLE_RBOX);
            flag = f_rbox.isFile() && flag;
            File f_rc = new File(file.getPath() + Utils.SEPARATOR + STYLE_RC);
            flag = f_rc.isFile() && flag;
            File f_rj = new File(file.getPath() + Utils.SEPARATOR + STYLE_RJ);
            flag = f_rj.isFile() && flag;
            File f_rm = new File(file.getPath() + Utils.SEPARATOR + STYLE_RM);
            flag = f_rm.isFile() && flag;
            File f_rp = new File(file.getPath() + Utils.SEPARATOR + STYLE_RP);
            flag = f_rp.isFile() && flag;
            File f_rs = new File(file.getPath() + Utils.SEPARATOR + STYLE_RS);
            flag = f_rs.isFile() && flag;
            File f_rx = new File(file.getPath() + Utils.SEPARATOR + STYLE_RX);
            flag = f_rx.isFile() && flag;
            File f_rz = new File(file.getPath() + Utils.SEPARATOR + STYLE_RZ);
            flag = f_rz.isFile() && flag;

            File f_dot = new File(file.getPath() + Utils.SEPARATOR + STYLE_DOT);
            flag = f_dot.isFile() && flag;
            File f_board = new File(file.getPath() + Utils.SEPARATOR + STYLE_BOARD);
            flag = f_board.isFile() && flag;

            File f_prop = new File(file.getPath() + Utils.SEPARATOR + STYLE_PROPERTIES);
            flag = f_prop.exists() && f_prop.isFile() && flag;

        }
        return flag;

    }



    private String board;

    private String b_box;

    private String b_c;

    private String b_j;

    private String b_m;

    private String b_p;

    private String b_s;

    private String b_z;

    private String b_x;

    private String dot;

    private String r_box;

    private String r_c;

    private String r_j;

    private String r_m;

    private String r_p;

    private String r_s;

    private String r_z;

    private String r_x;

    private Point point00;

    private double width;

    private double height;

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getB_box() {
        return b_box;
    }

    public void setB_box(String b_box) {
        this.b_box = b_box;
    }

    public String getB_c() {
        return b_c;
    }

    public void setB_c(String b_c) {
        this.b_c = b_c;
    }

    public String getB_j() {
        return b_j;
    }

    public void setB_j(String b_j) {
        this.b_j = b_j;
    }

    public String getB_m() {
        return b_m;
    }

    public void setB_m(String b_m) {
        this.b_m = b_m;
    }

    public String getB_p() {
        return b_p;
    }

    public void setB_p(String b_p) {
        this.b_p = b_p;
    }

    public String getB_s() {
        return b_s;
    }

    public void setB_s(String b_s) {
        this.b_s = b_s;
    }

    public String getB_z() {
        return b_z;
    }

    public void setB_z(String b_z) {
        this.b_z = b_z;
    }

    public String getB_x() {
        return b_x;
    }

    public void setB_x(String b_x) {
        this.b_x = b_x;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getR_box() {
        return r_box;
    }

    public void setR_box(String r_box) {
        this.r_box = r_box;
    }

    public String getR_c() {
        return r_c;
    }

    public void setR_c(String r_c) {
        this.r_c = r_c;
    }

    public String getR_j() {
        return r_j;
    }

    public void setR_j(String r_j) {
        this.r_j = r_j;
    }

    public String getR_m() {
        return r_m;
    }

    public void setR_m(String r_m) {
        this.r_m = r_m;
    }

    public String getR_p() {
        return r_p;
    }

    public void setR_p(String r_p) {
        this.r_p = r_p;
    }

    public String getR_s() {
        return r_s;
    }

    public void setR_s(String r_s) {
        this.r_s = r_s;
    }

    public String getR_z() {
        return r_z;
    }

    public void setR_z(String r_z) {
        this.r_z = r_z;
    }

    public String getR_x() {
        return r_x;
    }

    public void setR_x(String r_x) {
        this.r_x = r_x;
    }

    public Point getPoint00() {
        return point00;
    }

    public void setPoint00(Point point00) {
        this.point00 = point00;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
