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
        // Cambia "localhost" por el nombre del servicio definido en docker-compose (inventario)
        String inventarioUrl = "http://inventario:8080/api/inventario"; 
        ResponseEntity<String> response = restTemplate.getForEntity(inventarioUrl, String.class);
        return ResponseEntity.ok("Respuesta del microservicio de inventario: " + response.getBody());
    }

    // Invoca al microservicio de préstamos
    @GetMapping("/prestamos")
    public ResponseEntity<String> obtenerPrestamos() {
        // Cambia "localhost" por el nombre del servicio definido en docker-compose (prestamo-service)
        String prestamosUrl = "http://prestamo-service:8004/api/prestamos";
        ResponseEntity<String> response = restTemplate.getForEntity(prestamosUrl, String.class);
        return ResponseEntity.ok("Respuesta del microservicio de préstamos: " + response.getBody());
    }

    // Invoca al microservicio de gestión de usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<String> obtenerUsuarios() {
        // Añade la invocación al microservicio de gestión de usuarios (gestionusuario)
        String usuariosUrl = "http://gestionusuario:5000/api/usuarios"; 
        ResponseEntity<String> response = restTemplate.getForEntity(usuariosUrl, String.class);
        return ResponseEntity.ok("Respuesta del microservicio de usuarios: " + response.getBody());
    }
}
