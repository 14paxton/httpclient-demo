package com.example.micronaut;

import io.micronaut.context.annotation.*;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.http.filter.*;
import org.reactivestreams.*;

@Filter("/repos/**")
@Requires(property = GithubConfiguration.PREFIX + ".username")
@Requires(property = GithubConfiguration.PREFIX + ".token")
public class GithubFilter implements HttpClientFilter {

    private final GithubConfiguration configuration;

    public GithubFilter(GithubConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.basicAuth(configuration.getUsername(), configuration.getToken()));
    }
}