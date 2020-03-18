package ru.yakimov.utils;

import org.springframework.stereotype.Component;
import ru.yakimov.exceptions.ProductNotFoundException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Component
public class PatternUUID {
    private final Pattern pattern = Pattern.compile("^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$");

    public void checkUUID(UUID uuid) throws ProductNotFoundException {
        Matcher matcher = pattern.matcher(uuid.toString());
        if(!matcher.find())
            throw new ProductNotFoundException("Incorrect UUID: " + uuid);

    }
}
