package Function;

import AccessFile.Config;
import Subjects.Account;
import Utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOANG
 */

//Create package Subject( join Boss)
public class Boss {

   private String accountFile = new Config().getAccountFile();
	private List<Account> accounts = new ArrayList<>();
	
	public Boss() {
		Config config = new Config();
		accountFile = config.getAccountFile();
		loadAccounts();
	}
	
	private void loadAccounts() {
		List<String> lines = Utils.readLinesFromFile(accountFile);
		for (String line : lines) {
			Account account = new Account(line);
			this.accounts.add(account);
		}
	}
	
	private int isDuplicated(String accountName) {
		for (int index = 0; index < this.accounts.size(); index++) {
			if (this.accounts.get(index).getName().equalsIgnoreCase(accountName)) {
				return index;
			}
		}

		return -1;
	}
	
	private String getAccountName() {
		String name;
		int pos;
		do {
			name = Utils.readNonBlank("Enter account name");
			pos = isDuplicated(name);
			if (pos < 0) System.out.println("Account name is not exists");
		} while(pos < 0);
		
		return name;
	}
	
	private String getNewAccountName() {
		String name;
		int pos;
		do {
			name = Utils.readNonBlank("Enter account name");
			pos = isDuplicated(name);
			if (pos >= 0) System.out.println("Account name is exists");
		} while(pos >= 0);
		
		return name;
	}
	
	public void printAllAccounts() {
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	public void addAccount() {
		String name = getNewAccountName();
		String password = Utils.readNonBlank("Enter account password");
		String role = Utils.readPattern("Enter account role [BOSS, ACC-1, ACC-2]", "BOSS|ACC-1|ACC-2");
		
		Account account = new Account(name, password, role);
		this.accounts.add(account);
		Utils.writeFile(accountFile, this.accounts);
		System.out.println("Add account successfully!");
	}
	
	public void deleteAccount() {
		String name = getAccountName();
		int pos = isDuplicated(name);
		if (pos > 0) {
			this.accounts.remove(pos);
		}
		
		Utils.writeFile(accountFile, accounts);
		System.out.println("Delete successfully!");
	}
	
	public void changePassword() {
		String name = getAccountName();
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				String newPassword = Utils.readNonBlank("Enter new password");
				account.setPassword(newPassword);
				System.out.println("Change password successfully!");
			}
		}
		
		System.out.println("Change username successfully!");
		Utils.writeFile(accountFile, accounts);
	}
	
	public void changeUsername() {
		String name = getAccountName();
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				String username = Utils.readNonBlank("Enter new username");
				account.setName(username);
			}
		}
		
		Utils.writeFile(accountFile, accounts);
		System.out.println("Change username successfully!");
	}
	
	public void changeRole() {
		String name = getAccountName();
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				String newRole = Utils.readNonBlank("Enter new role [BOSS, ACC-1, ACC-2]");
				account.setRole(newRole);
			}
		}
		
		Utils.writeFile(accountFile, accounts);
		System.out.println("Change role successfully!");
	}
}
