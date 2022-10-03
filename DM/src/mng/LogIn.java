package mng;

import Utils.Utils;
import data.Account;
import data.AccountChecker;
import data.DealerList;

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
        String accountName = Utils.getString("Enter your name: ");
        String accountPassword = Utils.getString("Enter your password: ");
        String accountRole = Utils.getString("Enter your Role: ");
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

            String[] options = {"Add new dealer",
                "Search a dealer",
                "Remove a dealer",
                "Update a dealer",
                "Print all dealers",
                "Print continuing dealers",
                "Print UN-continuing dealers",
                "Write to file"};
            Menu menu = new Menu(options);
            DealerList dealerList = new DealerList(logIn);
            dealerList.initWithFile();
            int choice = 0;
            Menu:
            do {

                choice = menu.getChoice("Your choice: ");
                switch (choice) {
                    case 1:
                        dealerList.addDealer();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 2:
                        dealerList.searchDealer();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 3:
                        dealerList.removeDealer();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 4:
                        dealerList.updateDealer();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 5:
                        dealerList.printAllDealers();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 6:
                        dealerList.printContinuingDealers();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 7:
                        dealerList.printUnContinuingDealers();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 8:
                        dealerList.writeDealerToFile();
                        if (Utils.CheckReturn()) {
                            continue Menu;
                        }
                        break;
                    case 9:
                        System.out.println(" ===========>>>>>    Bye Bye Baby <3 <3 <3 ");
                        System.exit(0);
//                    default:
//                        if (dealerList.isChanged()) {
//                            boolean res = Utils.readBool("Data changed. Write to file?");
//                            if (res == true) {
//                                dealerList.writeDealerToFile1();
//                            }
//                        }
                }
            } while (choice > 0 && choice < menu.size());

            System.out.println("Bye.");

        }
    }

}
