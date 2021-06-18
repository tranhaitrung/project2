package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.NotificationConnect;

import java.util.List;

@Repository
public interface NotifictionConnectReponsitory extends JpaRepository<NotificationConnect, Long> {
    List<NotificationConnect> findAllByUsername(String username);

    List<NotificationConnect> findAllByUsernameAndSeen(String username, Integer seen);

    NotificationConnect findNotificationConnectById(Long id);
}
