package com.app.HealthSphere;

import com.app.HealthSphere.model.UserAuthentication;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class UserAuthenticationTest {

    @Test
    public void testValidUserCreation() {
        UserAuthentication user = new UserAuthentication(1L, "JohnDoe", "john.doe@example.com", "Password123!", "ADMIN");
        assertEquals(1L, user.getUserId());
        assertEquals("JohnDoe", user.getUsername());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Password123!", user.getPasswordHash());
        assertEquals("ADMIN", user.getRole());
        assertTrue(user.getIsActive());
        assertNotNull(user.getAccountCreated());
    }


    @Test
    public void testDefaultRoleAsUser() {
        UserAuthentication user = new UserAuthentication(2L, "JaneDoe", "jane.doe@example.com", "SecurePass123!", null);
        assertEquals("USER", user.getRole());
    }


    @Test
    public void testUsernameValidation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAuthentication(3L, "JD", "jd@example.com", "Password123!", "USER")
        );
        assertEquals("Username must be at least 3 characters long.", exception.getMessage());
    }


    @Test
    public void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAuthentication(4L, "JohnDoe", "invalid-email", "Password123!", "USER")
        );
        assertEquals("Invalid email format.", exception.getMessage());
    }


    @Test
    public void testPasswordValidation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAuthentication(5L, "JohnDoe", "john.doe@example.com", "short", "USER")
        );
        assertEquals("Password must be at least 8 characters long.", exception.getMessage());
    }


    @Test
    public void testSetUsernameValid() {
        UserAuthentication user = new UserAuthentication(6L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        user.setUsername("NewUser123");
        assertEquals("NewUser123", user.getUsername());
    }

    @Test
    public void testSetUsernameInvalid() {
        UserAuthentication user = new UserAuthentication(7L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                user.setUsername("JD")
        );
        assertEquals("Username must be at least 3 characters long.", exception.getMessage());
    }


    @Test
    public void testSetEmailValid() {
        UserAuthentication user = new UserAuthentication(8L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        user.setEmail("new.email@example.com");
        assertEquals("new.email@example.com", user.getEmail());
    }

    @Test
    public void testSetEmailInvalid() {
        UserAuthentication user = new UserAuthentication(9L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                user.setEmail("invalid-email")
        );
        assertEquals("Invalid email format.", exception.getMessage());
    }


    @Test
    public void testSetPasswordValid() {
        UserAuthentication user = new UserAuthentication(10L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        user.setPasswordHash("NewStrongPass123!");
        assertEquals("NewStrongPass123!", user.getPasswordHash());
    }

    @Test
    public void testSetPasswordInvalid() {
        UserAuthentication user = new UserAuthentication(11L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                user.setPasswordHash("short")
        );
        assertEquals("Password must be at least 8 characters long.", exception.getMessage());
    }


    @Test
    public void testSetLastLogin() {
        UserAuthentication user = new UserAuthentication(12L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        LocalDateTime loginTime = LocalDateTime.now();
        user.setLastLogin(loginTime);
        assertEquals(loginTime, user.getLastLogin());
    }


    @Test
    public void testAccountCreatedDate() {
        UserAuthentication user = new UserAuthentication(13L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        assertNotNull(user.getAccountCreated());
    }


    @Test
    public void testSetIsActive() {
        UserAuthentication user = new UserAuthentication(14L, "JohnDoe", "john.doe@example.com", "Password123!", "USER");
        user.setIsActive(false);
        assertFalse(user.getIsActive());
    }
}
