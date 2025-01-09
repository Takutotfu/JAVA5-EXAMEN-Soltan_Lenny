package org.example.bibliomanager.DAO.imp;

import org.example.bibliomanager.DAO.IAuteurDAO;
import org.example.bibliomanager.entity.Auteur;
import org.example.bibliomanager.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuteurDAO implements IAuteurDAO {

    AuteurRepository auteurRepository;

    @Autowired
    public AuteurDAO(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Auteur save(Auteur a) {return auteurRepository.save(a);}

    @Override
    public Auteur findById(Long id) {
        return auteurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id " + id));
    }

    @Override
    public List<Auteur> findAll() {return auteurRepository.findAll();}

    @Override
    public void delete(Auteur f) {auteurRepository.delete(f);}
}
