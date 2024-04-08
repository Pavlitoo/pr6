package pr2;

import java.util.List;

public class AverageTask implements Runnable {
    private final List<Integer> numbers;

    public AverageTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average: " + average);
    }
}

