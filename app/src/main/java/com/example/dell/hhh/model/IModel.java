package com.example.dell.hhh.model;

import com.example.dell.hhh.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void  requestData(String url, Map<String,String>params, Class clazz, MyCallBack callBack);
}
