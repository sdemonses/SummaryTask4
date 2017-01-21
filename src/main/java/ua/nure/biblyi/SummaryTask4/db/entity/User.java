package ua.nure.biblyi.SummaryTask4.db.entity;

import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;

/**
 * @author Biblyi Dmytro
 */
public class User extends Entity {
    private static final long serialVersionUID = 2810730893419191221L;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private UserStatus userStatus;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = Role.getRole(role);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int id) {
        this.userStatus = UserStatus.getType(id);
    }
}
