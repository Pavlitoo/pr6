package Test;

import pr2.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ParallelProcessingDemoTest {

    public static void main(String[] args) {
        testUserInput_MinimumOption();
        testUserInput_MaximumOption();
        testUserInput_AverageOption();
        testUserInput_CriteriaSelectionOption();
        testUserInput_StatisticalProcessingOption();
        
    }

    public static void testUserInput_MinimumOption() {
        runTestWithUserInput("1", "Ви обрали мінімум:");
    }

    public static void testUserInput_MaximumOption() {
        runTestWithUserInput("2", "Ви вибрали максимум:");
    }

    public static void testUserInput_AverageOption() {
        runTestWithUserInput("3", "Ви вибрали середнє значення:");
    }

    public static void testUserInput_CriteriaSelectionOption() {
        runTestWithUserInput("4", "Ви обрали відбір за критерієм:");
    }

    public static void testUserInput_StatisticalProcessingOption() {
        runTestWithUserInput("5", "Ви вибрали статистична обробка:");
    }

    // Метод для запуску тесту з певним введенням користувача та очікуваним висновком
    private static void runTestWithUserInput(String input, String expectedOutput) {
        // Зберігаємо стандартний потік виведення
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Підготовка введення користувача
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Запуск тестованого методу
        ParallelProcessingDemo.main(new String[]{});

        // Відновлюємо стандартні потоки
        System.setOut(originalOut);
        System.setIn(originalIn);

        // Отримуємо виведення програми
        String actualOutput = outContent.toString().trim();

        // Перевіряємо висновок
        if (actualOutput.equals(expectedOutput)) {
            System.out.println("Тест пройдено успішно");
        } else {
            System.out.println("Тест не пройдено");
        }
    }
}
