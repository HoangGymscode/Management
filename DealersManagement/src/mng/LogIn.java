
package mng;

import Data.Account;
import Data.AccountChecker;
import Utils.Utils;
import Data.DealerList;
/**
 *
 * @author HOANG
 */
public class LogIn {
    private Account account = null;

	// Constructor
	public LogIn(Account account) {
		this.account = account;
	}
	
	public static Account inputAccount() {
		System.out.println("Please Login to System...!!!");
		String accountName = Utils.readNonBlank("Your enter account name: ");
		String accountPassword = Utils.readNonBlank("Your enter password: ");
		String accountRole = Utils.readNonBlank("Your enter role: ");
		return new Account(accountName, accountPassword, accountRole);
	}
	
	public static void main(String[] args) {
		Account account = null;
		boolean cont = false;
		boolean valid = false;
		
		do {
			AccountChecker accountChecker = new AccountChecker();
			account = inputAccount();
			valid = accountChecker.check(account);                        
			if (!valid) {
				cont = Utils.readBool("Invalid account - Try again?");
			} else {
				System.out.println("Login successfully!");
				break;
			}
			if (!valid && !cont) {
				System.out.println("Good bye!");
				System.exit(0);
			}
		} while (cont);
		          
		LogIn logIn = new LogIn(account);
		
		if (account.getRole().equalsIgnoreCase("ACC-1")) {
			System.out.println("Managing dealers:");
			
			String[] options = { "Add new dealer", 
								 "Search a dealer", 
								 "Remove a dealer", 
								 "Update a dealer", 
								 "Print all dealers", 
								 "Print continuing dealers", 
								 "Print UN-continuing dealers", 
								 "Write to file" };
			Menu menu = new Menu(options);
			DealerList dealerList = new DealerList(logIn);
			dealerList.initWithFile();
			int choice = 0;
			
			do {
				
				choice = menu.getChoice("Your choice: ");
				switch (choice) {
				case 1: dealerList.addDealer(); break;
				case 2: dealerList.searchDealer(); break;
				case 3: dealerList.removeDealer(); break;
				case 4: dealerList.updateDealer(); break;
				case 5: dealerList.printAllDealers(); break;
				case 6: dealerList.printContinuingDealers(); break;
				case 7: dealerList.printUnContinuingDealers(); break;
				case 8: dealerList.writeDealerToFile(); break;
				default:
					if (dealerList.isChanged()) {
						boolean res = Utils.readBool("Data changed. Write to file?");
						if (res == true) dealerList.writeDealerToFile();
					}
				}
			} while (choice > 0 && choice < menu.size());
			
			System.out.println("Bye.");
		}
	}

	// Getters and Setters
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
