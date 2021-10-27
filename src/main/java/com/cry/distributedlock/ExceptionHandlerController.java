package com.cry.distributedlock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 */
@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object exceptionHandler(RuntimeException e){

        Map<String,Object> result=new HashMap<>(  );
        result.put( "status","error" );
        result.put( "message",e.getMessage() );
        return result;
    }


}
