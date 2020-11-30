package com.smartrepetition.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Progress {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private ZonedDateTime time;

  private boolean isPass;

  @ManyToOne
  @ToString.Exclude
  @JsonIgnore
  @JoinColumn(name = "item_id", insertable = false, updatable = false)
  private Item item;

  @Column(name = "item_id")
  private Long itemId;
}
