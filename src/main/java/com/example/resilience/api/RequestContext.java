package com.example.resilience.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "RequestContextBuilder", builderMethodName = "requestContextBuilder")
public class RequestContext {
  private final String messageId;
  private final String sessionId;
  private final String customerIpAddress;
}
