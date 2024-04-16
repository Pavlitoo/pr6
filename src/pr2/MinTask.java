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
        System.out.println("Мінімум: " + min);
    }
}

