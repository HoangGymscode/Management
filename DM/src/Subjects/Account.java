package Subjects;

/**
 *
 * @author HOANG
 */
public class Account {

public static final String SEPARATOR = ",";
	
	private String name;
	private String password;
	private String role;
	
	// Constructor
	public Account(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public Account(String line) {
		String[] parts = line.split(SEPARATOR);
		
		name = parts[0].trim();
		password = parts[1].trim();
		role = parts[2].trim();
	}
	
	@Override
	public String toString() {
		return name + SEPARATOR + password + SEPARATOR + role;
	}

	// Getters and Setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
