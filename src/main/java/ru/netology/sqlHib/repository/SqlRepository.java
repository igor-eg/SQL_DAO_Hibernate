package ru.netology.sqlHib.repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import ru.netology.sqlHib.entity.City;
import ru.netology.sqlHib.entity.Person;
import ru.netology.sqlHib.entity.PersonalData;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Repository
public class SqlRepository  implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Создаем города
        var cities = Stream.of("Москва", "Краснодар", "Иваново", "Париж")
                .map(n -> City.builder()
                        .name(n)
                        .build())
                .collect(Collectors.toUnmodifiableList());

        // Сохраняем созданные города в Базу данных
        for (City entity : cities) {
            entityManager.persist(entity);
        }

        // Создаем имена
        var names = List.of("Иван", "Аня", "Федор", "Ольга");

        // Создаем фамилии
        var surnames = List.of("Петров", "Васильева", "Иванов", "Сидоренко");

        var random = new Random();

        // Создаем и сохраняем сущность персоны
        IntStream.range(0, 10)
                .forEach(i -> {
                    var persons = Person.builder()
                            .personalData(PersonalData.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(random.nextInt(70))
                                    .build())
                                    .phoneNumber(String.valueOf(random.nextLong(999_999_999)))
                            .city(cities.get(random.nextInt(cities.size())))
                            .build();

                    entityManager.persist(persons);
                });


        // Выводим на экран всех людей, созданных и сохраненных в базе данных
        Query query = entityManager.createQuery("select p from Person p order by p.personalData.name");
        List <Person> resultList = query.getResultList();
        System.out.println(resultList);

    }

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("select p from Person p where p.city.name = " +
                ":name order by p.personalData.name");
        query.setParameter("name", city);
        List <Person> resultList = query.getResultList();
        return resultList;
    }

    public boolean checkCity(String city) {
        Query query = entityManager.createQuery("select c from City c where c.name = " +
                ":name order by c.id");
        query.setParameter("name", city);
        List <Person> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return true;
        } else return false;
    }

}