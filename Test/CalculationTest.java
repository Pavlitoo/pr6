package Test;

import java.util.Scanner;

import pr2.CalculationResult;
import pr2.TextTableResultDisplay;

public class CalculationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть параметр 1: ");
        int parameter1 = scanner.nextInt();

        System.out.print("Введіть параметр 2: ");
        int parameter2 = scanner.nextInt();

        // Обчислення результату
        int result = parameter1 * parameter2;

        CalculationResult resultObject = new CalculationResult(parameter1, parameter2, result);

        // Тестування результатів обчислень
        if (resultObject.getResult() != result) {
            throw new AssertionError("Результат розрахунку невірний");
        } else {
            System.out.println("Результат розрахунку правильний: " + resultObject.getResult());
        }

        // Створення об'єкта для відображення результатів у вигляді таблиці
        TextTableResultDisplay resultDisplay = new TextTableResultDisplay();
        resultDisplay.displayTable(resultObject, 1, 3);

        scanner.close();
    }
}
