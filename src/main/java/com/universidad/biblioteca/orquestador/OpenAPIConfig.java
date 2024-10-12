package com.universidad.biblioteca.orquestador;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@OpenAPIDefinition(
        info = @Info(title = "API Orquestador", version = "1.0",
                description = "Documentación para el API orquestador")
)
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.addServersItem(new Server().url("http://inventario:8080")); // URL base del microservicio de inventario
            openApi.addServersItem(new Server().url("http://prestamo:8004")); // URL base del microservicio de préstamo
           openApi.addServersItem(new Server().url("http://gestionusuario:5001")); // URL base del microservicio de gestión de usuarios
            openApi.addServersItem(new Server().url("http://orquestador:8082/api/biblioteca"));
        };
    }


    @Bean
    public GroupedOpenApi inventarioApi() {
        return GroupedOpenApi.builder()
                .group("Inventario API")
                .pathsToMatch("/api/inventario/**")
                .build();
    }

    @Bean
    public GroupedOpenApi prestamosApi() {
        return GroupedOpenApi.builder()
                .group("Prestamos API")
                .pathsToMatch("/api/prestamos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi usuariosApi() {
        return GroupedOpenApi.builder()
                .group("Usuarios API")
                .pathsToMatch("/api/User/**")
                .build();
    }

    @Bean
    public GroupedOpenApi combinedApi() {
        return GroupedOpenApi.builder()
                .group("Combined API")
                .pathsToMatch("/api/biblioteca/api-docs/combined")
                .build();
    }
}
