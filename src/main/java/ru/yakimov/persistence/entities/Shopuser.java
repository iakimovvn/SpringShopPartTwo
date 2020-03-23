package ru.yakimov.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.yakimov.persistence.entities.utils.PersistableEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */


@Entity
@Data
@NoArgsConstructor
@Table(name = "shopuser")
@EqualsAndHashCode(callSuper = true)
public class Shopuser extends PersistableEntity {

    private String phone;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToMany
    @JoinTable(name = "shopuser_role",
            joinColumns = @JoinColumn(name = "shopuser"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "shopuser")
    private List<Purchase> purchases;
}
