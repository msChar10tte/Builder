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

        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom.getName() + " есть сын, " + son);

        System.out.println("\n" + son.getName() + " празднует день рождения!");
        son.happyBirthday();
        System.out.println("Теперь ему " + son.getAge().getAsInt());

        System.out.println("\nСемья переезжает...");
        mom.setAddress("Мельбурн");
        System.out.println("Новый адрес мамы: " + mom.getAddress());
        System.out.println("Обновленные данные: " + mom);

        System.out.println("\n--- Тестирование исключений ---");
        try {
            new PersonBuilder().setName("Иван").build();
        } catch (IllegalStateException e) {
            System.err.println("Поймали ошибку: " + e.getMessage());
        }

        try {
            new PersonBuilder()
                    .setName("Петр")
                    .setSurname("Петров")
                    .setAge(-100)
                    .build();
        } catch (IllegalArgumentException e) {
            System.err.println("Поймали ошибку: " + e.getMessage());
        }
    }
}