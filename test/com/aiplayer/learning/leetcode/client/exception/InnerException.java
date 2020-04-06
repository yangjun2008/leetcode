package com.aiplayer.learning.leetcode.client.exception;

public class InnerException extends Exception {
    @Override
    public String getMessage() {
        return "Inner exception, maybe bugs.";
    }
}
