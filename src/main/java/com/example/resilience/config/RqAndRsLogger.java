package com.example.resilience.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class RqAndRsLogger implements Filter {
    private static final String TRACE_ID = "traceId";
    private  final Logger logger = LoggerFactory.getLogger(RqAndRsLogger.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        if (wrappedRequest.getRequestURI().startsWith("/codedemo/actuator") ||
                wrappedRequest.getRequestURI().startsWith("/codedemo/swagger") ||
                wrappedRequest.getRequestURI().startsWith("/codedemo/api-docs")) {
            ///codedemo/api-docs
            filterChain.doFilter(servletRequest, servletResponse); return;
        }
        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put(TRACE_ID, traceId);
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            String body = new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
            String rbody = new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);
            logger.info("Request uri : "+ wrappedRequest.getRequestURI());
            //logger.info("Request message :"+body);
            //logger.info("response :"+rbody);
            wrappedResponse.copyBodyToResponse(); // required
            MDC.remove(TRACE_ID);
        }
    }
}
