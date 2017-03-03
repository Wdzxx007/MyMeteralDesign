package com.example.jialijiang.mymeterialdesign.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jialijiang on 17/3/1.
 */

public class GirlEntity implements Parcelable{
    private String Name;
    private int ImageId;

    public void setName(String name) {
        Name = name;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getName() {

        return Name;
    }

    public int getImageId() {
        return ImageId;
    }

    public GirlEntity(String name, int imageId) {
        this.Name = name;
        this.ImageId = imageId;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeInt(this.ImageId);
    }

    protected GirlEntity(Parcel in) {
        this.Name = in.readString();
        this.ImageId = in.readInt();
    }

    public static final Creator<GirlEntity> CREATOR = new Creator<GirlEntity>() {
        @Override
        public GirlEntity createFromParcel(Parcel source) {
            return new GirlEntity(source);
        }

        @Override
        public GirlEntity[] newArray(int size) {
            return new GirlEntity[size];
        }
    };
}
