package com.smartrepetition.backend.repositories;

import com.smartrepetition.backend.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
