package com.smartrepetition.backend.controllers;

import com.smartrepetition.backend.models.Item;
import com.smartrepetition.backend.services.ItemService;
import java.util.Base64;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping("/items")
  public Item addItem(@RequestBody Item item) {
    Item savedItem = itemService.save(item);
    savedItem.setSound(null);
    savedItem.setImage(null);
    return savedItem;
  }

  @GetMapping("/items/{id}")
  public Item getItem(@PathVariable Long id) {
    return itemService.getById(id);
  }

  @GetMapping("/items/{id}/sound")
  public String getItemSound(@PathVariable Long id) {
    byte[] sound = itemService.getById(id).getSound();
    if (sound == null) {
      return "";
    }
    return Base64.getEncoder().encodeToString(sound);
  }

  @GetMapping("/items/{id}/image")
  public String getItemImage(@PathVariable Long id) {
    byte[] image = itemService.getById(id).getImage();
    if (image == null) {
      return "";
    }
    return Base64.getEncoder().encodeToString(image);
  }

  @GetMapping("/items/user/{userId}")
  public List<Item> getItemsByUserId(@PathVariable Long userId) {
    return itemService.getItemsByUserId(userId);
  }

  @GetMapping("/items/count/user/{userId}")
  public int getItemCountByUserId(@PathVariable Long userId) {
    return itemService.getItemCountByUserId(userId);
  }
}
