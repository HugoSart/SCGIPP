package scgipp.service.user_management;

public class UserController {

    public UserController() {}

    /** Call the database method to retrieve the correspondent user if the credentials matchs
     * @return The correspondent user **/
    public static User authenticate(String login, String password) {
        //TODO: Call the authentication method of the database class
        return new User((long)0, login);
    }

    /** Register the login and the password and create a new User in the database
     * @return True if the user was succefully registered **/
    public static boolean register(String login, String password) {
        //TODO: Verify if already exists this login
        //TODO: Add user to database
        return false;
    }

}
