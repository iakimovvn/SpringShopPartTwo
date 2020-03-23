/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.yakimov.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.yakimov.persistence.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
