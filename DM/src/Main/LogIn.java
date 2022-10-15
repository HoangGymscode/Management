package Main;

import Utils.Utils;
import Subjects.Account;
import AccessFile.AccountChecker;
import Function.Boss;
import Function.DealerList;
import Function.DeliveryList;

/**
 *
 * @author HOANG
 */
public class LogIn {

    private Account account = null;	// Account will login

    // Constructor
    public LogIn(Account account) {
        this.account = account;
    }

    /*
	 * Input data of account. Create new Account
	 * Return this account
     */
    public static Account inputAccount() {
        System.out.println("Please Login to System.");
        String accountName = Utils.readNonBlank("Your account name");
        String accountPassword = Utils.readNonBlank("Your password");
        String accountRole = Utils.readPattern("Your role [BOSS, ACC-1, ACC-2]", "BOSS|ACC-1|ACC-2");
        
        
        
        return new Account(accountName, accountPassword, accountRole);
    }

    public static void main(String[] args) {
        AccountChecker checker = new AccountChecker();

        Account account = null;	// account w-;6ill login
        boolean cont = false;	// login again?
        boolean valid = false;	// valid account or not

        do {
            account = inputAccount();
            valid = checker.check(account);

            if (valid) {
                System.out.println("Login successfully!");

                LogIn logIn = new LogIn(account);	// create a logIn object for valid account

                if (account.getRole().equalsIgnoreCase("ACC-1")) {
                    runDealer();
                    cont = checkReturn();
                } else if (account.getRole().equalsIgnoreCase("ACC-2")) {
                    runDelivery();
                    cont = checkReturn();
                } else if (account.getRole().equalsIgnoreCase("BOSS")) {
                    runBoss();
                    cont = checkReturn();
                } else {
                    System.out.println("Login with " + account.getRole());
                    cont = checkReturn();
                }
            } else {
                cont = Utils.readBool("Invalid account - Try again?");
                if (!cont) {
                    System.out.println("Good bye!");
                    System.exit(0);
                }
            }
        } while (cont);
    }

    private static boolean checkReturn() {
        boolean result = Utils.readBool("Do you want to continue?");
        return result;
    }

    private static void runBoss() {
        String[] options = {"Add new account",
            "Remove account",
            "Change username",
            "Change password",
            "Change role",
            "Print all accounts"};

        Menu menu = new Menu(options);
        Boss boss = new Boss();
        int choice = 0;

        do {
            choice = menu.getChoice("Managing BOSS");
            switch (choice) {
                case 1:
                    boss.addAccount();
                    break;
                case 2:
                    boss.deleteAccount();
                    break;
                case 3:
                    boss.changeUsername();
                    break;
                case 4:
                    boss.changePassword();
                    break;
                case 5:
                    boss.changeRole();
                    break;
                case 6:
                    boss.printAllAccounts();
                    break;
                default:
                    break;
            }
        } while (choice > 0 && choice <= menu.size());

        System.out.println("Bye.");
    }

    private static void runDelivery() {
        String[] options = {"Add new delivery",
            "Search a delivery",
            "Remove a delivery",
            "Update a delivery",
            "Print all deliveries",
            "Print continuing deliveries",
            "Print UN-continuing deliveries",
            "Write to file"};

        Menu menu = new Menu(options);
        DeliveryList deliveryList = new DeliveryList();
        deliveryList.initWithFile();
        int choice = 0;

        do {
            choice = menu.getChoice("Managing login");
            switch (choice) {
                case 1:
                    deliveryList.addDelivery();
                    break;
                case 2:
                    deliveryList.searchDelivery();
                    break;
                case 3:
                    deliveryList.removeDelivery();
                    break;
                case 4:
                    deliveryList.updateDelivery();
                    break;
                case 5:
                    deliveryList.printAllDeliveries();
                    break;
                case 6:
                    deliveryList.printContinuingDeliveries();
                    break;
                case 7:
                    deliveryList.printUnContinuingDealers();
                    break;
                case 8:
                    deliveryList.writeDeliveryToFile();
                    break;
                default:
                    if (deliveryList.isChanged()) {
                        boolean isWrite = Utils.readBool("Data changed. Write to file?");
                        if (isWrite) {
                            deliveryList.writeDeliveryToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= menu.size());

        System.out.println("Bye.");
    }

    private static void runDealer() {
        String[] options = {"Add new dealer",
            "Search a dealer",
            "Remove a dealer",
            "Update a dealer",
            "Print all dealers",
            "Print continuing dealers",
            "Print UN-continuing dealers",
            "Write to file"};

        Menu menu = new Menu(options);
        DealerList dealerList = new DealerList();
        dealerList.initWithFile();
        int choice = 0;

        do {
            choice = menu.getChoice("Managing dealer");
            switch (choice) {
                case 1:
                    dealerList.addDealer();
                    break;
                case 2:
                    dealerList.searchDealer();
                    break;
                case 3:
                    dealerList.removeDealer();
                    break;
                case 4:
                    dealerList.updateDealer();
                    break;
                case 5:
                    dealerList.printAllDealers();
                    break;
                case 6:
                    dealerList.printContinuingDealers();
                    break;
                case 7:
                    dealerList.printUnContinuingDealers();
                    break;
                case 8:
                    dealerList.writeDealerToFile();
                    break;
                default:
                    if (dealerList.isChanged()) {
                        boolean isWrite = Utils.readBool("Data changed. Write to file?");
                        if (isWrite) {
                            dealerList.writeDealerToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= menu.size());

        System.out.println("Bye.");
    }

    // Getters and Setters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
