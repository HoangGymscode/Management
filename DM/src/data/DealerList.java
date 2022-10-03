package data;

import Utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
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
	
	/*
	 * Get the list of continuing dealers
	 * Create new result list belonging to DealerList
	 */
	public DealerList getContinuingList() {
		return null;
	}
	
	// Get the list of un-continuing dealers
	public DealerList getUnContinuingList() {
		return null;
	}
	
	// Search Dealer with ID - Use Linear Search
	private int searchDealer(String ID) {
		return -1;
	}
	
	/*
	 * Search Dealer - Use Linear Search
	 * Input String ID
	 */
	public void searchDealer() {
		
	}
	
	// Add new Dealer
	public void addDealer() {
		String ID, name, address, phone;
		boolean continuing;
		int pos;
		
		do {
			ID = Utils.readPattern("ID of new dealer", Dealer.ID_FORMAT).toUpperCase();
			pos = searchDealer(ID);
			if (pos >= 0) System.out.println("ID is duplicated!");
		} while (pos >= 0);
		
		name = Utils.readNonBlank("Name of new dealer").toUpperCase();
		address = Utils.readNonBlank("Address of new dealer");
		phone = Utils.readPattern("Phone number", Dealer.PHONE_FORMAT);
		continuing = Utils.readBool("Is continuing?");
		
		Dealer dealer = new Dealer(ID, name, address, phone, continuing);
		this.add(dealer);
	}
	
	// Remove Dealer - Input ID
	public void removeDealer() {
		
	}
	
	/*
	 * Update a Dealer
	 * Only 'name', 'address' and 'phone' can be changed
	 */
	public void updateDealer() {
		System.out.print("Dealer's ID needs updating");
		String ID = Utils.sc().nextLine();
		int pos = searchDealer(ID);
		
		if (pos < 0) System.out.println("Dealer " + ID + " not found!");
		else {
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
	
	// Print all Dealers
	public void printAllDealers() {
		if (this.isEmpty()) System.out.println("Empty List!");
		else {
			for (Dealer dealer : this) {
				System.out.println(dealer);
			}
		}
	}
	
	// Print all continuing Dealers
	public void printContinuingDealers() { 
		this.getContinuingList().printContinuingDealers();
	}
	
	// Print all un-continuing Dealers
	public void printUnContinuingDealers() {
		
	}
	
	// Write dealer list to file
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
