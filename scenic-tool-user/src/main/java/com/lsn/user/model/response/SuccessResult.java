package com.lsn.user.model.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("请求成功返回对象")
public class SuccessResult<T> {

    @ApiModelProperty("数据")
    private T result;

    public SuccessResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public SuccessResult() {
    }
}
