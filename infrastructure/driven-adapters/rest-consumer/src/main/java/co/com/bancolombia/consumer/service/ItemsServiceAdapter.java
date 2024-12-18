package co.com.bancolombia.consumer.service;

import co.com.bancolombia.service.ItemsService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ItemsServiceAdapter implements ItemsService {
    @Value("${adapter.restconsumer.items.host}")
    String baseHost;
    @Value("${adapter.restconsumer.items.uri}")
    String baseUriAuthenticate;

    @Override
    public Mono<Float> getItemPrice(String id) {
        log.info("ENTER TO ItemsServiceAdapter :: getItemPrice {} ", id);
        return WebClient.create(baseHost)
                .get()
                .uri(baseUriAuthenticate + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("price").floatValue())
                .doOnError(error -> log.error("Error fetching item price", error));
    }
}
