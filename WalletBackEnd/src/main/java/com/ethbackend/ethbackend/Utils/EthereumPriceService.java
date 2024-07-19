package com.ethbackend.ethbackend.Utils;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EthereumPriceService {

    private final WebClient webClient;

    public EthereumPriceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coingecko.com/api/v3").build();
    }

    public Mono<Double> getEthereumPriceInUsd() {
        return this.webClient.get()
                .uri("/simple/price?ids=ethereum&vs_currencies=usd")
                .retrieve()
                .bodyToMono(CoinGeckoResponse.class)
                .map(response -> response.ethereum.usd);
    }

    static class CoinGeckoResponse {
        public Ethereum ethereum;

        static class Ethereum {
            public double usd;
        }
    }
}
