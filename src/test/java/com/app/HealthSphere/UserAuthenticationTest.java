package com.app.HealthSphere;

import com.app.HealthSphere.model.UserAuthentication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class UserAuthenticationTests {

    @Test
    void testUserAuthenticationCreation() {
        try {
            UserAuthentication userAuth = new UserAuthentication(1L, "john_doe", "john.doe@example.com", "hashed_password", "ADMIN");

            assertNotNull(userAuth);
            assertEquals(1L, userAuth.getUserId());
            assertEquals("john_doe", userAuth.getUsername());
            assertEquals("john.doe@example.com", userAuth.getEmail());
            assertEquals("hashed_password", userAuth.getPasswordHash());
            assertEquals("ADMIN", userAuth.getRole());
            assertTrue(userAuth.getIsActive());
            assertNotNull(userAuth.getAccountCreated());
        } catch (Exception e) {
            fail("Exception occurred during testUserAuthenticationCreation: " + e.getMessage());
        }
    }

    @Test
    void testSettersAndGetters() {
        try {
            UserAuthentication userAuth = new UserAuthentication();

            userAuth.setUserId(2L);
            userAuth.setUsername("jane_doe");
            userAuth.setEmail("jane.doe@example.com");
            userAuth.setPasswordHash("securepassword");
            userAuth.setRole("USER");
            userAuth.setLastLogin(LocalDateTime.now().minusDays(1));
            userAuth.setAccountCreated(LocalDateTime.now().minusDays(30));
            userAuth.setIsActive(false);

            assertEquals(2L, userAuth.getUserId());
            assertEquals("jane_doe", userAuth.getUsername());
            assertEquals("jane.doe@example.com", userAuth.getEmail());
            assertEquals("securepassword", userAuth.getPasswordHash());
            assertEquals("USER", userAuth.getRole());
            assertFalse(userAuth.getIsActive());
        } catch (Exception e) {
            fail("Exception occurred during testSettersAndGetters: " + e.getMessage());
        }
    }

    @Test
    void testInvalidUserId() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setUserId(-1L);
        });
    }

    @Test
    void testInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setUsername("");
        });
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setEmail("invalid-email");
        });
    }

    @Test
    void testInvalidPasswordHash() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setPasswordHash("short");
        });
    }

    @Test
    void testInvalidRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setRole("GUEST");
        });
    }

    @Test
    void testInvalidLastLogin() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserAuthentication userAuth = new UserAuthentication();
            userAuth.setLastLogin(LocalDateTime.now().plusDays(1));
        });
    }
}
