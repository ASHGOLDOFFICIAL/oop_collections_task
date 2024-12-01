package ru.naumen.collection.task2;

import java.util.Arrays;
import java.util.Objects;

/**
 * Пользователь
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public final class User {
    private final String username;
    private final String email;
    private final byte[] passwordHash;

    public User(
            String username,
            String email,
            byte[] passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.email, that.email) &&
                Arrays.equals(this.passwordHash, that.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, Arrays.hashCode(passwordHash));
    }

    @Override
    public String toString() {
        return "User[" +
                "username=" + username + ", " +
                "email=" + email + ", " +
                "passwordHash=" + Arrays.toString(passwordHash) + ']';
    }

}
