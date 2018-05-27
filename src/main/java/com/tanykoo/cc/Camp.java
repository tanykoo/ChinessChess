package com.tanykoo.cc;

/**
 * @author Tany
 * @createtime 2018-05-26
 * @since
 */
public enum Camp {
    RED("汉"),BLACK("楚");

    private String name;

    private Camp(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
