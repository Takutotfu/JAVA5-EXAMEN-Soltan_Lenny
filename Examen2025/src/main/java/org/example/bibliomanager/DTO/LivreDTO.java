package org.example.bibliomanager.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreDTO {
    private Long id;
    private String titre;
    private String genre;
    private Long auteurId;
}