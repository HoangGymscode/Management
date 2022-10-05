
package mng;

import Utils.Utils;
import java.util.ArrayList;

/**
 *
 * @author HOANG
 */
@SuppressWarnings("serial")
public class Menu  extends ArrayList<String> {
    public Menu() {
		super();
	}
	
	public Menu(String[] items) {
		super();
		for (String item : items) this.add(item);
	}
	
	public String getMenu() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result += "\t" + (i + 1) + "-" + get(i) + "\n";
		}
		return result;
	}
	
	// Get user choice
	public int getChoice(String title) {
		String message = getMenu();
		message += "\n" + title;
		int choice = Utils.readInt(message);
		return choice;
	}
}
