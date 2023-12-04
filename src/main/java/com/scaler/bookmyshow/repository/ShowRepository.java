package com.scaler.bookmyshow.repository;

import com.scaler.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {

    Optional<Show> findById(Long aLong);
}
