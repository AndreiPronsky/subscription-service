package org.pronsky.subscriptionservice.data.repository;

import org.pronsky.subscriptionservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE users SET active = false WHERE id = :userId", nativeQuery = true)
    void deactivateById(@Param("userId") Long userId);
}
