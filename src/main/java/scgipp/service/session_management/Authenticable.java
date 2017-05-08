package scgipp.service.session_management;

public interface Authenticable {

    boolean authenticate(String login, String password);

}
