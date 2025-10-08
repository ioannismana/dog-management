package com.dogs.management.persistence;
import com.dogs.management.persistence.model.DogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogInfoDAO extends JpaRepository<DogModel, String> {
    Page<DogModel> findByNameContainingAndBreedContainingAndSupplierContaining(
            String name, String Breed, String supplier, Pageable pageable
    );

    List<DogModel> findAll();

    DogModel findByBadgeId(Long badgeId);

    Long deleteByBadgeId(@Param("badgeId") Long badgeId);
}
