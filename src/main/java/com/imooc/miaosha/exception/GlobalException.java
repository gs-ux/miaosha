package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
