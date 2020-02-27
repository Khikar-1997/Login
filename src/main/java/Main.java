import controller.UserController;
import database.DatabaseInitializor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializor instance = DatabaseInitializor.instance;
        instance.createDatabase();
        UserController.menu();
    }
}
