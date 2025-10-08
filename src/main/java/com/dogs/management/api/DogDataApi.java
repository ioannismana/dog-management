package com.dogs.management.api;
import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.persistence.model.response.DogServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

public interface DogDataApi {

    @GetMapping(value = "/filterDogs", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets dogs filtered by: name, breed, supplier")
    public Page<DogModel> filterDogs(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                     @RequestParam(value = "filter", required = false) String filterStr);

    @GetMapping(value = "/dogs", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Gets all the dogs in database")
    public Page<DogModel> getAllDogs(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize);

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletes a dog from the database by badgeId")
    public DogServiceResponse deleteDog(@RequestParam(value = "badgeId", required = true) Long badgeId);

    @PostMapping(path = "/addDog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a dog in the database")
    DogModel addDog(@RequestBody DogModel dogInfo);

}
