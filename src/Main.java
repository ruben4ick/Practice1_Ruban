
import java.security.MessageDigest;

import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        String message = "Test for hashing";
        FileWriter writer = new FileWriter("hashResults.txt");

        System.out.println("Task 1");
        writer.write("MessageDigest Hashes:\n");
        String[] messageDigestAlgorithms = {"MD5", "SHA-1", "SHA-256"};
        for (String algorithm : messageDigestAlgorithms) {
            String hash = testMessageDigest(message, algorithm);
            System.out.println(algorithm + ": " + hash);
            writer.write(algorithm + ": " + hash + "\n");
        }

        System.out.println("\nTask 2");
        writer.write("\nSecureRandom Hashes:\n");
        String[] secureRandomAlgorithms = {"DRBG", "SHA1PRNG", "Windows-PRNG"};
        for (String algorithm : secureRandomAlgorithms) {
            String hash = testSecureRandom(message, algorithm);
            System.out.println(algorithm + ": " + hash);
            writer.write(algorithm + ": " + hash + "\n");
        }
        writer.close();

        System.out.println("\nTask 3");
        System.out.println("Correct hashCode() and equals():");
        HashMap<Car, Integer> carMap = new HashMap<>();
        Car car1 = new Car("Toyota", "Camry", 2020);
        Car car2 = new Car("Toyota", "Camry", 2020);
        Car car3 = new Car("Honda", "Accord", 2021);

        System.out.println("Car1 hashCode: " + car1.hashCode());
        System.out.println("Car2 hashCode: " + car2.hashCode());
        System.out.println("Car3 hashCode: " + car3.hashCode());
        System.out.println(car1.equals(car2));
        System.out.println(car1.equals(car3));

        carMap.put(car1, 1);
        carMap.put(car2, 2);  //car1 equals car2
        carMap.put(car3, 3);

        for (Map.Entry<Car, Integer> entry: carMap.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }

        System.out.println("\n Incorrect hashCode() and equals():");
        HashMap<BadCar, Integer> badCarMap = new HashMap<>();
        BadCar bc1 = new BadCar("BMW", "Black", 2020);
        BadCar bc2 = new BadCar("Skoda", "White", 2011);
        BadCar bc3 = new BadCar("BMW", "Black", 2020);

        badCarMap.put(bc1, 1);
        badCarMap.put(bc2, 2);
        badCarMap.put(bc3, 3);

        for (Map.Entry<BadCar, Integer> entry: badCarMap.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }


    }
    public static String testMessageDigest(String input, String algorithm) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = md.digest(input.getBytes());
        return byteArrayToHexString(hashBytes);
    }

    public static String testSecureRandom(String input, String algorithm) throws Exception {
        SecureRandom sr = SecureRandom.getInstance(algorithm);
        byte[] randomBytes = new byte[16];
        sr.setSeed(input.getBytes());
        sr.nextBytes(randomBytes);
        return byteArrayToHexString(randomBytes);
    }

    public static String byteArrayToHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}