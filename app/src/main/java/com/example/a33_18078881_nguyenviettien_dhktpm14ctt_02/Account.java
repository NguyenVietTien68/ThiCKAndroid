package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

public class Account {
    private String bank, account, name;

    public Account(String bank, String account, String name) {
        this.bank = bank;
        this.account = account;
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
