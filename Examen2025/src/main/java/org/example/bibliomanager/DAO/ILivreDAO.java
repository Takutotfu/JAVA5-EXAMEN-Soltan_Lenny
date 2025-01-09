package org.example.bibliomanager.DAO;

import org.example.bibliomanager.entity.Livre;
import org.example.bibliomanager.entity.Auteur;

import java.util.List;

public interface ILivreDAO {
    Livre save(Livre player);
    Livre findById(Long id);
    List<Livre> findAll();
    void delete(Livre player);
    List<Livre> findByAuteur(Auteur auteur);
}
