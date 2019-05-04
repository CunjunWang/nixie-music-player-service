package com.cunjunwang.music.player.exception;

import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.entity.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 通用异常处理类
 * Created by CunjunWang on 2018-12-22.
 */
@Component
@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultData handle(Exception exception) {
        String errMsg = null;
        ResultData resultData = null;
        if (exception instanceof MusicPlayerException) {
            // 通用异常处理方法
            MusicPlayerException e = (MusicPlayerException) exception;
            errMsg = String.format("ErrCode[%s], ErrMsg[%s]", e.getErrCode(), e.getErrMsg());
            resultData = new ResultData<>(ResultData.FAILURE, e.getErrCode(), errMsg, e.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exs = (MethodArgumentNotValidException) exception;
            List<ObjectError> errList = exs.getBindingResult().getAllErrors();
            StringBuffer stringBuffer = new StringBuffer("[数据校验失败]:");
            for (ObjectError objectError : errList) {
                stringBuffer.append(objectError.getDefaultMessage()).append(";");
            }
            resultData = new ResultData<>(ResultData.FAILURE, ErrConstant.INVALID_DATA_FILED, "数据校验失败", stringBuffer);
        } else {
            //未定义的异常
            logger.error("Unexpected Error, 未知异常!", exception);
            resultData = new ResultData<>(ResultData.FAILURE, ErrConstant.UNKNOWN_ERR, "Unexpected Error, 未知异常!", exception.getMessage());
        }
        return resultData;
    }

}
