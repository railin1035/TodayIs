package com.ghc.tdi_main.Display;

public class Display_widgetdata {

    private String id;
    private String Name;
    private String Nameorder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameorder() {
        return Nameorder;
    }

    public void setNameorder(String nameorder) {
        Nameorder = nameorder;
    }

    public Display_widgetdata(String id, String name, String nameorder) {
        this.id = id;
        Name = name;
        Nameorder = nameorder;
    }
}

