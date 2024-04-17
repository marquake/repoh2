package com.mms.h2bd;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    PersonaService personaService;

    @Test
    void getListaPersonas() {
        // Arrange
        Personas persona1 = Personas.builder().id(1L).name("juan").build();
        Personas persona2 = Personas.builder().id(2L).name("pepe").build();
        Personas persona3 = Personas.builder().id(3L).name("luis").build();

        // Act
        when(personaRepository.findAll())
                .thenReturn(List.of(persona1, persona2, persona3));

        List<Personas> listaPersonas = personaService.getListaPersonas();

        // Assert
        assertThat(listaPersonas.get(0)).isNotNull();
        assertThat(listaPersonas.get(1).getName()).isEqualTo("pepe");
        assertThat(listaPersonas.get(2).getName()).isEqualTo("luis");

        // estos dan error
        //assertThat(listaPersonas.getFirst()).isNotNull();
        //assertThat(listaPersonas.getFirst().getName()).isEqualTo("juan");
        //assertThat(listaPersonas.getLast().getName()).isEqualTo("luis");
    }

    @Test
    void getPersonaById() {

        when(personaRepository.findById(1L))
                .thenReturn(
                        Optional.of(Personas.builder().id(1L).name("fernando").build()));

        Optional<Personas> personaRecuperada = personaService.getPersonaById(1L);

        // Assert
        assertThat(personaRecuperada.get()).isNotNull();
        assertThat(personaRecuperada.get().getName()).isEqualTo("fernando");

    }

    @Test
    void personaService_save_persona(){

        // Arrange
        Personas persona1 = Personas.builder().id(8L).name("manuel").build();

        // Act

        // Este no tiene sentido, quiero probarlo para cualquier instancia de la clase
        // Personas, no solo para una. Usando any, me vale para cualquiera.
        //
        //when(personaRepository.save( Mockito.mock(Personas.class)))
        //        .thenReturn(persona1);

        when(personaRepository.save( Mockito.any(Personas.class)))
                .thenReturn(persona1);

        Personas personaSaved = personaService.savePersonas(Mockito.mock(Personas.class));

        // Assert
        assertThat(personaSaved).isNotNull();
        assertThat(personaSaved.getName()).isEqualTo("manuel");


    }
}