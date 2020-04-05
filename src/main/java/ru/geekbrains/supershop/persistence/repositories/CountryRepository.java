/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

package ru.geekbrains.supershop.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.supershop.persistence.entities.Country;

import java.util.UUID;

public interface CountryRepository extends CrudRepository<Country, UUID> {
}
