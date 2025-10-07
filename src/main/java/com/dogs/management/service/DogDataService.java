package com.dogs.management.service;
import com.dogs.management.persistence.model.DogModel;
import java.util.List;


public interface DogDataService {

    List<DogModel> getAllDogs();

    boolean deleteDog(Long badgeId);

    boolean addDog(DogModel dogInfo);

}
