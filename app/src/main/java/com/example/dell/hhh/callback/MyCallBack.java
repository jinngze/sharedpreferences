package com.example.dell.hhh.callback;

public interface MyCallBack<T> {

    void  success(T data);
    void  failed(Exception e);
}
