package com.mms.h2bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name="PERSONAS")
public record Personas (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        Long id,
                        String name){ }