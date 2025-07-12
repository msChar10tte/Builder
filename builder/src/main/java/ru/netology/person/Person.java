package ru.netology.person;

import java.util.Optional;
import java.util.OptionalInt;

// Используем record для неизменяемого хранения данных
public record Person(String name, String surname, OptionalInt age, Optional<String> address) {

    // Проверки можно добавить в компактный конструктор
    public Person {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
    }

    // Методы has... теперь просто проверяют наличие значения в Optional
    public boolean hasAge() {
        return age.isPresent();
    }

    public boolean hasAddress() {
        return address.isPresent() && !address.get().isBlank();
    }

    // Геттеры name(), surname(), age(), address() создаются автоматически

    // Вместо setAddress, создаем новый объект с новым адресом ("with-er")
    public Person withAddress(String newAddress) {
        return new Person(this.name, this.surname, this.age, Optional.ofNullable(newAddress));
    }

    // Вместо happyBirthday, создаем новый объект с увеличенным возрастом
    public Person happyBirthday() {
        if (this.hasAge()) {
            return new Person(this.name, this.surname, OptionalInt.of(this.age.getAsInt() + 1), this.address);
        }
        // Если возраста нет, возвращаем себя же без изменений
        return this;
    }

    // Метод для создания билдера ребенка
    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(this.surname)
                .setAddress(this.address.orElse(null)) // Извлекаем значение из Optional
                .setAge(0);
    }

    // toString() генерируется автоматически и выглядит примерно так:
    // Person[name=Анна, surname=Вольф, age=OptionalInt[31], address=Optional[Сидней]]
    // Если нужно более красивое представление, можно переопределить:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(surname);
        age.ifPresent(a -> sb.append(", возраст: ").append(a));
        address.ifPresent(addr -> sb.append(", город: ").append(addr));
        return sb.toString();
    }
}