package org.example.bibliomanager.repository;

import org.example.bibliomanager.entity.Livre;
import org.example.bibliomanager.entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByAuteur(Auteur auteur);
}
