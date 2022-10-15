
package AccessFile;

import Utils.Utils;
import java.util.List;

/**
 *
 * @author HOANG
 */
public class Config {
   private static final String CONFIG_FILE = "data/config.txt";
	
	private String accountFile;
	private String dealerFile;
	private String deliveryFile;

	public Config() {
		readData();
	}

	private void readData() {
		List<String> lines = Utils.readLinesFromFile(CONFIG_FILE);
		for (String line : lines) {
			String[] parts = line.split(":");
			if (line.contains("accounts")) {
				accountFile = "data/" + parts[1].trim(); //
			} else if (line.contains("dealers")) {
				dealerFile = "data/" + parts[1].trim();
			} else if (line.contains("deliveries")) {
				deliveryFile = "data/" + parts[1].trim();
			}
		}
	}

	// Getters
	
	public String getAccountFile() {
		return accountFile;
	}

	public String getDealerFile() {
		return dealerFile;
	}

	public String getDeliveryFile() {
		return deliveryFile;
	}
}
