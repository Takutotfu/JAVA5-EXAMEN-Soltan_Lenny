package org.example.bibliomanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titre;

    @Column(nullable = false, unique = true, length = 50)
    private String genre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Auteur auteur;
}