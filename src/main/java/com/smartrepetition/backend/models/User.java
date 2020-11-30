package com.smartrepetition.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  private String password;
  private int currStreak;
  private int longestStreak;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @Builder.Default
  private List<Streak> streaks = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @Builder.Default
  @JsonIgnore
  private List<Item> items = new ArrayList<>();

  public User addStreak(Streak streak) {
    if (streak.getUser() == null) {
      streak.setUser(this);
    }
    streaks.add(streak);
    return this;
  }

  public User addItem(Item item) {
    if (item.getUser() == null) {
      item.setUserId(this.id);
    }
    items.add(item);
    return this;
  }
}
