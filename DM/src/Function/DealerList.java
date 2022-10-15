package Function;

import AccessFile.Config;
import Subjects.Dealer;
import Utils.Utils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HOANG
 */
public class DealerList extends ArrayList<Dealer> {

//    private LogIn loginObj = null;
    private String dataFile = "";
    private boolean changed = false;	// Whether data in the list changed or not

    // Constructor using loginObj as a parameter
//    public DealerList() {
//        this.loginObj = loginObj;
//    }

    // Initialize basic data in files
    public void initWithFile() {
        Config config = new Config();
        dataFile = config.getDealerFile();
        loadDealerFromFile();
    }

    /*
	 * Use Tool to read lines from the data file.
	 * For each line in lines, create a Dealer using this line as parameter.
	 * Add this created Dealer to the list.
     */
    private void loadDealerFromFile() {
        List<String> lines = Utils.readLinesFromFile(dataFile);
        for (String line : lines) {
            Dealer dealer = new Dealer(line);
            this.add(dealer);
        }
    }

    // Search Dealer with ID - Use Linear Search
    private int isDuplicated(String ID) {
        for (Dealer d : this) {
            if (d.getID().equalsIgnoreCase(ID)) {
                return 1;
            }
        }
        return -1;
    }

    /*
	 * Search Dealer - Use Linear Search
	 * Input String ID
     */
    public void searchDealer() {
        String ID = Utils.readPattern("Enter dealer's ID", Dealer.ID_FORMAT);
        search(this, ID);
    }

    public void search(ArrayList<Dealer> dealers, String ID) {
        ArrayList<Dealer> newList = new ArrayList<Dealer>();
        for (Dealer dealer : this) {
            if (dealer.getID().equalsIgnoreCase(ID)) {
                newList.add(dealer);
            }
        }

        if (newList.isEmpty()) {
            System.out.println("Dealer does not exist");
        } else {
            newList.sort(new Comparator<Dealer>() {

                @Override
                public int compare(Dealer o1, Dealer o2) {
                    return o1.getID().compareTo(o2.getID());
                }
            });

            for (int i = 0; i < newList.size(); i++) {
                Dealer p = newList.get(i);
                System.out.println(p); 
                
            }
        }
    }

    // Add new Dealer
    public void addDealer() {
        String ID, name, address, phone;
        boolean continuing;
        int pos;

        do {
            ID = Utils.readPattern("ID of new dealer", Dealer.ID_FORMAT).toUpperCase();
            pos = isDuplicated(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);

        name = Utils.readNonBlank("Name of new dealer").toUpperCase();
        address = Utils.readNonBlank("Address of new dealer");
        phone = Utils.readPattern("Phone number", Dealer.PHONE_FORMAT);
        continuing = Utils.readBool("Is continuing?");

        Dealer dealer = new Dealer(ID, name, address, phone, continuing);
        this.add(dealer);
        changed = true;
    }

    // Remove Dealer - Input ID
    public void removeDealer() {
        String id;
        int pos;
        do {
            id = Utils.readPattern("Enter ID of dealer", Dealer.ID_FORMAT);
            pos = isDuplicated(id);
            if (pos < 0) {
                System.out.println("ID not found!");
            }
        } while (pos < 0);

        Iterator<Dealer> it = this.iterator();
        while (it.hasNext()) {
            Dealer dealer = it.next();
            if (dealer.getID().equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Delete successfully!");
                changed = true;
            }
        }
    }

    /*
	 * Update a Dealer
	 * Only 'name', 'address' and 'phone' can be changed
     */
    public void updateDealer() {
        String ID = Utils.readPattern("Dealer's ID needs updating", Dealer.ID_FORMAT);
        int pos = isDuplicated(ID);

        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer dealer = this.get(pos);
            String newName = "";
            System.out.print("New name: ");
            newName = Utils.sc().nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                dealer.setName(newName);

            }
            System.out.print("New phone: ");
            String newPhone;
            newPhone = Utils.sc().nextLine().trim().toUpperCase();
            if (!newPhone.isEmpty()) {
                dealer.setPhone(newPhone);
        }
            System.out.print("New address: ");
            String newAddress;
            newAddress = Utils.sc().nextLine().trim().toUpperCase();
            if (!newAddress.isEmpty()) {
                dealer.setAddress(newAddress);
    }
                            changed = true;
        }
    }

    // Print all Dealers

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println("------------- Dealers from List ---------------");
            for (Dealer dealer : this) {
                System.out.println(dealer);
            }
        }

        System.out.println("------------- Dealers from File ---------------");
        printListFile();
    }

    private void printListFile() {
        List<String> dealers = Utils.readLinesFromFile(dataFile);
        for (String str : dealers) {
            System.out.println(str);
        }
    }

    // Print all continuing Dealers
    public void printContinuingDealers() {
        for (Dealer d : this) {
            if (d.isContinuing()) {
                System.out.println(d);
            }
        }
    }

    // Print all un-continuing Dealers
    public void printUnContinuingDealers() {
        for (Dealer d : this) {
            if (!d.isContinuing()) {
                System.out.println(d);
            }
        }
    }

    // Write dealer list to file
    public void writeDealerToFile() {
        if (changed) {
            Utils.writeFile(dataFile, this);
            System.out.println("Write successfully!");
            changed = false;
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
