package org.example.bibliomanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bibliomanager.DTO.LivreDTO;
import org.example.bibliomanager.controller.LivreController;
import org.example.bibliomanager.service.imp.LivreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivreController.class)
public class LivreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivreService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    private LivreDTO livreDto;

    @BeforeEach
    void setUp() {
        livreDto = LivreDTO.builder()
                .id(1L)
                .titre("Test")
                .genre("test")
                .auteurId(1L)
                .build();
    }

    @Test
    void testCreateLivre() throws Exception {
        when(playerService.createLivre(any(LivreDTO.class))).thenReturn(livreDto);

        mockMvc.perform(post("/api/livres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livreDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titre").value("Test"))
                .andExpect(jsonPath("$.genre").value("test"));
    }

    @Test
    void testGetAllLivres() throws Exception {
        List<LivreDTO> livres = Arrays.asList(livreDto);
        when(playerService.getAllLivres()).thenReturn(livres);

        mockMvc.perform(get("/api/livres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testGetLivreById() throws Exception {
        when(playerService.getLivreById(1L)).thenReturn(livreDto);

        mockMvc.perform(get("/api/livres/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titre").value("Test"))
                .andExpect(jsonPath("$.genre").value("test"));
    }

    @Test
    void testUpdateLivre() throws Exception {
        when(playerService.updateLivre(eq(1L), any(LivreDTO.class))).thenReturn(livreDto);

        mockMvc.perform(put("/api/livres/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livreDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testDeleteLivre() throws Exception {
        doNothing().when(playerService).deleteLivre(1L);

        mockMvc.perform(delete("/api/livres/1"))
                .andExpect(status().isNoContent());
    }
}
