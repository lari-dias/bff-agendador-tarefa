package com.javanauta.bffagendador.business.dto.out;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTOResponse {

    private Long id;
    private String numero;
    private String ddd;
}