package com.coffeejjim.developers.data;

public class ListResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = 9051798750517355569L;

    private T result;
    private int currentPage;
    private int code;

    public T getResult() {
        return result;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCode() {
        return code;
    }
}
