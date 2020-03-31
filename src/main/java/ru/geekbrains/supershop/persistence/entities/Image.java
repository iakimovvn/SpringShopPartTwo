package ru.geekbrains.supershop.persistence.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ru.geekbrains.supershop.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Entity-класс для хранения изображения.")
public class Image extends PersistableEntity implements Serializable {

    private static final long SUID = 1L;

    @ApiModelProperty(required = true, value = "Название фотографии.")
    private String name;

}