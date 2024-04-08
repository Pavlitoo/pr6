package pr2;

import java.util.List;

public class CriteriaSelectionTask implements Runnable {
    private List<Integer> numbers;

    public CriteriaSelectionTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        // Реализация отбора по критерию (например, отбор четных чисел)
        System.out.println("Числа, удовлетворяющие критерию (например, четные числа):");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
