package com.tanykoo.cc;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tany
 * @createtime 2018-05-26 下午6:36
 * @since
 */
public class ChessManualTest {

    private static Logger logger = LoggerFactory.getLogger(ChessManualTest.class);

    @Test
    public void test(){
        String manualStrPatten = "^[r,b]{1}[0-7,A-G]{90}[0,K,.]{0,90}$";
        String INITALITASTR = "b"
                + "ABCDEDCBA"
                + "000000000"
                + "0F00000F0"
                + "G0G0G0G0G"
                + "000000000"
                + "000000000"
                + "707070707"
                + "060002060"
                + "000000000"
                + "123454301"
                + ".........00000K00000......";

        System.out.println(INITALITASTR.matches(manualStrPatten));

//        ChessManual chessManual = ChessManual.createManual(INITALITASTR);
//
//        System.out.println(chessManual.toString());

        ChessManual chessManual1 = ChessManual.INIT_MANUAL;

        Assert.assertEquals("toString() 有错", chessManual1.toString(), "rABCDEDCBA0000000000F00000F0G0G0G0G0G000000000000000000707070707060000060000000000123454321");

        int[][] ints = chessManual1.getTable();
        for(int i =0 ; i <ints.length ; i++){
            for(int j =0 ; j < ints[i].length;j++){
                System.out.print(ints[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println(2.0 ==2);
        System.out.println(2.0 ==2);
        System.out.println(2.0 ==2);



    }
}
