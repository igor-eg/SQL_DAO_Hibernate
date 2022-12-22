
package ru.netology.sqlHib.service;

import org.springframework.stereotype.Service;
import ru.netology.sqlHib.entity.Person;
import ru.netology.sqlHib.repository.SqlRepository;

import java.util.List;

@Service
public class SqlService {

    private final SqlRepository sqlRepository;

    public SqlService(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }

    public List<Person> getPersonsByCity(String city) {
        return sqlRepository.getPersonsByCity(city);
    }

    public boolean checkCity(String city) {
        return sqlRepository.checkCity(city);
    }

}