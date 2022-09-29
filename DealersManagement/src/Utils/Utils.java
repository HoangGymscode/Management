package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Data.Dealer;

/**
 *
 * @author HOANG
 */
public class Utils {

//	public static final Scanner sc = new Scanner(System.in);
    public static boolean validStr(String str, String regEx) {
        return false;
    }

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

    public static String dataToStr(Date date, String dateFormat) {
        return null;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";

        do {
            System.out.print(message);
            input = sc().nextLine().trim();
        } while (input.isEmpty());

        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid = false;

        do {
            System.out.print(message);
            input = sc().nextLine().trim();

            valid = !input.isEmpty();
        } while (!valid);

        return input;
    }

    public static int readInt(String message) {
        System.out.print(message);
        int input = sc().nextInt();
        return input;
    }

    public static boolean readBool(String message) {
        System.out.print(message + " Y/N: ");
        String input = sc().nextLine().trim();

        if (input.isEmpty()) {
            return false;
        }
        String c = input.toUpperCase();

        return c.equals("Y");
    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> files = new ArrayList<>();
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                files.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public static void writeFile(String filename, List<Dealer> list) {

    }

    public static Scanner sc() {
        return new Scanner(System.in);
    }
}
