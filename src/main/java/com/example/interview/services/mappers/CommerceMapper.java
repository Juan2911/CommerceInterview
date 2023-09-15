package com.example.interview.services.mappers;
import com.example.interview.domain.Domain;
import com.example.interview.entities.EntityObject;


public interface CommerceMapper {
    EntityObject mapToEntityFromDomain(Domain request);
    Domain mapToDomainFromEntity(EntityObject entityObject);
}
