package com.nikosera.repository.repository.core;

import com.nikosera.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {





    @Query(value = "SELECT n from NOTIFICATION n WHERE n.STATUS=:status AND n.type=:type",nativeQuery =true )
    List<Notification> fetchNotificationToSend(@Param("status") String status,@Param("type") String type);
//
//    @Query(value = "SELECT * from notification n WHERE n.id=:id",nativeQuery =true )
//    Notification fetchNotificationById(@Param("id") Long id);
}

