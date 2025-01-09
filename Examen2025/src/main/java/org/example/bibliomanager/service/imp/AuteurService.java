package org.example.bibliomanager.service.imp;

import org.example.bibliomanager.DAO.imp.AuteurDAO;
import org.example.bibliomanager.DAO.imp.LivreDAO;
import org.example.bibliomanager.DTO.AuteurDTO;
import org.example.bibliomanager.DTO.AuteurProfileDTO;
import org.example.bibliomanager.DTO.LivreDTO;
import org.example.bibliomanager.entity.Auteur;
import org.example.bibliomanager.service.IAuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuteurService implements IAuteurService {

    private final AuteurDAO auteurDao;
    private final LivreDAO livreDao;

    @Autowired
    public AuteurService(AuteurDAO auteurDao, LivreDAO livreDao) {
        this.auteurDao = auteurDao;
        this.livreDao = livreDao;
    }

    // CREATE: Create an Auteur
    @Override
    public AuteurDTO createAuteur(AuteurDTO auteurDto) {
        Auteur auteur = mapToEntity(auteurDto);
        Auteur savedAuteur = auteurDao.save(auteur);
        return mapToDTO(savedAuteur);
    }

    @Override
    public List<AuteurDTO> getAllAuteurs() {
        List<AuteurDTO> auteurs = auteurDao.findAll().stream()
                .map(auteur -> mapToDTO(auteur))
                .collect(Collectors.toList());

        return auteurs;
    }

    @Override
    public AuteurProfileDTO getAuteurProfileById(Long id) {
        return mapToProfileDTO(auteurDao.findById(id));
    }

    // Mapper Auteur -> AuteurDTO
    private AuteurDTO mapToDTO(Auteur auteur) {
        return AuteurDTO.builder()
                .id(auteur.getId())
                .nom(auteur.getNom())
                .prenom(auteur.getPrenom())
                .build();
    }

    // Mapper AuteurDTO -> Auteur
    private Auteur mapToEntity(AuteurDTO auteurDto) {
        return Auteur.builder()
                .id(auteurDto.getId())
                .nom(auteurDto.getNom())
                .prenom(auteurDto.getPrenom())
                .build();
    }

    private AuteurProfileDTO mapToProfileDTO(Auteur auteur) {
        AuteurProfileDTO profile = AuteurProfileDTO.builder()
                .id(auteur.getId())
                .nom(auteur.getNom())
                .prenom(auteur.getPrenom())
                .build();

        List<LivreDTO> livrrDtos = livreDao.findByAuteur(auteur).stream()
                .map(livre -> LivreDTO.builder()
                        .id(livre.getId())
                        .titre(livre.getTitre())
                        .genre(livre.getGenre())
                        .auteurId(auteur.getId())
                        .build())
                .collect(Collectors.toList());
        profile.setLivres(livrrDtos);
        return profile;
    }
}