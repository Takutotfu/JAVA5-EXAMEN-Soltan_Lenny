package org.example.bibliomanager.DTO;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuteurProfileDTO {
    private Long id;
    private String nom;
    private String prenom;
    private List<LivreDTO> livres;
}
