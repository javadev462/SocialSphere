package com.socialsphere.auth.entity;
import com.socialsphere.auth.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

    @Entity
    @Table(
            name = "users",
            uniqueConstraints = {
                    @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
                    @UniqueConstraint(name = "uk_users_username", columnNames = "username")
            }
    )
    @Getter
    @Setter
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false, length = 50)
        private String username;

        @Column(nullable = false, length = 100)
        private String email;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 20)
        private Role role;

        @Column(nullable = false)
        private boolean enabled = true;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        @PrePersist
        protected void onCreate() {
            LocalDateTime now = LocalDateTime.now();
            createdAt = now;
            updatedAt = now;
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }
    }


