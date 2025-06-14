package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Конфігурація тестового середовища Spring, що підключає in-memory базу даних та необхідні компоненти
@SpringJUnitConfig(SystemTestConfiguration.class)
public class SpecialtyRepositoryTest {

    // Автоматичне впровадження репозиторію для тестування методів роботи з таблицею спеціальностей
    @Autowired
    SpecialtyRepository specialtyRepository;

    // Тест перевіряє правильність збереження нової спеціальності до бази даних
    @Test
    public void saveSpecialtyTest() {
        Department department = new Department(); // створення тестової кафедри
        department.setId(1); // встановлення існуючого ID кафедри
        Specialty specialty = new Specialty(0, "unique testName", department); // створення спеціальності з унікальним ім’ям
        specialtyRepository.saveSpecialty(specialty); // збереження спеціальності в базу
        assertEquals(7, specialtyRepository.getAllSpecialties().size()); // перевірка, що кількість спеціальностей збільшилась
    }

    // Тест перевіряє можливість отримати спеціальність за ідентифікатором
    @Test
    public void getSpecialtyByIdTest() {
        Specialty specialty = specialtyRepository.getSpecialtyById(1); // запит спеціальності з ID 1
        assertEquals("Software Engineering", specialty.getName()); // перевірка правильності імені
    }

    // Тест перевіряє функціональність оновлення запису спеціальності
    @Test
    public void updateSpecialtyTest() {
        Department department = new Department();
        department.setId(1);
        Specialty specialty = new Specialty(1, "testName", department); // нові дані для існуючої спеціальності
        specialtyRepository.updateSpecialty(specialty); // оновлення запису
        assertEquals("testName", specialtyRepository.getSpecialtyById(1).getName()); // перевірка оновлення
    }

    // Тест перевіряє можливість видалення спеціальності за ID
    @Test
    public void deleteSpecialtyTest() {
        specialtyRepository.deleteSpecialty(7); // видалення спеціальності з ID 7 (попередньо доданої у saveSpecialtyTest)
    }
}
