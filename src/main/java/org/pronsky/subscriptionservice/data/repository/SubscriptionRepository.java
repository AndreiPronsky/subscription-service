package org.pronsky.subscriptionservice.data.repository;

import org.pronsky.subscriptionservice.data.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = """
            SELECT * FROM subscriptions s
            LEFT JOIN users_subscriptions us ON s.id = us.subscription_id
            GROUP BY s.id
            ORDER BY COUNT(us.user_id) DESC
            LIMIT 3
            """, nativeQuery = true)
    List<Subscription> findTop3MostPopularSubscriptions();
}
