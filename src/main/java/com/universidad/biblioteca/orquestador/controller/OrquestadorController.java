package com.universidad.biblioteca.orquestador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/biblioteca")
public class OrquestadorController {

    @Autowired
    private RestTemplate restTemplate;

    // Invoca al microservicio de inventario
    @GetMapping("/inventario")
    public ResponseEntity<String> obtenerInventario() {
        String inventarioUrl = "http://localhost:8080/api/inventario"; // URL del microservicio de inventario
        ResponseEntity<String> response = restTemplate.getForEntity(inventarioUrl, String.class);
        return ResponseEntity.ok("Respuesta del microservicio de inventario: " + response.getBody());
    }

    // Invoca al microservicio de préstamos
    @GetMapping("/prestamos")
    public ResponseEntity<String> obtenerPrestamos() {
        String prestamosUrl = "http://localhost:8081/api/prestamos"; // URL del microservicio de préstamos
        ResponseEntity<String> response = restTemplate.getForEntity(prestamosUrl, String.class);
        return ResponseEntity.ok("Respuesta del microservicio de préstamos: " + response.getBody());
    }
}
