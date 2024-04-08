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
        runTestWithUserInput("1", "Вы выбрали минимум:");
    }

    public static void testUserInput_MaximumOption() {
        runTestWithUserInput("2", "Вы выбрали максимум:");
    }

    public static void testUserInput_AverageOption() {
        runTestWithUserInput("3", "Вы выбрали среднее значение:");
    }

    public static void testUserInput_CriteriaSelectionOption() {
        runTestWithUserInput("4", "Вы выбрали відбір за критерієм:");
    }

    public static void testUserInput_StatisticalProcessingOption() {
        runTestWithUserInput("5", "Вы выбрали статистична обробка:");
    }

    // Метод для запуска теста с определенным вводом пользователя и ожидаемым выводом
    private static void runTestWithUserInput(String input, String expectedOutput) {
        // Сохраняем стандартный поток вывода
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Подготовка ввода пользователя
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Запуск тестируемого метода
        ParallelProcessingDemo.main(new String[]{});

        // Восстанавливаем стандартные потоки
        System.setOut(originalOut);
        System.setIn(originalIn);

        // Получаем вывод программы
        String actualOutput = outContent.toString().trim();

        // Проверяем вывод
        if (actualOutput.equals(expectedOutput)) {
            System.out.println("Тест пройден успешно");
        } else {
            System.out.println("Тест не пройден");
        }
    }
}
