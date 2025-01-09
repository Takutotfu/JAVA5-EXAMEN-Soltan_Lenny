package org.example.bibliomanager.controller;

import org.example.bibliomanager.DTO.LivreDTO;
import org.example.bibliomanager.service.imp.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    @Autowired
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // CREATE: Create a Livre
    @PostMapping
    public ResponseEntity<LivreDTO> createLivre(@RequestBody LivreDTO livreDto) {
        LivreDTO createdLivre = livreService.createLivre(livreDto);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // READ: Get all the Livres
    @GetMapping
    public ResponseEntity<List<LivreDTO>> getAllLivres() {
        List<LivreDTO> livres = livreService.getAllLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    // READ: Get Livre by an id
    @GetMapping("/{id}")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable Long id) {
        LivreDTO livre = livreService.getLivreById(id);
        return ResponseEntity.ok(livre);
    }
    //////////////////////////////////////////////////////////////////////////////////////

    // UPDATE: Update a Livre
    @PutMapping("/{id}")
    public ResponseEntity<LivreDTO> updateLivre(@PathVariable Long id, @RequestBody LivreDTO livreDTO) {
        LivreDTO updatedLivre = livreService.updateLivre(id, livreDTO);
        return new ResponseEntity<>(updatedLivre, HttpStatus.OK);
    }

    // DELETE: Delete a Livre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}