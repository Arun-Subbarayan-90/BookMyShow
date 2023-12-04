package com.scaler.bookmyshow.repository;

import com.scaler.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long aLong);

    Optional<User> findUserByEmail (String email);

    User save(User entity);
}
