package Utils;

import data.Dealer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HOANG
 */
public class Utils {

    public static Scanner sc() {
        return new Scanner(System.in);
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Please Re-Enter text!!!!!!!!!");
            } else {

//            	if (result.contains(" ") || result.length() < 5) {
//            		System.out.println("Please Re-Enter text!!!!!!!!!");
//            	} else {
//                	check = false;
//            	}
                check = false;
            }

        } while (check);

        return result;
    }

    public static String getStringStatus(String welcome) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Please Re-Enter text!!!!!!!!!");
            } else {

                if (result.equals("Available") || result.equals("Not Available")) {
                    check = false;
                } else {
                    System.out.println("Please Re-Enter text!!!!!!!!!");
                }
            }

        } while (check);

        return result;
    }

    public static int getIntmin(String welcome, int min) {
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println("Number must be large than " + min);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!!!");
            }
        } while (check || number < min);
        return number;
    }

    public static float getFloat(String welcome, float min, float max) {
        boolean check = true;
        float number = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String userEnter = sc.nextLine();
                number = Float.parseFloat(userEnter);

                if (number < min || number > max) {
                    System.out.println("Please Re-Enter number!!!");
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Please Enter number!!!");
            }

        } while (check);

        return number;
    }

    public static int getInt(String welcome, int i) {
        System.out.print(welcome);
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        number = Integer.parseInt(sc.nextLine());
        return number;
    }

//    public static boolean checkDuplicateName(List<Product> list, String ProductName) {
//    	boolean check = false;
//    	for (Product p : list) {
//    		String pName = p.getProductName();
//    		if (pName.equalsIgnoreCase(ProductName)) {
//    			check = true;
//    			break;
//    		}
//    	}
//    	
//    	return check;
//    }
//    public static boolean checkDuplicateId(List<Product> list, String ProductID) {
//    	for (Product p : list) {    
//    		String pID = p.getProductID(); 
//    		if (pID.equalsIgnoreCase(ProductID)) {    
//    			return true;
//    		}
//    	}
//    	
//    	return false;
//    }
//    public static Product getProduct(String productString) {
//    	Product product = new Product();
//    	String[] arrayAttr = new String[5];
//    	
//    	String[] array = productString.split(", ");
//    	for (int i = 0; i < array.length; i++) {
//    		String str = array[i];
//    		String[] arr = str.split(": ");
//    		arrayAttr[i] = arr[1];
//    	}
//    	
//    	product.setProductID(arrayAttr[0]);
//    	product.setProductName(arrayAttr[1]);
//    	product.setUnitPrice(Float.valueOf(arrayAttr[2]));
//    	product.setQuantity(Integer.valueOf(arrayAttr[3]));
//    	product.setStatus(arrayAttr[4]);
//    	
//    	return product;
//    }
    public static List<String> readFromFile(String filename) {
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
    public static final String SEPARATOR = ",";

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

    public static boolean CheckReturn() {
        System.out.println("Do you want to continue or not? (Y/N)");
        boolean kt = false;
        Scanner sc = new Scanner(System.in);
        String ct = sc.nextLine();
        if (ct.equals("Y") || ct.equals("y")) {
            return kt = true;
        } else if (ct.equals("N") || ct.equals("n")) {
            System.exit(0);
        } else {
            return kt;
        }

        return false;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid = false;

        do {
            System.out.print(message);
            input = sc().nextLine().trim();
            valid=input.matches(pattern);
//            valid = !input.isEmpty();
        } while (!valid);

        return input;
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
     public static String readNonBlank(String message) {
		String input = "";
		
		do {
			System.out.print(message + ": ");
			input = sc().nextLine().trim();
		} while (input.isEmpty() || input.isEmpty());
		
		return input;
	}
}
