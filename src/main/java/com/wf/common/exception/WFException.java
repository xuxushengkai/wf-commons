package com.wf.common.exception;

import com.wf.common.constants.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WFException extends RuntimeException{

    private Integer code;

    private ResultCode resultCode;

    public WFException() {
        super();
    }

    public WFException(String message) {
        super(message);
    }

    public WFException(String message, Throwable cause) {
        super(message, cause);
    }

    public WFException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public WFException(ResultCode resultCode) {
       this.resultCode = resultCode;
    }

}
