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
