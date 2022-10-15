package AccessFile;

import Subjects.Account;
import Utils.Utils;
import java.util.List;

/**
 *
 * @author HOANG
 */
//crete Class tools for the LogIn
public class AccountChecker {

    private String accountFile;

    public AccountChecker() {
        setupAccountFile();
    }

    private void setupAccountFile() {
        Config config = new Config();
        accountFile = config.getAccountFile();
    }

    // Check validity of an account
    public boolean check(Account account) {
        List<String> lines = Utils.readLinesFromFile(accountFile);
        for (String line : lines) {
            String[] parts = line.split(Account.SEPARATOR);
            if (parts.length <3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(account.getName())
                    && parts[1].equals(account.getPassword())
                    && parts[2].equalsIgnoreCase(account.getRole())) {
                return true;
            }
        }
    
        return false;
    }
}
