package ru.yakimov.persistence.entities.utils;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class PersistableEntity {

    @Id
    private UUID id;

}
