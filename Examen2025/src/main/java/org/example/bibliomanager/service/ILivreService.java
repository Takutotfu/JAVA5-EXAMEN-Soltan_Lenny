package org.example.bibliomanager.service;

import org.example.bibliomanager.DTO.LivreDTO;

import java.util.List;

public interface ILivreService {
    LivreDTO createLivre(LivreDTO dto);
    List<LivreDTO> getAllLivres();
    LivreDTO getLivreById(Long id);
    LivreDTO updateLivre(Long id, LivreDTO dto);
    void deleteLivre(Long id);
}
