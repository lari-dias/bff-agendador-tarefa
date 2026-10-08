package com.javanauta.bffagendador.business.dto.in;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}