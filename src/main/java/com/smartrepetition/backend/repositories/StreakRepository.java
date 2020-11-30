package com.smartrepetition.backend.repositories;

import com.smartrepetition.backend.models.Streak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StreakRepository extends JpaRepository<Streak, Long> {
}
