package com.example.micronaut;

import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.*;
import org.reactivestreams.*;

import static io.micronaut.http.HttpHeaders.*;

@Client(GithubConfiguration.GITHUB_API_URL)
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
public interface GithubApiClient {

    @Get("/repos/${github.organization}/${github.repo}/releases")
    Publisher<GithubRelease> fetchReleases();
}