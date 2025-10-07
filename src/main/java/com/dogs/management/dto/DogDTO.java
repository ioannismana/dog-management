package com.dogs.management.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class DogDTO implements Serializable{

    @Serial
    private static final long serialVersionUID = -2242941408901859533L;

    private Long badgeId;
    private String name;
    private String breed;
    private LocalDateTime birthDate;
    private LocalDateTime acquisitionDate;
    private String gender;

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDateTime acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
