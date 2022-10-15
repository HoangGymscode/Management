package Subjects;

import Utils.Utils;

/**
 *
 * @author HOANG
 */
public class Dealer implements Comparable<Dealer> {

    public static final String SEPARATOR = ",";
    public static final String ID_FORMAT = "D\\d{3}";			// D001, D123, D700,...
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";	// The 9 or 11 digits phone number

    private String ID;			// Template D000
    private String name;		// Dealers's name
    private String address;		// Dealers's address
    private String phone;		// 9 or 11 digits
    private boolean continuing;	// Whether this dealer still cooperates or not

    // Constructor using 5 parameters
    public Dealer(String ID, String name, String address, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.continuing = continuing;
    }

    // Constructor using a line using a line using separator ','
    public Dealer(String line) {
        String[] parts = line.split(SEPARATOR);

        ID = parts[0].trim();		// Dealer's ID
        name = parts[1].trim();		// Dealer's name
        address = parts[2].trim();	// Dealer's address
        phone = parts[3].trim();	// 9 or 11 digits
        continuing = Utils.parseBool(parts[4]);
    }

    @Override
    public int compareTo(Dealer o) {
        return 0;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + name + SEPARATOR + address + SEPARATOR + phone + SEPARATOR + continuing;
    }
//        public void show() {
//        System.out.println("|ID: " + getID() +"|"+ "Name: " + getName() + ", "
//        					+ "UnitPrice: " + getPhone() + ", "
//        					+ "Quantity: "+ getAddress() + ", "
//        					+ "Status: " + getStatus());
//    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }
}
