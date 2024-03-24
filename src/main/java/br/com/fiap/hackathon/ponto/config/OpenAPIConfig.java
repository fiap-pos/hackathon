package br.com.fiap.hackathon.ponto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${hackathon.openapi.dev-url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        var devServer = new Server()
                .url(url)
                .description("URL do ambiente de desenvolvimento");

        var info = new Info()
                .title("API de gerenciamento de registro de Ponto")
                .version("1.0")
                .description("Esta API expõe endpoints para gerenciar o sistema de registro de pontos");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
