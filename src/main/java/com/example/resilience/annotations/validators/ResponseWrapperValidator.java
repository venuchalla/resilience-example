package com.example.resilience.annotations.validators;

import com.example.resilience.annotations.LogRequest;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

public class ResponseWrapperValidator implements Validator {

  @Override
  public void validate(ValidationContext ctx, ExecutableElement method) {

    LogRequest log = method.getAnnotation(LogRequest.class);

    if (!log.enforceResponseWrapper()) return;

    TypeMirror responseWrapper =
        ctx.elements().getTypeElement("com.example.resilience.dto.ResponseWrapper").asType();

    TypeMirror returnType = method.getReturnType();

    // Check if return type contains ResponseWrapper (handles ResponseEntity<ResponseWrapper<T>>)
    String returnTypeStr = returnType.toString();
    if (!returnTypeStr.contains("ResponseWrapper")) {
      ctx.error(method, "Method with @LogRequest must return ResponseEntity<ResponseWrapper<...>>");
    }
  }
}
