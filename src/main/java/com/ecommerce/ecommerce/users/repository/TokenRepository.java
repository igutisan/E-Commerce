package com.ecommerce.ecommerce.users.repository;

import com.ecommerce.ecommerce.users.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query("SELECT t FROM tokens t WHERE t.user.id = :userId AND t.expired = false AND t.revoked = false")
    List<Token> findAllValidTokenByUser(@Param("userId") String userId);

    Optional<Token> findByToken(String token);
}
