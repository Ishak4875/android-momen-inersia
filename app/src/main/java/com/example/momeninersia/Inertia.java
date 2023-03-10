package com.example.momeninersia;


import android.os.Parcel;
import android.os.Parcelable;

public class Inertia{
    private String title;
    private String formula;
    private Integer avatar;

    Inertia(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }
}
