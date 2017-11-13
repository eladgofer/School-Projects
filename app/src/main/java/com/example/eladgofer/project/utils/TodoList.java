package com.example.eladgofer.project.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eladgofer on 09/07/2017.
 */

public class TodoList implements Parcelable {

    public static final Creator<TodoList> CREATOR = new Creator<TodoList>() {
        @Override
        public TodoList createFromParcel(Parcel source) {
            return new TodoList(source);
        }

        @Override
        public TodoList[] newArray(int size) {
            return new TodoList[size];
        }


    };
    private String title;
    private String owner; //Display Name / UID
    private String updateTime;
    private int profileImage;
    private String listId;

    public TodoList() {
    }

    public TodoList(Parcel source) {
    }

    public TodoList(String title, String owner, String updateTime, int profileImage, String listId) {
        this.title = title;
        this.owner = owner;
        this.updateTime = updateTime;
        this.profileImage = profileImage;
        this.listId = listId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", listId='" + listId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.owner);
        dest.writeString(this.updateTime);
        dest.writeString(String.valueOf(this.profileImage));
        dest.writeString(this.listId);
    }
}
