package controller;

import user.User;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private static final UserController instance = new UserController();
    private UserService userService = UserService.instance;

    private UserController() {
    }

    private void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write user name");
        String name = scanner.nextLine();
        System.out.println("Please write user surname");
        String surname = scanner.nextLine();
        System.out.println("Plese write user password");
        String password = scanner.nextLine();
        User user = new User(name, surname, password);
        userService.create(user);
    }

    private void selectUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write user id");
        int id = scanner.nextInt();
        System.out.println(userService.findUserById(id));
    }

    private void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Plese write user id");
        int id = scanner.nextInt();
        String test = scanner.nextLine();
        System.out.println("Plese write new name");
        String name = scanner.nextLine();
        System.out.println("Plese write new surname");
        String surname = scanner.nextLine();
        System.out.println("Please write new password");
        String password = scanner.nextLine();
        userService.update(id, new User(name, surname, password));
    }

    private void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write user id");
        int id = scanner.nextInt();
        userService.delete(id);
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write username");
        String username = scanner.nextLine();
        System.out.println("Please write user password");
        String password = scanner.nextLine();
        if (userService.login(username, password)) {
            System.out.println("You are login");
            System.out.println("________________________________________");
            System.out.println("     Save or not save password          ");
            System.out.println();
            System.out.println("   Press 1 save your password        ");
            System.out.println("   Press 2 unsaved your password     ");
            System.out.println("   Press 3 only this turn            ");
            System.out.println("________________________________________");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    System.out.println("save your password");
                    break;
                case 2:
                    System.out.println("never save my password");
                    break;
                case 3:
                    System.out.println("only this turn");
                    break;
                default:
                    System.out.println("Unvalid order!!!!!!!!!!");
                    break;
            }

        } else {
            System.out.println("Unvalid password or username");
        }
    }

    public static void menu() {
        UserController userController = UserController.instance;
        int num;
        do {
            System.out.println("______________________________");
            System.out.println("              Menu            ");
            System.out.println("   Press 1 to create user     ");
            System.out.println("   Press 2 to select user     ");
            System.out.println("   Press 3 to update user     ");
            System.out.println("   Press 4 to delete user     ");
            System.out.println("   Press 5 to login user      ");
            System.out.println("   Press 6 to exit            ");
            System.out.println("______________________________");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    userController.createUser();
                    break;
                case 2:
                    userController.selectUser();
                    break;
                case 3:
                    userController.updateUser();
                    break;
                case 4:
                    userController.deleteMovie();
                    break;
                case 5:
                    userController.login();
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Plese write valid order");
            }
        } while (num != 6);
    }
}
