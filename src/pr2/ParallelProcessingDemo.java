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
