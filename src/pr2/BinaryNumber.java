package pr2;


public class BinaryNumber {
    public static void main(String[] args) {
        int decimalNumber = 123; // Задане десяткове число
        int binaryOnesCount = countOnesInBinary(decimalNumber);
        int fullQuartets = binaryOnesCount / 4; // Кількість повних зошит
        System.out.println("Кількість повних квартетів у двійковому представленні " + decimalNumber + " це " + fullQuartets);
    }

    private static int countOnesInBinary(int decimalNumber) {
        String binaryString = Integer.toBinaryString(decimalNumber);
        int count = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
