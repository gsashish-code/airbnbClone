package com.ashish.airbnbclone.advice;

import com.ashish.airbnbclone.dto.PageResponse;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public @Nullable Object beforeBodyWrite(
      @Nullable Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    // these routes are allowed we can ignore this and not make it go through json
    List<String> allowedRoutes = List.of("actuator", "v3/api-docs");

    boolean isAllowedRoute =
        allowedRoutes.stream().anyMatch(route -> request.getURI().getPath().contains(route));
    if (body instanceof ApiResponse<?> || isAllowedRoute) {
      return body;
    }

    if (body instanceof Page<?> page) {
      return new PageResponse<>(
          page.getContent(),
          page.getNumber() + 1,
          page.getSize(),
          page.getTotalElements(),
          page.getTotalPages(),
          page.hasNext());
    }

    return new ApiResponse<>(body);
  }
}
