package Logic;
//Victor
public class LoginChecker {
    public static boolean isAdmin;
    public static String username;

    public static boolean loginChecker(String username, String password){

        if ( "Admin".equals(username) && "1234".equals(password)){
            LoginChecker.isAdmin = true;
            LoginChecker.username = username;
            System.out.println("du er logget ind som admin, admin status =" + isAdmin);
            return true;
        }
        else if ( "123".equals(password)) {
            LoginChecker.isAdmin = false;
            LoginChecker.username = username;
            System.out.println("du er logget ind som sælger, admin status =" + isAdmin);
            return true;
        }
        else {
            return false;
        }
    }
}
