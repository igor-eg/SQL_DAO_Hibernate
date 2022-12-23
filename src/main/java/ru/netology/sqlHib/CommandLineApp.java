package ru.netology.sqlHib;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.netology.sqlHib.entity.City;
import ru.netology.sqlHib.entity.Person;
import ru.netology.sqlHib.entity.PersonalData;
import ru.netology.sqlHib.repository.CityRepository;
import ru.netology.sqlHib.repository.PersonRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CommandLineApp implements CommandLineRunner {

    private final CityRepository cityRepository;
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        // Создаем города
        var cities = Stream.of("Москва", "Краснодар", "Иваново", "Москва")
                .map(n -> City.builder()
                        .name(n)
                        .build()).toList();

        // Сохраняем созданные города в Базу данных
        cityRepository.saveAll(cities);

        var names = List.of("Иван", "Аня", "Федор", "Ольга");

        // Создаем фамилии
        var surnames = List.of("Петров", "Васильева", "Иванов", "Сидоренко");

        var random = new Random();

        // Создаем и сохраняем сущность человека, полученную на основе мужчин
        IntStream.range(0, 10)
                .forEach(i -> {
                    var persons  = Person.builder()
                            .personalData(PersonalData.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(random.nextInt(65))
                                    .build())
                                    .phoneNumber(String.valueOf(random.nextLong(999_999_999)))
                            .city(cities.get(random.nextInt(cities.size())))
                            .build();

                    personRepository.save(persons);
                });

    }
}
