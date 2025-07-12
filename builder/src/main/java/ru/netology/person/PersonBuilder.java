package ru.netology.person;

import java.util.Optional;
import java.util.OptionalInt;

public class PersonBuilder {
    private String name;
    private String surname;
    private OptionalInt age = OptionalInt.empty();
    private Optional<String> address = Optional.empty();

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = OptionalInt.of(age);
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = Optional.ofNullable(address);
        return this;
    }

    public Person build() {
        // Проверка на обязательные поля
        if (name == null || surname == null) {
            throw new IllegalStateException("Недостаточно данных для создания объекта Person. Имя и фамилия обязательны.");
        }
        // Вызываем конструктор record. Валидация внутри конструктора тоже сработает.
        return new Person(name, surname, age, address);
    }
}