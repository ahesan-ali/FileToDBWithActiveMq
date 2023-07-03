package com.rbc.bbp0.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import java.io.IOException;

@Slf4j
@Component
public class LmsActiveMqErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        var cause = t.getCause();
        if(cause instanceof JsonProcessingException){
            log.error("JsonProcessingException in listener: {}", cause.toString());
        }else if(cause instanceof IOException){
            log.error("IOException in listener: {}", cause.toString());
        }else if(cause instanceof JMSException){
            log.error("JMSException in Listener: {}", cause.toString());
        }else{
            log.error("Exception in listener: {}", t.toString());
        }
    }

}
