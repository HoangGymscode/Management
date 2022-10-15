package Function;

import AccessFile.Config;
import Subjects.Delivery;
import Utils.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import java.util.List;

/**
 *
 * @author HOANG
 */
@SuppressWarnings("serial")
public class DeliveryList extends ArrayList<Delivery> {
//    private LogIn loginObj = null;

    private String dataFile = "";
    private boolean changed = false;	// Whether data in the list changed or not

    // Constructor using loginObj as a parameter
//	public DeliveryList(LogIn loginObj) {
//		this.loginObj = loginObj;
//	}
    // Initialize basic data in files
    public void initWithFile() {
        Config config = new Config();
        dataFile = config.getDeliveryFile();
        loadDeliveriesFromFile();
    }

    /*
	 * Use Tool to read lines from the data file.
	 * For each line in lines, create a Delivery using this line as parameter.
	 * Add this created Delivery to the list.
     */
    private void loadDeliveriesFromFile() {
        List<String> lines = Utils.readLinesFromFile(dataFile);
        for (String line : lines) {
            Delivery delivery = new Delivery(line);
            this.add(delivery);
        }
    }

    // Search Dealer with ID - Use Linear Search
    private int isDuplicated(String ID) {
        for (Delivery d : this) {
            if (d.getID().equalsIgnoreCase(ID)) {
                return 1; // có dữ liệu ID
            }
        }
        return -1;
    }

    /*
	 * Search Delivery - Use Linear Search
	 * Input String ID
     */
    public void searchDelivery() {
        String ID = Utils.readPattern("Enter delivery's ID", Delivery.ID_FORMAT);
        search(this, ID);
    }

    public void search(ArrayList<Delivery> dealers, String ID) {
        ArrayList<Delivery> newList = new ArrayList<Delivery>();
        for (Delivery delivery : this) {
            if (delivery.getID().equalsIgnoreCase(ID)) {
                newList.add(delivery);
            }
        }

        if (newList.isEmpty()) {
            System.out.println("Delivery does not exist");
        } else {
            newList.sort(new Comparator<Delivery>() {

                @Override
                public int compare(Delivery o1, Delivery o2) {
                    return o1.getID().compareTo(o2.getID());
                }
            });

            for (int i = 0; i < newList.size(); i++) {
                Delivery p = newList.get(i);
                System.out.println(p);
            }
        }
    }

    // Add new Dealer
    public void addDelivery() {
        String ID, name, address, phone;
        boolean continuing;
        int pos;

        do {
            ID = Utils.readPattern("ID of new delivery", Delivery.ID_FORMAT).toUpperCase();
            pos = isDuplicated(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!"); // trả lại giá trị 1!
            }
        } while (pos >= 0);

        name = Utils.readNonBlank("Name of new delivery").toUpperCase();
        address = Utils.readNonBlank("Address of new delivery");
        phone = Utils.readPattern("Phone number", Delivery.PHONE_FORMAT);
        continuing = Utils.readBool("Is continuing?");

        Delivery delivery = new Delivery(ID, name, address, phone, continuing);
        this.add(delivery);
        changed = true;
    }

    // Remove Delivery - Input ID
    public void removeDelivery() {
        String id;
        int pos;
        do {
            id = Utils.readPattern("Enter ID of delivery", Delivery.ID_FORMAT);
            pos = isDuplicated(id);
            if (pos < 0) {
                System.out.println("ID not found!");
            }
        } while (pos < 0);

        Iterator<Delivery> it = this.iterator(); // lấy hàm ArrayList<Delivery> vào xử lí
        while (it.hasNext()) {
            Delivery delivery = it.next();
            if (delivery.getID().equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Delete successfully!");
                changed = true;
            }
        }
    }

    /*
	 * Update a Delivery
	 * Only 'name', 'address' and 'phone' can be changed
     */
    public void updateDelivery() {
        String ID = Utils.readPattern("Delivery's ID needs updating", Delivery.ID_FORMAT);
        int pos = isDuplicated(ID);

        if (pos < 0) {
            System.out.println("Delivery " + ID + " not found!");
        } else {
            Delivery delivery = this.get(pos);
            String newName = "";
            System.out.print("New name: ");
            newName = Utils.sc().nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                delivery.setName(newName);

            }
            System.out.print("New phone: ");
            String newPhone;
            newPhone = Utils.sc().nextLine().trim().toUpperCase();
            if (!newPhone.isEmpty()) {
                delivery.setPhone(newPhone);
            }
            System.out.print("New address: ");
            String newAddress;
            newAddress = Utils.sc().nextLine().trim().toUpperCase();
            if (!newAddress.isEmpty()) {
                delivery.setAddress(newAddress);
            }
            changed = true;
        }
    }

    // Print all Deliveries
    public void printAllDeliveries() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println("------------- Deliveries from List ---------------");
            for (Delivery delivery : this) {
                System.out.println(delivery);
            }
        }

        System.out.println("------------- Deliveries from File ---------------");
        printListFile();
    }

    private void printListFile() {
        List<String> deliveries = Utils.readLinesFromFile(dataFile);
        for (String str : deliveries) {
            System.out.println(str);
        }
    }

    // Print all continuing Deliveries
    public void printContinuingDeliveries() {
        for (Delivery d : this) {
            if (d.isContinuing()) { // in các kiểu dữ liệu chạy từ continounig set là true hoặc false!
                System.out.println(d);
            }
        }
    }

    // Print all un-continuing Deliveries
    public void printUnContinuingDealers() {
        for (Delivery d : this) {
            if (!d.isContinuing()) {
                System.out.println(d);
            }
        }
    }

    // Write dealer list to file
    public void writeDeliveryToFile() {
        if (changed = true) {
            Utils.writeFile(dataFile, this);
            System.out.println("Write successfully!");
            changed = false; // reset change thành false để lưu các giá trị tiếp theo!
        } else {
            System.out.println("Data not changed!");
        }
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
