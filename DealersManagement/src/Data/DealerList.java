package Data;

import Utils.Utils;
import java.util.ArrayList;
import java.util.List;
import mng.LogIn;

/**
 *
 * @author HOANG
 */
public class DealerList extends ArrayList<Dealer> {

    private LogIn loginObj = null;
    private String dataFile = "";
    private boolean changed = false;	// Whether data in the list changed or not

    // Constructor using loginObj as a parameter
    public DealerList(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> dealers = Utils.readLinesFromFile(dataFile);
        for (String dealerString : dealers) {
            String[] dealerArray = dealerString.split(Dealer.SEPARATOR);

            String id = dealerArray[0];
            String name = dealerArray[1];
            String address = dealerArray[2];
            String phone = dealerArray[3];
            boolean continuing = Boolean.valueOf(dealerArray[4]);

            Dealer dealer = new Dealer(id, name, address, phone, continuing);
            this.add(dealer);
        }
    }

    public void initWithFile() {
        Config config = new Config();
        dataFile = config.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        return null;
    }

    private int searchDealer(String ID) {
        return -1;
    }

    public void searchDealer() {

    }

    public void addDealer() {
        String ID, name, address, phone;
        boolean continuing;
        int pos;

        do {
            ID = Utils.readPattern("ID of new dealer: ", Dealer.ID_FORMAT).toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);

        name = Utils.readNonBlank("Name of new dealer: ").toUpperCase();
        address = Utils.readNonBlank("Address of new dealer: ");
        phone = Utils.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = Utils.readBool("Is continuing? ");

        Dealer dealer = new Dealer(ID, name, address, phone, continuing);
        this.add(dealer);
    }

    public void removeDealer() {

    }

    public void updateDealer() {
        System.out.print("Dealer's ID needs updating: ");
        String ID = Utils.sc().nextLine();
        int pos = searchDealer(ID);

        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer dealer = this.get(pos);
            String newName = "";
            System.out.print("New name, ENTER for omitting: ");
            newName = Utils.sc().nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                dealer.setName(newName);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Dealer dealer : this) {
                System.out.println(dealer);
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printContinuingDealers();
    }

    public void printUnContinuingDealers() {

    }

    public void writeDealerToFile() {
        if (changed) {
            Utils.writeFile(dataFile, this);
            changed = false;
        }
    }

    // Getters and Setters
    public LogIn getLoginObj() {
        return loginObj;
    }

    public void setLoginObj(LogIn loginObj) {
        this.loginObj = loginObj;
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
