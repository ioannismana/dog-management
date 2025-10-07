package com.dogs.management.persistence.model.mapper;

import com.dogs.management.dto.DogDTO;
import com.dogs.management.persistence.model.DogModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DogMapper {

    DogMapper INSTANCE = Mappers.getMapper( DogMapper.class );
    DogDTO dogModelToDogDTO(DogModel dog);
}
