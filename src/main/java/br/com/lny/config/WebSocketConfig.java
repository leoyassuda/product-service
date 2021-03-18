package br.com.lny.config;

import br.com.lny.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    private String from(Product product) {
        return this.objectMapper.writeValueAsString(product);
    }

    @Bean
    WebSocketHandler webSocketHandler(Flux<Product> productFlux) {
        return new WebSocketHandler() {
            @Override
            public Mono<Void> handle(WebSocketSession webSocketSession) {

                var map = productFlux
                        .map(product -> from(product))
                        .map(webSocketSession::textMessage);

                return webSocketSession.send(map);
            }
        };
    }

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler productWebSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/products", productWebSocketHandler), 10);
    }

}
