/*
  * GirlItemData      2017-11-25
  * Copyright (c) 2017 zch. All right reserved.
  *
  */
package com.zch.hometab.modules.girl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description
 *
 * @author zch
 * @since 2017-11-25
 */
public class GirlItemData implements Parcelable {

    public String title;
    public String url;
    public String id;
    public int width;
    public int height;
    public String subtype;

    public GirlItemData(String title, String url, String id, int width, int height, String subtype) {
        this.title = title;
        this.url = url;
        this.id = id;
        this.width = width;
        this.height = height;
        this.subtype = subtype;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.id);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.subtype);
    }

    protected GirlItemData(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.id = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.subtype = in.readString();
    }

    public static final Parcelable.Creator<GirlItemData> CREATOR = new Parcelable.Creator<GirlItemData>() {
        @Override
        public GirlItemData createFromParcel(Parcel source) {
            return new GirlItemData(source);
        }

        @Override
        public GirlItemData[] newArray(int size) {
            return new GirlItemData[size];
        }
    };
}