package com.dogs.management.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dog-info")
public class DogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    @JsonProperty("badge_id")
    private Long badgeId;

    @Basic
    @Column(name="name")
    @JsonProperty("name")
    private String name;

    @Basic
    @Column(name="breed")
    @JsonProperty("breed")
    private String breed;

    @Basic
    @Column(name="birth_date",columnDefinition = "DATE")
    @JsonProperty("birth_date")
    private LocalDate  birthDate;

    @Basic
    @Column(name="acquisition_date",columnDefinition = "DATE")
    @JsonProperty("acquisition_date")
    private LocalDate  acquisitionDate;

    @Basic
    @Column(name="leaving_date",columnDefinition = "DATE")
    @JsonProperty("leaving_date")
    private LocalDate leavingDate;

    @Basic
    @Column(name="supplier")
    @JsonProperty("supplier")
    private String supplier;

    @Basic
    @Column(name="gender")
    @JsonProperty("gender")
    private String gender;

    @Basic
    @Column(name="current_status")
    @JsonProperty("current_status")
    private String currentStatus;

    @Basic
    @Column(name="leaving_reason")
    @JsonProperty("leaving_reason")
    private String leavingReason;

    @Basic
    @Column(name="kennelling_characteristic")
    @JsonProperty("kennelling_characteristic")
    private String kennellingCharacteristic;

    public DogModel(Long badgeId, String name, String breed, LocalDate birthDate, LocalDate acquisitionDate, LocalDate leavingDate, String supplier, String gender, String currentStatus, String leavingReason, String kennellingCharacteristic) {
        this.badgeId = badgeId;
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.acquisitionDate = acquisitionDate;
        this.leavingDate = leavingDate;
        this.supplier = supplier;
        this.gender = gender;
        this.currentStatus = currentStatus;
        this.leavingReason = leavingReason;
        this.kennellingCharacteristic = kennellingCharacteristic;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getLeavingReason() {
        return leavingReason;
    }

    public void setLeavingReason(String leavingReason) {
        this.leavingReason = leavingReason;
    }

    public String getKennellingCharacteristic() {
        return kennellingCharacteristic;
    }

    public void setKennellingCharacteristic(String kennellingCharacteristic) {
        this.kennellingCharacteristic = kennellingCharacteristic;
    }
}

