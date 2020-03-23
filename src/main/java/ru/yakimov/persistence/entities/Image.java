package ru.yakimov.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.yakimov.persistence.entities.utils.PersistableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Image extends PersistableEntity implements Serializable {

    private static final long SUID = 1L;

    private String name;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

}