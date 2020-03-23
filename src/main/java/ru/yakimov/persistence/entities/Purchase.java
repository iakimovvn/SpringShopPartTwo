package ru.yakimov.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yakimov.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Purchase extends PersistableEntity {

    private Double price;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<CartRecord> cardRecord;

    @ManyToOne
    @JoinColumn(name = "shopuser")
    private Shopuser shopuser;
}
