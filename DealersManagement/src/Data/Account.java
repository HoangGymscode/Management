
package Data;

/**
 *
 * @author HOANG
 */
public class Account {
    private String name;
	private String password;
	private String role;
	
	// Constructor
	public Account(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + ",Password: " + password + ",Role: " + role;
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
