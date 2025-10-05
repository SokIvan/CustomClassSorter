package com.aston.fileworker;

import com.aston.customClasses.Car;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileWriterUtilTest {

    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Создаём временный файл перед тестом
        tempFile = Files.createTempFile("cars", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        // Удаляем временный файл после теста
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteCarsToFileSimple() throws IOException {
        // Создаём один тестовый автомобиль
        Car car = new Car.CarBuilder()
                .setGosNumber("A111AA")
                .setModel("Toyota")
                .setDate(2020)
                .setCost(10000)
                .setLastOwner("Иванов")
                .build();

        // Записываем автомобиль в файл и проверяем, что вернулось 1
        int count = FileWriterUtil.writeCarsToFile(tempFile.toString(), List.of(car));
        assertEquals(1, count);

        // Проверяем, что файл содержит эту запись
        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("Toyota"));
    }
}
