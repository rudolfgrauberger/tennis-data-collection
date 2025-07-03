package xyz.grauberger.application.interceptor;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ForwardedHeaderUtils;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String X_REQUEST_ID_HEADER_NAME = "X-Request-ID";

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler) {

        String rid = getRequestId(request);
        if (response.containsHeader(X_REQUEST_ID_HEADER_NAME))
            rid = response.getHeader(X_REQUEST_ID_HEADER_NAME);
        else
            response.setHeader(X_REQUEST_ID_HEADER_NAME, rid);

        if (request.getDispatcherType().equals(DispatcherType.REQUEST)) {
            LOG.info("Incoming {}", requestLoggingMessage(request));
        }

        return true;
    }

    @SuppressWarnings("null")
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) {
        if (request.getDispatcherType().equals(DispatcherType.REQUEST))
            LOG.info("Completed {}  with HttpStatus: {}", requestLoggingMessage(request), HttpStatus.valueOf(response.getStatus()).getReasonPhrase());

        MDC.clear();
    }

    private String getRequestId(HttpServletRequest request) {
        String xRequestId = request.getHeader(X_REQUEST_ID_HEADER_NAME);

        if (xRequestId != null && !xRequestId.isEmpty())
            return xRequestId;

        return UUID.randomUUID().toString();
    }

    private String requestLoggingMessage(HttpServletRequest request) {
        final var servletServerHttpRequest = new ServletServerHttpRequest(request);
        HttpHeaders headers = servletServerHttpRequest.getHeaders();
        final var uri = servletServerHttpRequest.getURI();

        String url = ForwardedHeaderUtils.adaptFromForwardedHeaders(uri, headers).build().toUriString();

        return String.format("%s request on %s", request.getMethod(), url);
    }
}
