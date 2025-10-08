package com.dogs.management.service;
import com.dogs.management.persistence.model.DogModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface DogDataService {

    List<DogModel> getAllDogs(int page, int pageSize);

    boolean deleteDog(Long badgeId);

    boolean addDog(DogModel dogInfo);

}
