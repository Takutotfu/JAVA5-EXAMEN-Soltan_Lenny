package org.example.bibliomanager.service;

import java.util.List;

import org.example.bibliomanager.DTO.AuteurDTO;
import org.example.bibliomanager.DTO.AuteurProfileDTO;

public interface IAuteurService {
    AuteurDTO createAuteur(AuteurDTO dto);

    List<AuteurDTO> getAllAuteurs();

    AuteurProfileDTO getAuteurProfileById(Long auteurId);
}
