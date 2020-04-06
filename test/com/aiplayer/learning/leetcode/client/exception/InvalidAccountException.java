package com.aiplayer.learning.leetcode.client.exception;

public class InvalidAccountException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid Account";
    }
}
