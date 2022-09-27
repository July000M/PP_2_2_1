package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        System.out.println(userService.getUserByCar("Nissan", 9));
        System.out.println(userService.getUsersByCar("Nissan", 3));

        userService.add(new User(new Car("Nissan", 1), "User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User(new Car("Ford", 2), "User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User(new Car("Nissan", 3), "User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User(new Car("BMW", 4), "User4", "Lastname4", "user4@mail.ru"));

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        context.close();
    }
}
