package com.dogs.management.controller;

import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.persistence.model.response.DogServiceResponse;
import com.dogs.management.service.DogDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dogs.management.api.DogDataApi;

import java.util.List;

@RestController
public class DogDataController implements DogDataApi {

    @Autowired
    DogDataService dogDataService;

    @Override
    public List<DogModel> getAllDogs(int page, int pageSize) {
        return dogDataService.getAllDogs(page, pageSize);
    }

    @Override
    public DogServiceResponse deleteDog(Long badgeId) {
        DogServiceResponse response = new DogServiceResponse();

        boolean deleted = dogDataService.deleteDog(badgeId);
        if (deleted) {
            response.setMessage("Data deleted successfully");
        } else {
            response.setMessage(String.format("Delete failed for badgeId %8d", badgeId));
        }

        response.setStatus(deleted);
        return response;
    }

    @Override
    public DogModel addDog(DogModel dogInfo){
        try {
            dogDataService.addDog(dogInfo);
            return dogInfo;
        } catch (Exception x) {
            return null;
        }
    }

    @GetMapping("/")
    public String index() {
        return "Hello from dog management service";
    }

}
