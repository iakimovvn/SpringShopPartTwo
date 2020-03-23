/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.yakimov.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.yakimov.persistence.entities.Role;
import ru.yakimov.persistence.entities.Shopuser;

import java.util.UUID;

public interface ShopuserRepository extends CrudRepository<Shopuser, UUID> {
    Shopuser findOneByPhone(String phone);
    boolean existsByPhone(String phone);

    Shopuser findByFirstName(String name);
    boolean existsByFirstName(String name);
}
