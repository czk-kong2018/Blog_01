package com.zhbit.entity;

public class Login {
    private int login_id;
    private String login_name;
    private String pwd;
    private String email;
    @Override
    public String toString() {
        return "Login{" +
                "login_id=" + login_id +
                ", login_name='" + login_name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
