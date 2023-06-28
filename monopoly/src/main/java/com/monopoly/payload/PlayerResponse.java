package com.monopoly.payload;
public class PlayerResponse {
    private Long id;
    private String name;
    private int balance;

    public PlayerResponse(Long id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    // Other setter and getter methods, if needed
}
