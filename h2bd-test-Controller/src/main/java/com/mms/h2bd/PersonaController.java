package com.mms.h2bd;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonaController {

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getMensaje(){
        return ResponseEntity.ok().body("get");
    }

    @GetMapping(value = "lista-personas", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Personas>> getListaPersonas(){
        Personas persona1 = Personas.builder().id(1L).name("luis").build();
        Personas persona2 = Personas.builder().id(2L).name("juan").build();
        Personas persona3 = Personas.builder().id(3L).name("pepe").build();

        return ResponseEntity.ok().body(List.of(persona1, persona2, persona3));
    }

    @PostMapping(value = "post/{nombre}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String altaPersonas(@PathVariable String nombre){
        System.out.println("nombre: "+nombre);
        return "post";
    }

    @PutMapping("put")
    public String modificarPersona(){
        return "put";
    }

    @DeleteMapping("delete")
    public String deletePersona(){
        return "delete";
    }

}