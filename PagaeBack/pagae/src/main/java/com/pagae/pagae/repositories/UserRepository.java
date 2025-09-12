package com.pagae.pagae.repositories;

import com.pagae.pagae.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}