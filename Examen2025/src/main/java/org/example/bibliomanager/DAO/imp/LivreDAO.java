package org.example.bibliomanager.DAO.imp;

import org.example.bibliomanager.DAO.ILivreDAO;
import org.example.bibliomanager.entity.Livre;
import org.example.bibliomanager.entity.Auteur;
import org.example.bibliomanager.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class LivreDAO implements ILivreDAO {

    LivreRepository livreRepository;

    @Autowired
    public LivreDAO(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void delete(Livre livre) {
        livreRepository.delete(livre);
    }

    @Override
    public Livre findById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id " + id));
    }

    @Override
    public List<Livre> findByAuteur(Auteur auteur) {
        return livreRepository.findByAuteur(auteur);
    }

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }
}
