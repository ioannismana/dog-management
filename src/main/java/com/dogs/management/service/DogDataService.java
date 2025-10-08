package com.dogs.management.service;
import com.dogs.management.persistence.model.DogModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface DogDataService {

    Page<DogModel> filterDogs(int page, int pageSize, Map<String, String> filters);

    Page<DogModel> getAllDogs(int page, int pageSize);

    boolean deleteDog(Long badgeId);

    boolean addDog(DogModel dogInfo);

}
