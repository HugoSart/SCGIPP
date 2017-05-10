package scgipp;

import scgipp.service.user_management.User;
import scgipp.service.user_management.UserDAO;

import java.util.List;


public class Teste {

    public static void main(String args[]) {

        UserDAO userDAO = new UserDAO();



        userDAO.close();

    }

}
