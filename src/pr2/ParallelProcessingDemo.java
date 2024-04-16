package pr2;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.ArrayList;


public class ParallelProcessingDemo {
    public static void main(String[] args) {
    // Створення колекції випадкових чисел
        List<Integer> numbers = generateRandomNumbers(100);

        // Створення пулу потоків до виконання завдань
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Збільшуємо кількість потоків

        // Створення черги завдань
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // Створення черги завдань
        taskQueue.add(new MinTask(numbers));
        taskQueue.add(new MaxTask(numbers));
        taskQueue.add(new AverageTask(numbers));
        taskQueue.add(new CriteriaSelectionTask(numbers)); 
        taskQueue.add(new StatisticalProcessingTask(numbers)); 

        
        for (int i = 0; i < 5; i++) { // Збільшуємо кількість потоків
            executorService.execute(new WorkerThread(taskQueue));
        }

        
        executorService.shutdown();

        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть операцію:");
        System.out.println("1. Мінімум");
        System.out.println("2. Максимум");
        System.out.println("3. Среднеє значення");
        System.out.println("4. Відбір за критерієм");
        System.out.println("5. Статистична обробка");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Ви обрали мінімум:");
                taskQueue.add(new MinTask(numbers));
                break;
            case 2:
                System.out.println("Ви обрали максимум:");
                taskQueue.add(new MaxTask(numbers));
                break;
            case 3:
                System.out.println("Ви вибрали середнє значення:");
                taskQueue.add(new AverageTask(numbers));
                break;
            case 4:
                System.out.println("Вы выбрали відбір за критерієм:");
                taskQueue.add(new CriteriaSelectionTask(numbers));
                break;
            case 5:
                System.out.println("Ви вибрали статистична обробка:");
                taskQueue.add(new StatisticalProcessingTask(numbers));
                break;
            default:
                System.out.println("Неправильний вибір.");
        }
        scanner.close();
    }

    //Метод для генерації випадкових чисел
    private static List<Integer> generateRandomNumbers(int count) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(1000));
        }
        return numbers;
    }
}
