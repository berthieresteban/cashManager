package com.cashmanager.products.repository;
import com.cashmanager.products.model.User;
import java.util.Optional;

public interface UserRepositoryCustom<User, Long> {
    Optional<User> getUserByUsername(String username);
}