package com.hdbank.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdbank.demo.model.Response;
import com.hdbank.demo.utils.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceException extends ResponseEntityExceptionHandler {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JsonService jsonService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders, HttpStatus httpStatus,
            WebRequest webRequest) {

        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());

        // Get all errors
        List<String> exceptionalErrors
                = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, httpStatus);
    }

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<String> handleDemoException(DemoException ex) {
        LOGGER.error("DemoException: {}", ex.getMessage());
        Response response = new Response();
        response.setResultCode(ex.getResultCode());
        response.setResultMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonService.writeValueAsString(response));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        LOGGER.error("Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
    }
}
