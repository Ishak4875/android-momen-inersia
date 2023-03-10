package com.example.momeninersia;


import android.os.Parcel;
import android.os.Parcelable;

public class Inertia implements Parcelable {
    private String title;
    private String formula;
    private Integer avatar;

    Inertia(){

    }

    protected Inertia(Parcel in) {
        title = in.readString();
        formula = in.readString();
        if (in.readByte() == 0) {
            avatar = null;
        } else {
            avatar = in.readInt();
        }
    }

    public static final Creator<Inertia> CREATOR = new Creator<Inertia>() {
        @Override
        public Inertia createFromParcel(Parcel in) {
            return new Inertia(in);
        }

        @Override
        public Inertia[] newArray(int size) {
            return new Inertia[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(formula);
        if (avatar == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(avatar);
        }
    }
}
