package ru.yakimov.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import ru.yakimov.entities.enums.ProductCategory;
import ru.yakimov.utils.PersistableEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends PersistableEntity {

    private String title;

    private String description;

    private Date added;

    private Double price;

    private boolean available;

    @Enumerated(EnumType.ORDINAL)
    private ProductCategory category;

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

}