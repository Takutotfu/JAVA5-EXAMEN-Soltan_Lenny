package org.example.bibliomanager.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuteurDTO {
    private Long id;
    private String nom;
    private String prenom;
}
