package com.ecommerce.ecommerce.users.repository;

import com.ecommerce.ecommerce.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
