package pr2;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        CalculationResult result = new CalculationResult(10, 20, 200);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("calculation_result.ser"))) {
            outputStream.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("calculation_result.ser"))) {
            CalculationResult restoredResult = (CalculationResult) inputStream.readObject();
            System.out.println("Відновлений результат: " + restoredResult);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

