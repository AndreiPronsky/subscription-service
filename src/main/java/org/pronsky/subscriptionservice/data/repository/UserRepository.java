package org.pronsky.subscriptionservice.data.repository;

import org.pronsky.subscriptionservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
