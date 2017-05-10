package scgipp;

import scgipp.service.user_management.User;
import scgipp.service.user_management.UserDAO;

import java.util.List;


public class Teste {

    public static void main(String args[]) {

        UserDAO userDAO = new UserDAO();

        User user = userDAO.get("new3");
        System.out.println("Pass: " + user.getPassword());

        user.setPassword("456");
        userDAO.update(user);

        user = userDAO.get("new3");
        System.out.println("Pass: " + user.getPassword());

        userDAO.close();

    }

}
