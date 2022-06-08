package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User userFounded = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                userFounded = user;
                break;
            }
        }
        if (userFounded == null) {
            throw new UserNotFoundException("User " + login + " not found");
        }
        return userFounded;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("User " + user.getUsername() + " invalid");
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ex) {
            System.out.println(ex.getMessage());
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
