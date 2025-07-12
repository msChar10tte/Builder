package ru.netology.person;

import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    protected Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public boolean hasAge() {
        return age != -1;
    }

    public boolean hasAddress() {
        return address != null && !address.isBlank();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (hasAge()) {
            return OptionalInt.of(age);
        } else {
            return OptionalInt.empty();
        }
    }

    public String getAddress() {
        if (hasAddress()) {
            return address;
        } else {
            return "Адрес неизвестен";
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge()) {
            this.age++;
        }
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(this.surname)
                .setAddress(this.address)
                .setAge(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(surname);
        if (hasAge()) {
            sb.append(", возраст: ").append(age);
        }
        if (hasAddress()) {
            sb.append(", город: ").append(address);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, surname);
    }
}