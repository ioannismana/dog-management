package com.dogs.management.service.implementation;

import com.dogs.management.persistence.DogInfoDAO;
import com.dogs.management.persistence.model.DogModel;
import com.dogs.management.service.DogDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;


@Service
public class DogDataServiceImpl implements DogDataService {

    @Value("#{'${app.allowed.current-status}'.split(',')}")
    private String allowedStatus;

    @Value("#{'${app.allowed.leaving-reason}'.split(',')}")
    private String allowedLeavingReasons;

    @Autowired
    DogInfoDAO dogInfoDAO;


    @Override
    public Page<DogModel> filterDogs(int page, int pageSize, Map<String, String> filters){
        Pageable pageable = PageRequest.of(page, pageSize);
        try {
            String name = filters.getOrDefault("name", "");
            String breed = filters.getOrDefault("breed", "");
            String supplier = filters.getOrDefault("supplier", "");

            Page<DogModel> response = dogInfoDAO.findByNameContainingAndBreedContainingAndSupplierContaining(
                    name, breed, supplier, pageable
            );

            return response;
        } catch (Exception x) {
            return Page.empty(pageable);
        }
    }

    @Override
    public Page<DogModel> getAllDogs(int page, int pageSize){
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return dogInfoDAO.findAll(pageable);
        } catch (Exception x) {
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteDog(Long badgeId) {
        try {
            // Find the record by badgeId
            DogModel dog = dogInfoDAO.findByBadgeId(badgeId);

            // If no record is found return false
            if (dog == null) {
                return false;
            }

            // To delete uncomment the next line
//            Long deleteCount = dogInfoDAO.deleteByBadgeId(badgeId);
            Long deleteCount = 1L;  // fake result without deleting the actual entry

            // If no records were deleted (deleteCount is 0 or null), log a warning
            if (deleteCount == null || deleteCount == 0) {
                return false;
            }

            // If records were deleted return true
            return true;
        } catch (Exception e) {  // Some other error happened
            return false;
        }
    }

    @Override
    public boolean addDog(DogModel dogInfo) {
        boolean isValidDogInfo = validateDogInfo(dogInfo);

        if (!isValidDogInfo) {
            return false;
        }

        try {
            dogInfoDAO.save(dogInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateDogInfo(DogModel dogInfo) {
        String status = dogInfo.getCurrentStatus();
        String leavingReason = dogInfo.getLeavingReason();
        LocalDate leavingDate = dogInfo.getLeavingDate();

        boolean validStatus = allowedStatus.contains(status);

        boolean validLeavingReason = false;
        if (!leavingReason.isBlank()) {
            validLeavingReason = allowedLeavingReasons.contains(leavingReason);
        } else {
            validLeavingReason = leavingDate == null;
        }

        return validStatus && validLeavingReason;
    }

}
