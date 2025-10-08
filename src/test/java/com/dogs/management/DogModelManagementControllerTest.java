package com.dogs.management;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dogs.management.controller.DogDataController;
import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.service.DogDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class DogModelManagementControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private DogDataService dogDataService;

    @InjectMocks
    private DogDataController dogDataController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDogs() {
        // Arrange: mock the service
        List<DogModel> mockDogs = List.of(
                new DogModel(2L, "Chloe", "poodle", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-03"), null, "Harrods", "female", "In Service", "", ""),
                new DogModel(3L, "Rea", "corgi", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-03"), null, "Harrods", "male", "In Service", "", "")
        );

        when(dogDataService.getAllDogs(1, 1)).thenReturn(mockDogs);

        // Act: call the controller
        List<DogModel> result = dogDataController.getAllDogs(1, 1);

        // Assert: verify results
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Chloe", result.get(0).getName());

        // Verify service was called once
        verify(dogDataService, times(1)).getAllDogs(1, 1);
    }
    
    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from dog management service")));
    }
}
