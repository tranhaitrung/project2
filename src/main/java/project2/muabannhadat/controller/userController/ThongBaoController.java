package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project2.muabannhadat.model.NotificationConnect;
import project2.muabannhadat.service.NotificationConnectService;

@RestController
public class ThongBaoController {

    @Autowired
    NotificationConnectService notificationConnectService;

    @GetMapping("/xem-thong-bao/{id}")
    public NotificationConnect xemThongBao(@PathVariable("id") Long id){
        NotificationConnect notificationConnect = notificationConnectService.seeNoti(id);
        notificationConnectService.setSeen(id);
        return notificationConnect;
    }

    @PostMapping("/them-thong-bao")
    public ResponseEntity themThongBao(@RequestBody NotificationConnect connect){
        connect.setSeen(0);
        notificationConnectService.addNotifi(connect);
        return ResponseEntity.ok().body(connect);
    }
}
