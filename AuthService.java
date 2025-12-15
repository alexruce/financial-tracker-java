package services;

import models.User;
import java.util.ArrayList;

public class AuthService {
    private static ArrayList<User> users = new ArrayList<>();
    public static User currentUser;

    public static boolean register(String u, String p) {
        if (p.length() < 6 || p.contains(" ")) return false;
        for (User user : users)
            if (user.username.equals(u)) return false;
        users.add(new User(u, p));
        return true;
    }

    public static boolean login(String u, String p) {
        for (User user : users) {
            if (user.username.equals(u) && user.password.equals(p)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
}
