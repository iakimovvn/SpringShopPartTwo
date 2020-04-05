package ru.geekbrains.supershop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.supershop.persistence.entities.Country;
import ru.geekbrains.supershop.persistence.repositories.CountryRepository;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Iterable<Country> findAll(){
        return countryRepository.findAll();
    }
}
