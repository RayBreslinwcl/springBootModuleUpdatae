package com.rhwayfun.springboot.quickstart.vo;

import lombok.Data;

@Data
public class Result {

    private int code;

    private String msg;

    private boolean success;

    private Object data;
}