package pr2;

public class TextTableResultDisplay implements TableResultDisplay {

    @Override
    public void displayTable(CalculationResult result, int rows, int cols) {
        String header = "+------------+------------+------------+\n";
        String separator = "+------------+------------+------------+\n";
        
        System.out.println(header + "| Параметр1 | Параметр2 |   Результат   |\n" + separator);
        System.out.printf("| %10d | %10d | %10d |\n", result.getParameter1(), result.getParameter2(), result.getResult());
        System.out.println(separator);
    }
}
