package com.ankit.webflux.router.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request,
      ErrorAttributeOptions options) {
    Map<String, Object> errorAttributes = new HashMap<>();

    Throwable throwable = getError(request);
    errorAttributes.put("Message", throwable.getMessage());
    errorAttributes.put("Endpoint url", request.path());
    return errorAttributes;
  }

}
