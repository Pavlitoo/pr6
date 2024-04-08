### Використання командного рядка Java для обчислення кількість повних тетрад у двійковому поданні заданого

Даний Java-застосунок дозволяє обчислювати кількість повних тетрад у двійковому поданні заданого
десяткового числа, та тестувати різноманітні функції через командний рядок.

## WorkerThread.java

```java
package pr2;

import java.util.concurrent.BlockingQueue;

public class WorkerThread implements Runnable {
    private final BlockingQueue<Runnable> taskQueue;

    public WorkerThread(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Извлечение задачи из очереди и выполнение её
                Runnable task = taskQueue.take();
                task.run();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```


## MaxTask.java

```java
package pr2;

import java.util.List;

public class MaxTask implements Runnable {
    private final List<Integer> numbers;

    public MaxTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int max = numbers.stream().max(Integer::compareTo).orElse(0);
        System.out.println("Maximum: " + max);
    }
}
```

##   MinTask.java

```java
package pr2;

import java.util.List;

public class MinTask implements Runnable {
    private final List<Integer> numbers;

    public MinTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int min = numbers.stream().min(Integer::compareTo).orElse(0);
        System.out.println("Minimum: " + min);
    }
}
```

## StatisticalProcessingTask.java

```java
package pr2;

import java.util.List;

public class StatisticalProcessingTask implements Runnable {
    private List<Integer> numbers;

    public StatisticalProcessingTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        // Вычисление среднего значения
        double sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = sum / numbers.size();
        System.out.println("Среднее значение: " + average);

        // Вычисление медианы
        int size = numbers.size();
        double median;
        if (size % 2 == 0) {
            median = (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            median = numbers.get(size / 2);
        }
        System.out.println("Медиана: " + median);

        // Вычисление стандартного отклонения
        double variance = 0;
        for (int num : numbers) {
            variance += Math.pow(num - average, 2);
        }
        double standardDeviation = Math.sqrt(variance / size);
        System.out.println("Стандартное отклонение: " + standardDeviation);
    }
}
```


## SerializationDemo.java

```java
package pr2;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        CalculationResult result = new CalculationResult(10, 20, 200);

        // Сохранение объекта в файл
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("calculation_result.ser"))) {
            outputStream.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Восстановление объекта из файла
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("calculation_result.ser"))) {
            CalculationResult restoredResult = (CalculationResult) inputStream.readObject();
            System.out.println("Restored result: " + restoredResult);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## ParallelProcessingDemo.java

```java
package pr2;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.ArrayList;


public class ParallelProcessingDemo {
    public static void main(String[] args) {
        // Создание коллекции случайных чисел
        List<Integer> numbers = generateRandomNumbers(100);

        // Создание пула потоков для выполнения задач
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Увеличиваем количество потоков

        // Создание очереди задач
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // Добавление задач в очередь
        taskQueue.add(new MinTask(numbers));
        taskQueue.add(new MaxTask(numbers));
        taskQueue.add(new AverageTask(numbers));
        taskQueue.add(new CriteriaSelectionTask(numbers)); // Добавляем задачу для відбір за критерієм
        taskQueue.add(new StatisticalProcessingTask(numbers)); // Добавляем задачу для статистична обробка

        // Запуск рабочих потоков
        for (int i = 0; i < 5; i++) { // Увеличиваем количество потоков
            executorService.execute(new WorkerThread(taskQueue));
        }

        // Завершение работы ExecutorService
        executorService.shutdown();

        // Получение выбора пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите операцию:");
        System.out.println("1. Минимум");
        System.out.println("2. Максимум");
        System.out.println("3. Среднее значение");
        System.out.println("4. Відбір за критерієм");
        System.out.println("5. Статистична обробка");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Вы выбрали минимум:");
                taskQueue.add(new MinTask(numbers));
                break;
            case 2:
                System.out.println("Вы выбрали максимум:");
                taskQueue.add(new MaxTask(numbers));
                break;
            case 3:
                System.out.println("Вы выбрали среднее значение:");
                taskQueue.add(new AverageTask(numbers));
                break;
            case 4:
                System.out.println("Вы выбрали відбір за критерієм:");
                taskQueue.add(new CriteriaSelectionTask(numbers));
                break;
            case 5:
                System.out.println("Вы выбрали статистична обробка:");
                taskQueue.add(new StatisticalProcessingTask(numbers));
                break;
            default:
                System.out.println("Неверный выбор.");
        }
        scanner.close();
    }

    // Метод для генерации случайных чисел
    private static List<Integer> generateRandomNumbers(int count) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(1000));
        }
        return numbers;
    }
}
```

## ParallelProcessingDemoTest.java


```java
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
```


### Ось результат ↓

![Результат](/screenshot/pr6.jpg)




