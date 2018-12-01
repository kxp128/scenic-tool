package com.lsn.user.model.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("请求成功返回对象")
public class ListSuccessResult<T> {

    @ApiModelProperty("数据")
    private Data<T> result;

    public ListSuccessResult(int total, List<T> items) {
        this.result = new Data<T>(total, items);
    }

    public Data<T> getResult() {
        return result;
    }

    public void setResult(Data<T> result) {
        this.result = result;
    }

    @ApiModel
    class Data<T> {
        @ApiModelProperty("总数")
        private int total;

        @ApiModelProperty("数据")
        private List<T> items;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }

        public Data() {
        }

        public Data(int total, List<T> items) {
            this.total = total;
            this.items = items;
        }
    }
}

