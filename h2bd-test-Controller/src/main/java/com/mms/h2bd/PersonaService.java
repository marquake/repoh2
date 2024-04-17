package com.mms.h2bd;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    public List<Personas> getListaPersonas(){
        return personaRepository.findAll();
    }

    public Optional<Personas> getPersonaById(Long id){
        return personaRepository.findById(id);
    }

    public Personas savePersonas(Personas persona){
        return personaRepository.save(persona);
    }
}
