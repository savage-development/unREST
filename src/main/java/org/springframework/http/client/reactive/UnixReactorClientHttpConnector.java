package org.springframework.http.client.reactive;

import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;
import reactor.netty.NettyOutbound;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientRequest;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class UnixReactorClientHttpConnector extends ReactorClientHttpConnector {

    private final HttpClient httpClient;

    public UnixReactorClientHttpConnector(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri, Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {
        AtomicReference<ReactorClientHttpResponse> responseRef = new AtomicReference<>();
        return this.httpClient
                .request(io.netty.handler.codec.http.HttpMethod.valueOf(method.name()))
                .uri(uri.toString())
                .send((request, outbound) -> requestCallback.apply(adaptRequest(method, uri, request, outbound)))
                .responseConnection((response, connection) -> {
                    responseRef.set(new ReactorClientHttpResponse(response, connection));
                    return Mono.just((ClientHttpResponse) responseRef.get());
                })
                .next()
                .doOnCancel(() -> {
                    ReactorClientHttpResponse response = responseRef.get();
                    if (response != null) {
                        response.releaseAfterCancel(method);
                    }
                });
    }

    private ReactorClientHttpRequest adaptRequest(HttpMethod method, URI uri, HttpClientRequest request,
                                                  NettyOutbound nettyOutbound) {
        return new ReactorClientHttpRequest(method, uri, request, nettyOutbound);
    }

}
