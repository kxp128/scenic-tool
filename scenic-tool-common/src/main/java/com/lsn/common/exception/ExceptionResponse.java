
package com.lsn.common.exception;

/**
 * @author 000001
 * @ClassName: ExceptionResponse
 * @Description: 异常响应
 * @date 2017年6月5日 下午6:20:21
 */
public class ExceptionResponse {

    //状态码
    private int code;

    private String message;

    public ExceptionResponse() {
        super();
    }

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}

