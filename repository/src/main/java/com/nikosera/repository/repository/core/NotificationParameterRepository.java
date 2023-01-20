package com.nikosera.repository.repository.core;

import com.nikosera.entity.NotificationParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Repository
public interface NotificationParameterRepository extends JpaRepository<NotificationParameter, Long> {

    @Query(value = "SELECT * from NOTIFICATION_PARAMETER n WHERE n.NOTIFICATION_ID= :notificationId",nativeQuery = true)
    List<NotificationParameter> fetchByNotificationId(@Param("notificationId") Long notificationId);
}

