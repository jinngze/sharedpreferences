package com.example.dell.hhh.view;

public interface IView <T>{

    void showResponseData(T data);
    void showResponseFail(T data);
}
