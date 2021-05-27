package Logic;
//Victor
public class LoginChecker {
    public static boolean isAdmin = false;
    public static String username;

    public static boolean loginChecker(String username, String password){

        if ( "Admin".equals(username) && "6969".equals(password)){
            LoginChecker.setAdmin(true);
            LoginChecker.username = username;
            return true;
        }
        else if ( "123".equals(password)) {
            LoginChecker.username = username;
            return true;
        }
        else {
            //System.out.println("Password or Username is incorrect");
            return false;
        }

    }
    /*public void setUsername (String usrname) {
        username = usrname;
    }
    public String getUsername() {
        return username;
    }*/

    private static boolean setAdmin(boolean admin) {
        if (isAdmin == true) {
            admin = true;
        }
        else admin = false;

        return admin;
    }
    public static boolean getAdminStatus() {
        if (isAdmin == true){
            return true;
        }
        else return false;

    }
}
