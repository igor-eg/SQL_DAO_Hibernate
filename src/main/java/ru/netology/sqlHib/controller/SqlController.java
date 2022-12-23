
package ru.netology.sqlHib.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.sqlHib.entity.City;
import ru.netology.sqlHib.entity.Person;
import ru.netology.sqlHib.service.SqlService;

import java.util.List;

@RestController
public class SqlController {
    private final SqlService sqlService;

    public SqlController(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    // Запрос: localhost:8080/persons/city/create?name=...
    @GetMapping("${endpoint-city-create}")
    public City createCity(@RequestParam("name") String cityName) {
        try {
            return sqlService.createCity(cityName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "City is already saved in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/city/read?name=...
    @GetMapping("${endpoint-city-read}")
    public City readByName(@RequestParam("name") String cityName) {
        try {
            return sqlService.readCityByName(cityName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/city/read/all
    @GetMapping("${endpoint-city-read-all}")
    public List<City> readAllCityThanOrderByName() {
        return sqlService.readAllCityThanOrderByName();
    }

    // Запрос: localhost:8080/persons/person/read/by-city?cityName=...
    @GetMapping("${endpoint-person-read-by-city}")
    public List<Person> readPersonByCityName(@RequestParam("cityName") String cityName) {
        try {
            return sqlService.readPersonByCityName(cityName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/person/read/by-age/less?age=...
    @GetMapping("${endpoint-person-read-by-age-less}")
    public List<Person> readPersonByAgeLessThanOrderByAge(@RequestParam("age") int age) {
        try {
            return sqlService.readPersonByAgeLessThanOrderByAge(age);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/person/read/by-name-and-surname?name=...&surname=...
    @GetMapping("${endpoint-person-read-by-name-and-surname}")
    public List<Person> readPersonByNameAndSurname(
            @RequestParam("name") String name, @RequestParam("surname") String surname) {
        try {
            return sqlService.readPersonByNameAndSurname(name, surname);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/person/create?name=...&surname=...&age=...&gender=...&phoneNumber=...&city=...
    @GetMapping("${endpoint-person-create}")
    public Person createPerson(
            @RequestParam("name") String name, @RequestParam("surname") String surname,
            @RequestParam("age") int age,
            @RequestParam("phoneNumber") String phoneNumber, @RequestParam("city") String city) {
        try {
            return sqlService.createPerson(name, surname, age, phoneNumber, city);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Incorrect parameters specified!");
        }
    }

    // Запрос: localhost:8080/persons/person/read/all
    @GetMapping("${endpoint-person-read-all}")
    public List<Person> readAllPersonThanOrderByAge() {
        return sqlService.readAllPersonThanOrderByAge();
    }

    // Запрос: localhost:8080/persons/person/delete?name=...&surname=...&age=...
    @GetMapping("${endpoint-person-delete}")
    public Person deletePerson(
            @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("age") int age) {
        try {
            return sqlService.deletePerson(name, surname, age);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found in the DataBase!");
        }
    }

    // Запрос: localhost:8080/persons/person/update-phone?name=...&surname=...&age=...&phoneNumber=...
    @GetMapping("${endpoint-person-update-phone}")
    public Person updatePersonsPhone(
            @RequestParam("name") String name, @RequestParam("surname") String surname,
            @RequestParam("age") int age, @RequestParam("phoneNumber") String phoneNumber) {
        try {
            return sqlService.updatePersonsPhone(name, surname, age, phoneNumber);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found in the DataBase!");
        }
    }
}
