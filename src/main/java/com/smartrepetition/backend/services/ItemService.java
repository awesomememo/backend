package com.smartrepetition.backend.services;

import com.smartrepetition.backend.models.Item;
import com.smartrepetition.backend.repositories.ItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ItemService {
  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public Item getById(Long id) {
    return itemRepository.findById(id).orElse(null);
  }

  public int getItemCountByUserId(Long userId) {
    return itemRepository.countItemsByUserId(userId);
  }

  public List<Item> getItemsByUserId(Long userId) {
    return itemRepository.getAllByUserId(userId);
  }
}
