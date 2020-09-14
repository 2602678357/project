package com.bhsoftware.projectserver.result;

import java.io.Serializable;

public class ResultFactory<T> implements Serializable {
    //成功返回，并带回所需数据
    public  Result buildSuccessResult(T data){
        return buildResult(ResultCode.SUCCESS,"成功",data);
    }

    public Result buildSuccessResult2(ResultCode resultCode,T data){
        return build2(ResultCode.SUCCESS,data);
    }


    public static Result buildSuccessCode(){
        return build(ResultCode.SUCCESS);
    }

    public static Result buildFailCode(){
        return build(ResultCode.FAIL);
    }

    //结果失败，显示相关提示信息
    public  Result buildFailResult(String message){
        return buildResult(ResultCode.FAIL,message,null);
    }
    public  Result buildResult(ResultCode resultCode,String
            message,T data){
        return buildResult(resultCode.code,message,data);
    }

    public  Result buildResult(int resultCode,String message,T
            data){
        return new Result(resultCode,message,data);
    }


    public static Result build(ResultCode resultCode){
        return new Result(resultCode.code);
    }

    public  Result build2(ResultCode resultCode,T data){
        return new Result(resultCode.code,data);
    }
}
