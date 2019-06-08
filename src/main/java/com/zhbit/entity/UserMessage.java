package com.zhbit.entity;

public class UserMessage {
    private int user_id;
    private String user_name;
    private String head_portrait;
    private String user_profile;
    @Override
    public String toString() {
        return "UserMessage{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                ", user_profile='" + user_profile + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }
}
