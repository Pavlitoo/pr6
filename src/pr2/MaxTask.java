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
        System.out.println("Максімум: " + max);
    }
}

