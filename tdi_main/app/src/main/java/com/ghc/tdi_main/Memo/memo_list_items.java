package com.ghc.tdi_main.Memo;

public class memo_list_items {


    public String title;
    public String memo;
    public String create_day;
    public String update_day;

    public memo_list_items(String title, String memo, String create_day, String update_day) {
        this.title = title;
        this.memo = memo;
        this.create_day = create_day;
        this.update_day = update_day;
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
