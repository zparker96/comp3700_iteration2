
public class User {
	private String userName;
	private String displayName;
	private boolean isManager;
	private int userID;
	private String password;
	// private Image profilePicture;
	
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public boolean getRole() {
        return isManager;
    }

    public void setRole(boolean isManager) {
        this.isManager = isManager;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
