package com.qf.level.bean;

/**
 * Created by level on 2016/12/21.
 */

public class Star {


    /**
     * id : 12
     * name : 李钟硕
     * pic : http://cdn01.happyjuzi.com/upload/star/57148aa4444f6.jpg!ac1.nw.webp
     * description : 李钟硕，韩国演员、模特。2010年3月参演律政剧《检察官公主》，以演员身份正式出道，同年9月参演浪漫爱情剧《秘密花园》；2013年6月主演的幻想爱情剧《听见你的声音》位列韩国水木剧年度收视冠军，并被中国观众所知；2014年12月凭借职业爱情剧《匹诺曹》在中国大热。
     * type : 6
     * urlroute : juzi://star/detail?id=12&name=%E6%9D%8E%E9%92%9F%E7%A1%95
     * issub : false
     */

    private int id;
    private String name;
    private String pic;
    private String description;
    private int type;
    private String urlroute;
    private boolean issub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrlroute() {
        return urlroute;
    }

    public void setUrlroute(String urlroute) {
        this.urlroute = urlroute;
    }

    public boolean isIssub() {
        return issub;
    }

    public void setIssub(boolean issub) {
        this.issub = issub;
    }
}
