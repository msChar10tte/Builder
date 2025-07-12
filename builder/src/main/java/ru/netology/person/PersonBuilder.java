package ru.netology.person;

public class PersonBuilder {
    private String name;
    private String surname;
    private int age = -1;
    private String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Некорректный возраст: " + age);
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя не может быть пустым");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalStateException("Фамилия не может быть пустой");
        }
        return new Person(name, surname, age, address);
    }
}