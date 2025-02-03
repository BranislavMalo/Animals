package org.jump.soft.animals.core;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.jump.soft.animals.core.controllers.AnimalController;
import org.jump.soft.animals.core.dto.AnimalDto;
import org.jump.soft.animals.core.dto.AnimalWithDetailsDto;
import org.jump.soft.animals.core.enumeration.Gender;
import org.jump.soft.animals.core.services.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AnimalController.class)
@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

    @MockitoBean
    private AnimalService animalService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAnimal() throws Exception {
        long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Scar");
        animalDto.setAge(7);
        animalDto.setBreedId(3);
        animalDto.setGender("MALE");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/animals/add-animal", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isOk());

        verify(animalService, times(1)).addAnimal(eq(animalDto));
    }

    @Test
    void testGetAnimal() throws Exception {
        long animalId = 1L;

        AnimalDto animalDto = new AnimalDto(1, "Mufasa", 15, 1, "MALE");
        when(animalService.getAnimal(animalId)).thenReturn(animalDto);

        // Perform the GET request and check the response
        mockMvc.perform(get("/api/animals/get-animal/{id}", animalId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mufasa"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.breedId").value(1))
                .andExpect(jsonPath("$.gender").value("MALE"));

        // Verify that the getAnimal method of AnimalService was called
        verify(animalService, times(1)).getAnimal(animalId);
    }

    @Test
    void testRemoveAnimal() throws Exception {
        long animalId = 1L;

        mockMvc.perform(delete("/api/animals/remove-animal/{id}", animalId))
                .andExpect(status().isOk());

        verify(animalService, times(1)).removeAnimal(animalId);
    }

    @Test
    void testUpdateAnimal() throws Exception {
        long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setId(animalId);
        animalDto.setName("Fiona");
        animalDto.setAge(13);
        animalDto.setBreedId(5);
        animalDto.setGender("FEMALE");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/animals/update-animal/{id}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isOk());

        verify(animalService, times(1)).updateAnimal(eq(animalDto.getId()), eq(animalDto));
    }

    @Test
    void testGetAnimals() throws Exception {
        List<AnimalDto> animals = Arrays.asList(
                new AnimalDto(1, "Mufasa", 15, 1, "MALE"),
                new AnimalDto(2, "Simba", 10, 2, "MALE")
        );
        when(animalService.getAnimals()).thenReturn(animals);

        mockMvc.perform(get("/api/animals/get-animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mufasa"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Simba"));

        verify(animalService, times(1)).getAnimals();
    }

    @Test
    void testGetAnimalsWithDetails() throws Exception {
        List<AnimalWithDetailsDto> animalsWithDetails = Arrays.asList(
                new AnimalWithDetailsDto(1, "Mufasa", 15, "Afganský chrt", Gender.MALE),
                new AnimalWithDetailsDto(2, "Simba", 10, "Americká akita", Gender.MALE)
        );
        when(animalService.getAnimalsWithDetails()).thenReturn(animalsWithDetails);

        mockMvc.perform(get("/api/animals/get-animals-with-details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mufasa"))
                .andExpect(jsonPath("$[0].breedName").value("Afganský chrt"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Simba"))
                .andExpect(jsonPath("$[1].breedName").value("Americká akita"));

        verify(animalService, times(1)).getAnimalsWithDetails();
    }
}