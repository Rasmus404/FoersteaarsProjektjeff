package Logic;

//Victor
public class LoginChecker {
    public static boolean isAdmin;
    public static String username;

    public static boolean loginChecker(String username, String password) {

        if ("Admin".equals(username) && "1234".equals(password)) {
            LoginChecker.isAdmin = true;
            LoginChecker.username = username;
            return true;

        } else if ("123".equals(password)) {
            LoginChecker.isAdmin = false;
            LoginChecker.username = username;
            return true;

        } else {
            return false;
        }
    }
}
