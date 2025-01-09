package org.example.bibliomanager.controller;

import java.util.List;

import org.example.bibliomanager.DTO.AuteurDTO;
import org.example.bibliomanager.DTO.AuteurProfileDTO;
import org.example.bibliomanager.service.imp.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    @Autowired
    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    // CREATE: Create an auteur
    @PostMapping
    public ResponseEntity<AuteurDTO> createAuteur(@RequestBody AuteurDTO auteurDto) {
        AuteurDTO auteur = auteurService.createAuteur(auteurDto);
        return new ResponseEntity<>(auteur, HttpStatus.CREATED);
    }

    // READ: Get an auteur profile with Livres
    @GetMapping("/{auteurId}")
    public ResponseEntity<AuteurProfileDTO> getAuteurProfile(@PathVariable Long auteurId) {
        AuteurProfileDTO auteurProfile = auteurService.getAuteurProfileById(auteurId);
        return new ResponseEntity<>(auteurProfile, HttpStatus.OK);
    }

    // READ: Get all the auteurs
    @GetMapping
    public ResponseEntity<List<AuteurDTO>> getAllAuteurs() {
        List<AuteurDTO> auteurs = auteurService.getAllAuteurs();
        return new ResponseEntity<>(auteurs, HttpStatus.OK);
    }
}