package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

public class TaiKhoan {
    private String email,pass;

    public TaiKhoan(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
