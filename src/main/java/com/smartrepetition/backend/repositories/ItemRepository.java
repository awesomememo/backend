package com.smartrepetition.backend.repositories;

import com.smartrepetition.backend.models.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
  int countItemsByUserId(Long userId);
  List<Item> getAllByUserId(Long userId);
}
