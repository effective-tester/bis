package ru.itmo.bis.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itmo.bis.backend.constant.UserRole;

@Entity
@Table(name = "landlord")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Landlord extends User {

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @Override
  public UserRole getRole() {
    return UserRole.LANDLORD;
  }
}
