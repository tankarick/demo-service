package com.hdbank.demo.config;

import com.hdbank.demo.utils.JsonUtils;
import com.jayway.jsonpath.DocumentContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ResponseFilter implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes == null) {
                log.info("Response: {}", JsonUtils.writeValueAsString(body));
                return body;
            }
            String bodyRequest = (String) servletRequestAttributes.getAttribute("bodyRequest", RequestAttributes.SCOPE_REQUEST);
            if (bodyRequest == null || bodyRequest.isEmpty()) {
                log.info("Response: {}", JsonUtils.writeValueAsString(body));
                return body;
            }
            DocumentContext dcResponse = JsonUtils.toJson(body);
            dcResponse.set("$.responseId", getRequestId(bodyRequest));
            dcResponse.set("$.responseTime", System.currentTimeMillis());
            log.info("Response: {}", dcResponse.jsonString());
            return dcResponse.json();
        } catch (Exception ex) {
            log.error("Exception when set response: ", ex);
            return body;
        }
    }

    private String getRequestId(String bodyRequest) {
        return Optional.ofNullable(JsonUtils.read(bodyRequest, "$.request.requestId", String.class)).orElse(UUID.randomUUID().toString());
    }
}
