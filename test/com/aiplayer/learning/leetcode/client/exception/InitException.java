package com.aiplayer.learning.leetcode.client.exception;

public class InitException extends Exception {

    @Override
    public String getMessage() {
        return "Init http client exception, please check the network.";
    }
}
