package com.example.recyclerview.model;

import java.io.Serializable;

public class IconModel implements Serializable {
    private Integer idImg;
    private String desc;

    public IconModel(Integer idImg, String desc) {
        this.idImg = idImg;
        this.desc = desc;
    }

    public Integer getIdImg() {
        return idImg;
    }

    public void setIdImg(Integer idImg) {
        this.idImg = idImg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
