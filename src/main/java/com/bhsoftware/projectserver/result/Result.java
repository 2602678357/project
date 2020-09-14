package com.bhsoftware.projectserver.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result() {

    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
