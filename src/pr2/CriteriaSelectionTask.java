package pr2;

import java.util.List;

public class CriteriaSelectionTask implements Runnable {
    private List<Integer> numbers;

    public CriteriaSelectionTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        // Реалізація відбору за критерієм (наприклад, відбір парних чисел)
        System.out.println("Числа, які відповідають критерію (наприклад, парні числа):");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
