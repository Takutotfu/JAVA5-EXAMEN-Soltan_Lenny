package org.example.bibliomanager;

import org.example.bibliomanager.DTO.AuteurDTO;
import org.example.bibliomanager.DTO.AuteurProfileDTO;
import org.example.bibliomanager.DTO.LivreDTO;
import org.example.bibliomanager.controller.AuteurController;
import org.example.bibliomanager.service.imp.AuteurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(AuteurController.class)
public class AuteurControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuteurService auteurService;

    private AuteurDTO auteurDto;
    private AuteurProfileDTO auteurProfileDto;
    private List<LivreDTO> livreDtos;

    @BeforeEach
    void setUp() {
        livreDtos = new ArrayList<>();
        livreDtos.add(LivreDTO.builder()
                .auteurId(1L)
                .titre("Test")
                .genre("test")
                .id(1L)
                .build());
        auteurDto = AuteurDTO.builder()
                .id(1L)
                .nom("Doe")
                .prenom("John")
                .build();
        auteurProfileDto = AuteurProfileDTO.builder()
                .id(1L)
                .nom("Doe")
                .prenom("John")
                .livres(livreDtos)
                .build();
    }

    @Test
    void testCreateAuteur() throws Exception {

        when(auteurService.createAuteur(auteurDto)).thenReturn(auteurDto);

        mockMvc.perform(post("/api/auteurs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.prenom").value("John"));
    }

    @Test
    void testGetAuteurProfile() throws Exception {
        when(auteurService.getAuteurProfileById(1L)).thenReturn(auteurProfileDto);

        mockMvc.perform(get("/api/auteurs/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.prenom").value("John"))
                .andExpect(jsonPath("$.livres[0].auteurId").value(1L));
    }

    @Test
    void testGetAllAuteurs() throws Exception {
        List<AuteurDTO> auteurs = Arrays.asList(auteurDto);

        when(auteurService.getAllAuteurs()).thenReturn(auteurs);

        mockMvc.perform(get("/api/auteurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nom").value("Doe"))
                .andExpect(jsonPath("$[0].prenom").value("John"));
    }

}
