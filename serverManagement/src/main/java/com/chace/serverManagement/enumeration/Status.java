package com.chace.serverManagement.enumeration;

public enum Status {
    SERVER_UP("SERVER _UP"),
    SERVER_DOWN("SERVER_DOWN");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
