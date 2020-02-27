import java.util.Scanner;

public class UserController {
    public static final UserController instance = new UserController();
    private UserService userService = UserService.instance;
    private UserController(){
    }

    private void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write User name");
        String name = scanner.nextLine();
        System.out.println("Please write User surname");
        String surname = scanner.nextLine();
        System.out.println("Plese write user password");
        String password = scanner.nextLine();
        User user = new User(name,surname,password);
        userService.create(user);
    }

    private void selectUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write user id");
        int id = scanner.nextInt();
        userService.findUserById(id);
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
    }

    private void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write user id");
        int id = scanner.nextInt();
        userService.delete(id);
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write username");
        String username = scanner.nextLine();
        System.out.println("Please write user password");
        String password = scanner.nextLine();
        if (userService.login(username,password)){
            System.out.println("You are login\n" +
                    "save your password, or never\n" +
                    "only this turn");
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
        }while (num != 6);
    }
}
