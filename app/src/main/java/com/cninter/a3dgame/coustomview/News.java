package com.cninter.a3dgame.coustomview;

/**
 * Created by ${jacksen-hss} on 2016/6/25 0025.
 */
public class News {
    private String title;
    private String description;
    private String pathimg;
    private String litpic;
    private String arcurl;
    private String pubdate;
    private String click;

    public News(String title, String description, String pathimg, String litpic, String arcurl, String pubdate, String click) {
        this.title = title;
        this.description = description;
        this.pathimg = pathimg;
        this.litpic = litpic;
        this.arcurl = arcurl;
        this.pubdate = pubdate;
        this.click = click;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathimg() {
        return pathimg;
    }

    public void setPathimg(String pathimg) {
        this.pathimg = pathimg;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
}
