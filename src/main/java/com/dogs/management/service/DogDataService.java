package com.dogs.management.service;
import com.dogs.management.dto.DogDTO;
import com.dogs.management.persistence.model.DogModel;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface DogDataService {

    Page<DogDTO> filterDogs(int page, int pageSize, Map<String, String> filters);

    Page<DogDTO> getAllDogs(int page, int pageSize);

    boolean deleteDog(Long badgeId);

    boolean addDog(DogModel dogInfo);

}
