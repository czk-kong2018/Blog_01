package com.zhbit.entity;

public class Login {
    private int login_id;
    private String pwd;
    private String email;

    @Override
    public String toString() {
        return "Login{" +
                "login_id=" + login_id +
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
