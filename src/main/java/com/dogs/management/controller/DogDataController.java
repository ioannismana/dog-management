package com.dogs.management.controller;

import com.dogs.management.dto.DogDTO;
import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.persistence.model.response.DogServiceResponse;
import com.dogs.management.service.DogDataService;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dogs.management.api.DogDataApi;

import java.util.Map;

@RestController
public class DogDataController implements DogDataApi {

    @Autowired
    DogDataService dogDataService;

    @Override
    public Page<DogDTO> getDogs(int page, int pageSize, String filterStr) {
        if (filterStr == null || filterStr.isEmpty()) {
            return dogDataService.getAllDogs(page, pageSize);
        } else {
            // extract the filters from filterStr
            Map<String, String> filters = Splitter.on( "," ).withKeyValueSeparator( ':' ).split( filterStr );
            return dogDataService.filterDogs(page, pageSize, filters);
        }
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
