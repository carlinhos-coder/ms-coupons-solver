package co.com.bancolombia.service;

import reactor.core.publisher.Mono;

public interface ItemsService {
    Mono<Float> getItemPrice(String id);
}
