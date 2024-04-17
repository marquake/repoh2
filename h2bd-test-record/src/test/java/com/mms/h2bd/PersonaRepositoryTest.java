package com.mms.h2bd;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonaRepositoryTest {

    // Pruebas de integración
    @Autowired
    private PersonaRepository personaRepositoryAutowired;

    // Pruebas de lógica de clase
    @Mock
    private PersonaRepository personaRepositoryMock;

    @Test
    public void test_persona_saved_ok_autowired(){
        // Arrange
        Personas persona1 = new Personas(3L, "pepito");
        // Act
        Personas personaSaved =  personaRepositoryAutowired.save(persona1);
        // Assert
        assertEquals(personaSaved.name(), persona1.name());
        assertNotNull(personaSaved);
        assertNotNull(personaSaved.id());
    }

    @Test
    public void test_persona_saved_ok_mock(){
        // Arrange
        Personas persona1 = new Personas(3L, "pepito");
        // Act
        //Personas personaSaved =  personaRepositoryAutowired.save(persona1);
        when(personaRepositoryMock.save(persona1))
                .thenReturn(persona1);

        Personas personaSaved =  personaRepositoryMock.save(persona1);

        // Assert
        assertEquals(personaSaved.name(), persona1.name());
        assertNotNull(personaSaved);
        assertNotNull(personaSaved.id());
    }

    @Test
    public void test_persona_saved_pepito(){
        // Arrange
        Personas persona1 = new Personas(3L, "pepito");
        // Act
        Personas personaSaved =  personaRepositoryAutowired.save(persona1);
        // Assert
        assertEquals(personaSaved.name(), "pepito");
    }

    @Test
    public void testFindAll() {

        // Arrange
        Personas persona1 = new Personas(1L, "pepito");
        Personas persona2 = new Personas(2L, "juan");
        Personas persona3 = new Personas(3L, "luis");

        Personas personaB1 = Personas.builder().id(4L).name("ron").build();
        Personas personaB2 = Personas.builder().id(5L).name("lucas").build();
        Personas personaB3 = Personas.builder().id(6L).name("fer").build();

        // Act
        when(personaRepositoryMock.findAll())
                .thenReturn(Arrays.asList(persona1, persona2, persona3));

        List<Personas> listaPersonas = personaRepositoryMock.findAll();

        // Assert
        assertEquals(listaPersonas.get(1).name(), "juan" );
    }

    @Test
    public void testFindAllAutowired(){
        // Este test lo he hecho para probar si estoy recuperando algún dato de base de datos
        // o se levanta y se tira la base de datos en el momento

        List<Personas> listaPersonas = personaRepositoryAutowired.findAll();
        System.out.println("listaPersonas.size(): " + listaPersonas.size());

        listaPersonas.stream().forEach(System.out::println);
        assertEquals(true, true);
    }


    @Test
    public void testFindById() {
        // Arrange
        Personas persona1 = new Personas(1L, "xxxx");
        Personas persona2 = new Personas(2L, "yyyy");
        Personas persona3 = new Personas(3L, "zzzz");

        when(personaRepositoryMock.findById(1L))
                .thenReturn(Optional.of(persona1))
                .thenReturn(Optional.of(persona2))
                .thenReturn(Optional.of(persona3));

        // Act
        Optional<Personas> persFind1 = personaRepositoryMock.findById(1L);
        Optional<Personas> persFind2 = personaRepositoryMock.findById(1L);
        Optional<Personas> persFind3 = personaRepositoryMock.findById(1L);

        // Assert
        assertEquals(persFind1.get().name(), "xxxx");
        assertEquals(persFind2.get().name(), "yyyy");
        assertEquals(persFind3.get().name(), "zzzz");
    }

}