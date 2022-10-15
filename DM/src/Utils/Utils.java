package Utils;

import Subjects.Account;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HOANG
 */
public class Utils {

    private static final String FILE = "data/account.txt";

    /*
	 * Checking whether str matches a pattern or not
	 * Use the method String.matches(regEx)
     */
    public static boolean validStr(String str, String regEx) {
        return false;
    }

    /*
	 * Checking a password with minLength in which it contains 
	 * at least a character, a number and 1 specific character
     */
    public static boolean validPassword(String password, int minLenght) {
        if (password.length() < minLenght) {
            return false;
        }
        return password.matches(".*[a-zA-Z]+.*")
                && password.matches(".*[\\d]+.*")
                && password.matches(".*[\\W]+.*");
    }

    // Convert data string to Date - Using class SimpleDataFormat
    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getInstance();
        sdf.applyPattern(dateFormat);

        try {
            long time = sdf.parse(dateStr).getTime();
            return new Date(time);
        } catch (ParseException e) {
            System.out.println(e);
        }

        return null;
    }

    // Convert Data to data string
    public static String dateToString(Date date, String dateFormat) {
        return null;
    }

    // Convert bool string to boolean
    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    // Method for inputting data - Requires non-blank
    public static String readNonBlank(String message) {
        String input = "";

        do {
            System.out.print(message + ": ");
            input = sc().nextLine().trim();

        } while (input.isEmpty());

        return input;
    }

    // Input string that matches pattern
    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid = false;

        do {
            System.out.print(message + ": ");
            input = sc().nextLine().trim();

            valid = input.matches(pattern);//....
        } while (!valid);

        return input;
    }

    public static boolean checkRole() {
        List<String> lines = Utils.readLinesFromFile(FILE);
        for (String line : lines) {
            String[] parts = line.split(Account.SEPARATOR);
            if (parts.length <3) {
                return false;
            }
            // ghi viết đọc để check role;
            
         
//                    && parts[1].equals(account.getPassword())
//                    && parts[2].equalsIgnoreCase(account.getRole())) {
                return true;
//            }
        }
    
        return false;
        }
        // Method for inputting integer type
    public static int readInt(String message) {
        System.out.print(message + ": ");
        int input = sc().nextInt();
        return input;
    }

    // Method for inputting boolean type
    public static boolean readBool(String message) {
        System.out.print(message + " Y/N: ");
        String input = sc().nextLine().trim().toUpperCase();

        if (input.isEmpty()) {
            return false;
        }
//		String c = input.toUpperCase();

        return parseBool(input);
    }

    /*
	 * Method for reading lines from text file
	 * @return list of lines
     */
    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    // Method for writing a list to a text file line-by-line
    public static <T> void writeFile(String filename, List<T> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (T d : list) {
                bw.write(d.toString());
                bw.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scanner sc() {
        return new Scanner(System.in);
    }

}
