package ru.yakimov.persistence.pojo;

import lombok.Data;
import ru.yakimov.persistence.entities.enums.ProductCategory;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Data
public class ProductPojo {
    private String title;
    private String description;
    private Double price;
    private boolean available;
    private ProductCategory category;
}
