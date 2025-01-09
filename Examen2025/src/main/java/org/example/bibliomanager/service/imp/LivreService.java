package org.example.bibliomanager.service.imp;

import org.example.bibliomanager.DAO.imp.AuteurDAO;
import org.example.bibliomanager.DAO.imp.LivreDAO;
import org.example.bibliomanager.DTO.LivreDTO;
import org.example.bibliomanager.entity.Livre;
import org.example.bibliomanager.service.ILivreService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivreService implements ILivreService {
    private final LivreDAO LivreDAO;
    private final AuteurDAO auteurDao;

    public LivreService(LivreDAO LivreDAO, AuteurDAO auteurDao) {
        this.LivreDAO = LivreDAO;
        this.auteurDao = auteurDao;
    }

    // CREATE: Create a Livre
    @Override
    public LivreDTO createLivre(LivreDTO livreDto) {
        if (livreDto.getTitre().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livre title cannot be empty");
        else if (livreDto.getGenre().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livre genre cannot be empty");
        Livre livre = mapToEntity(livreDto);
        Livre savedLivre = LivreDAO.save(livre);

        return mapToDTO(savedLivre);
    }

    // READ: Get all the livres
    @Override
    public List<LivreDTO> getAllLivres() {
        List<LivreDTO> livres = new ArrayList<>();

        for (Livre livre : LivreDAO.findAll()) {
            livres.add(mapToDTO(livre));
        }

        return livres;
    }

    // READ: Get one livre by ID
    @Override
    public LivreDTO getLivreById(Long id) {
        Livre livre = LivreDAO.findById(id);
        LivreDTO livreDto = mapToDTO(livre);

        return livreDto;
    }

    // UPDATE: Update a Livre
    @Override
    public LivreDTO updateLivre(Long id, LivreDTO livreDto) {
        Livre existingLivre = LivreDAO.findById(id);

        existingLivre.setTitre(livreDto.getTitre() != null ? livreDto.getTitre() : existingLivre.getTitre());
        existingLivre.setGenre(livreDto.getGenre() != null ? livreDto.getGenre() : existingLivre.getGenre());

        Livre updatedPlayer = LivreDAO.save(existingLivre);

        return mapToDTO(updatedPlayer);
    }

    // DELETE: Delete a Livre
    @Override
    public void deleteLivre(Long id) {
        Livre livre = LivreDAO.findById(id);

        LivreDAO.delete(livre);
    }

    // Map Livre -> LivreDTO
    private LivreDTO mapToDTO(Livre livre) {
        return LivreDTO.builder()
                .id(livre.getId())
                .titre(livre.getTitre())
                .genre(livre.getGenre())
                .auteurId(livre.getAuteur().getId())
                .build();
    }

    // Map LivreDTO -> Livre
    private Livre mapToEntity(LivreDTO livreDto) {
        return Livre.builder()
                .id(livreDto.getId())
                .titre(livreDto.getTitre())
                .genre(livreDto.getGenre())
                .auteur(auteurDao.findById(livreDto.getAuteurId()))
                .build();
    }
}