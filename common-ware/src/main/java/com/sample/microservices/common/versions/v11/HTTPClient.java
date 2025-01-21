package com.sample.microservices.common.versions.v11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
    Java 11 includes a new HTTP client API that provides a more modern and efficient way to send HTTP requests and receive responses.
    The new HTTP client API supports both HTTP/1.1 and HTTP/2 protocols and includes features like HTTP/2 push, server push, and WebSocket.
    The new API is also asynchronous and non-blocking, making it more scalable and performant than the previous HttpURLConnection API.

 */
public class HTTPClient {

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://xyz.com/posts/1"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

}
