package Model.SQL;

import Exceptions.PasswordTooWeakException;

public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this(username, password);
        this.id = id;
    }

    public
    User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public boolean validateUsername(String username) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (password == null || password.length() < 8) {
            throw new PasswordTooWeakException("Password is too weak. It must be at least 8 characters long.");
        }
        return true;
    }

    public boolean validatePassword(String password) throws Exception {
        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordTooWeakException("Password must contain at least one uppercase letter.");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new PasswordTooWeakException("Password must contain at least one number.");
        }

        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new PasswordTooWeakException("Password must contain at least one special character.");
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setAndValidateUsername(String username) throws Exception {
        validateUsername(username);
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAndValidatePassword(String password) throws Exception {
        validatePassword(password);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
