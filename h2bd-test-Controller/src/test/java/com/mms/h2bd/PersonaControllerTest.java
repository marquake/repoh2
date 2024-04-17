package com.mms.h2bd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonaService personaService;

    @Test
    void metodoGet() throws Exception {
        mockMvc.perform( get("/get") )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
                //.andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE));
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getListaPersonas() throws Exception {
        mockMvc.perform( get("/lista-personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[1].name").value("juan"));
    }

    @Test
    void altaPersonas() {
    }

    @Test
    void modificarPersona() {
    }

    @Test
    void deletePersona() {
    }
}