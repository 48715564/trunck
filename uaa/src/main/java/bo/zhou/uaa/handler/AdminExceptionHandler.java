package bo.zhou.uaa.handler;

import bo.zhou.common.vo.ErrorCode;
import bo.zhou.common.vo.Result;
import bo.zhou.uaa.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class AdminExceptionHandler {

    /**
     * @author: zhoubo
     * @description: 系统异常捕获处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result javaExceptionHandler(Exception ex) {
        log.error("捕获到Exception异常", ex);
        //异常日志入库
        return Result.failure(ErrorCode.BAD_REQUEST, ex.getMessage());
    }

    /**
     * @author: zhoubo
     * @description: 自定义异常捕获处理
     */
    @ResponseBody
    @ExceptionHandler(value = MessageException.class)//MessageException是自定义的一个异常
    public Result messageCenterExceptionHandler(MessageException ex) {
        log.error("捕获到MessageCenterException异常", ex.getException());
        //异常日志入库
        return Result.failure(ErrorCode.BAD_REQUEST, ex.getMessage());
    }
}