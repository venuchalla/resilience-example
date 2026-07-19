package com.example.resilience.annotations.validators;

import com.example.resilience.annotations.LogRequest;
import javax.lang.model.element.ExecutableElement;

public class ResponseEntityValidator implements Validator {

  @Override
  public void validate(ValidationContext ctx, ExecutableElement method) {

    LogRequest log = method.getAnnotation(LogRequest.class);

    String returnType = method.getReturnType().toString();

    // Must return ResponseEntity
    if (!returnType.startsWith("org.springframework.http.ResponseEntity")) {
      ctx.error(method, "Method with @LogRequest must return ResponseEntity<ResponseWrapper<...>>");
    }
  }
}
