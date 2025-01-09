package org.example.bibliomanager.DAO;

import java.util.List;

import org.example.bibliomanager.entity.Auteur;;

public interface IAuteurDAO {
    Auteur save(Auteur f);
    Auteur findById(Long id);
    List<Auteur> findAll();
    void delete(Auteur f);
}
