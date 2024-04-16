package pr2;

public class TextResultDisplay implements ResultDisplay {
    @Override
    public void displayResult(CalculationResult result) {
        System.out.println("Параметр 1: " + result.getParameter1());
        System.out.println("Параметр 2: " + result.getParameter2());
        System.out.println("Результат: " + result.getResult());
    }
}

