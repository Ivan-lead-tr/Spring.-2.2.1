package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        CarService carService = context.getBean(CarService.class);

        carService.add(new Car("BMW", 6));
        carService.add(new Car("Opel", 9));
        carService.add(new Car("Pegout", 3008));
        carService.add(new Car("Porsche", 911));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("First Name = " + car.getModel());
            System.out.println("Last Name = " + car.getSeries());
            System.out.println();
        }

        for (int i = 0; i < users.size(); i++) {
            users.get(i).setCar(cars.get(i));
            userService.update(users.get(i));
        }

        users = userService.listUsers();
        System.out.println(users);

        System.out.println(userService.getUserByCar("BMW", 6));
        System.out.println(userService.getUserByCar("Pegout", 3008));
        System.out.println(userService.getUserByCar("Opel", 9));
        System.out.println(userService.getUserByCar("Porsche", 911));

        context.close();
    }
}
