package com.ghc.tdi_main.Memo;

public class memo_list_items {

    public String title;
    public String memo;
    public String create_day;
    public String update_day;
    public String targb;    // 텍스트 argb
    public String bargb;    // 배경 argb
    public String boargb;   // 테두리 argb
    public String align;    // 정렬

    public memo_list_items(String title, String memo, String create_day, String update_day, String targb, String bargb, String boargb, String align) {
        this.title = title;
        this.memo = memo;
        this.create_day = create_day;
        this.update_day = update_day;
        this.targb = targb;
        this.bargb = bargb;
        this.boargb = boargb;
        this.align = align;
    }

    public memo_list_items(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }

    public String getUpdate_day() {
        return update_day;
    }

    public void setUpdate_day(String update_day) {
        this.update_day = update_day;
    }

    public void setTargb(String targb) {
        this.targb = targb;
    }

    public String getTargb() {
        return targb;
    }

    public void setBargb(String bargb) {
        this.bargb = bargb;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getBargb() {
        return bargb;
    }

    public String getAlign() {
        return align;
    }

    public String getBoargb() {
        return boargb;
    }

    public void setBoargb(String boargb) {
        this.boargb = boargb;
    }

    @Override
    public String toString() {
        return "memo_list_items{" +
                "title='" + title + '\'' +
                ", memo='" + memo + '\'' +
                ", create_day='" + create_day + '\'' +
                ", update_day='" + update_day + '\'' +
                '}';
    }
}
