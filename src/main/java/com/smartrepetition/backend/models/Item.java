package com.smartrepetition.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  private String description;
  private ZonedDateTime createTime;

  @Lob
  @ToString.Exclude
  @Basic(fetch = FetchType.LAZY)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private byte[] sound;

  @Lob
  @ToString.Exclude
  @Basic(fetch = FetchType.LAZY)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private byte[] image;
  private String hint;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Column(name = "user_id")
  private Long userId;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  @Builder.Default
  private List<Progress> progresses = new ArrayList<>();

  public Item addProgress(Progress progress) {
    if (progress.getItem() == null) {
      progress.setItemId(this.id);
    }
    progresses.add(progress);
    return this;
  }
}
