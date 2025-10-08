package com.dogs.management;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dogs.management.controller.DogDataController;
import com.dogs.management.dto.DogDTO;
import com.dogs.management.persistence.DogInfoDAO;
import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.service.DogDataService;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.dogs.management.persistence.model.mapper.DogMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class DogModelManagementControllerTest {

    private final DogMapper dogMapper;

    @Autowired
    public DogModelManagementControllerTest(DogMapper dogMapper) {
        this.dogMapper = dogMapper;
    }

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

        // convert to DTOs
        List<DogDTO> mockDogDTOs = mockDogs.stream()
                .map(dogMapper::dogModelToDogDTO)  // or create manually if mapper is not injected
                .toList();

        Pageable pageable = PageRequest.of(1, 1);
        Page<DogDTO> pagedDogs = new PageImpl<>(mockDogDTOs, pageable, 2);  // totalElements = 2

        when(dogDataService.getAllDogs(1, 1)).thenReturn(pagedDogs);

        // Act: call the controller
        Page<DogDTO> result = dogDataController.getDogs(1, 1, "");

        // Assert: verify results
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());  // total number of dogs
        assertEquals(2, result.getContent().size()); // number of dogs in this page

        assertEquals("Chloe", result.getContent().get(0).getName());

        // Verify service was called once
        verify(dogDataService, times(1)).getAllDogs(1, 1);
    }

    @Test
    void testGetDogs() {
        // Arrange: mock the service
        List<DogModel> mockDogs = List.of(
                new DogModel(2L, "Chloe", "poodle", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-03"), null, "Harrods", "female", "In Service", "", ""),
                new DogModel(3L, "Rea", "corgi", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-03"), null, "Harrods", "male", "In Service", "", "")
        );

        // convert to DTOs
        List<DogDTO> mockDogDTOs = mockDogs.stream()
                .map(dogMapper::dogModelToDogDTO)  // or create manually if mapper is not injected
                .toList();

        String filterStr = "filter=name:Chloe,breed:,supplier:Harrods";
        Map<String, String> filters = Splitter.on( "," ).withKeyValueSeparator( ':' ).split( filterStr );

        Pageable pageable = PageRequest.of(0, 1);
        Page<DogDTO> pagedDogs = new PageImpl<>(mockDogDTOs, pageable, 1);

        when(dogDataService.filterDogs(0, 10, filters)).thenReturn(pagedDogs);

        // Act: call the controller
        Page<DogDTO> result = dogDataController.getDogs(0, 10, filterStr);

        // Assert: verify results
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());  // total number of dogs
        assertEquals(2, result.getContent().size()); // number of dogs in this page

        assertEquals("Chloe", result.getContent().get(0).getName());

    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from dog management service")));
    }
}
