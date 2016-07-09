package com.cninter.a3dgame.utils;

/**
 * Created by ${jacksen-hss} on 2016/7/9 0009.
 */
public class GridViewImgText {
        private String shortTitle;
        private String pathImag;

    public GridViewImgText(String shortTitle, String pathImag) {
        this.shortTitle = shortTitle;
        this.pathImag = pathImag;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getPathImag() {
        return pathImag;
    }

    public void setPathImag(String pathImag) {
        this.pathImag = pathImag;
    }
}
