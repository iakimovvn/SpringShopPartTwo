package ru.yakimov.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.yakimov.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_record")
@EqualsAndHashCode(callSuper = true)
public class CartRecord extends PersistableEntity {

    private Integer quantity;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase")
    private Purchase purchase;

    public CartRecord(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
    }
}
