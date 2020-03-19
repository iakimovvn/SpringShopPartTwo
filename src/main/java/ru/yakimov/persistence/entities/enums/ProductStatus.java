/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.yakimov.persistence.entities.enums;

import lombok.Getter;

public enum ProductStatus {
    ENABLE("Показать всё"),
    DISABLE("В наличии");

    @Getter
    private String name;

    ProductStatus(String name) {
        this.name = name;
    }
}
