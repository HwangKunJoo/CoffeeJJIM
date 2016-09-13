package com.coffeejjim.developers.data;

public class NetworkResult<T> {
    private T result;
    private int code;
    private String message;

    public String getMessage(){return this.message;}
    public T getResult() {
        return this.result;
    }
    public int getCode() {
        return this.code;
    }
}
