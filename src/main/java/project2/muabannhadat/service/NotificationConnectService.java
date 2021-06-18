package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.NotificationConnect;
import project2.muabannhadat.repository.NotifictionConnectReponsitory;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationConnectService {
    @Autowired
    NotifictionConnectReponsitory reponsitory;

    public void addNotifi(NotificationConnect connect){
        reponsitory.save(connect);
    }

    public List<NotificationConnect> getByUsername(String username){
        List<NotificationConnect> list = new ArrayList<>();

        list = reponsitory.findAllByUsername(username);

        if (list.isEmpty()) return new ArrayList<>();
        return list;
    }

    public List<NotificationConnect> getByUsernameAndSeen(String username, Integer seen){
        List<NotificationConnect> list = reponsitory.findAllByUsernameAndSeen(username, seen);
        return list;
    }

    public void deleteById(Long id){
        NotificationConnect connect = reponsitory.findNotificationConnectById(id);
        reponsitory.deleteById(id);
    }

    public void setSeen(Long id){
        NotificationConnect connect = reponsitory.findNotificationConnectById(id);
        connect.setSeen(1);
        reponsitory.save(connect);
    }

    public NotificationConnect seeNoti(Long id){
        return reponsitory.findNotificationConnectById(id);
    }

    public Integer countNotiNew(String username){
        List<NotificationConnect> list = reponsitory.findAllByUsernameAndSeen(username, 0);
        return list.size();
    }
}
