package ru.netology.person;

public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        System.out.println("Создана " + mom);

        // Создаем сына
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom.name() + " есть сын, " + son);

        // Демонстрация работы happyBirthday с неизменяемым объектом
        System.out.println("\n" + son.name() + " празднует день рождения!");
        // Метод возвращает НОВЫЙ объект, поэтому мы переприсваиваем переменную
        son = son.happyBirthday();
        System.out.println("Теперь ему " + son.age().getAsInt());

        // Демонстрация смены адреса
        System.out.println("\nСемья переезжает...");
        // mom = mom.withAddress("Мельбурн"); // Так можно поменять адрес у мамы
        System.out.println("Старый адрес сына: " + son.address().orElse("Неизвестен"));
        son = son.withAddress("Мельбурн");
        System.out.println("Новый адрес сына: " + son.address().orElse("Неизвестен"));
        System.out.println("Обновленные данные сына: " + son);


        // --- Проверка исключений (остается без изменений) ---
        System.out.println("\n--- Тестирование исключений ---");

        try {
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            System.out.println("Успешно поймали ошибку: " + e.getMessage());
        }

        try {
            new PersonBuilder().setName("Имя").setSurname("Фамилия").setAge(-100).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Успешно поймали ошибку: " + e.getMessage());
        }
    }
}