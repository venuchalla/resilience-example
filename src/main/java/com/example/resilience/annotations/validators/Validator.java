package com.example.resilience.annotations.validators;

import javax.lang.model.element.ExecutableElement;

public interface Validator {

  void validate(ValidationContext context, ExecutableElement method);
}
