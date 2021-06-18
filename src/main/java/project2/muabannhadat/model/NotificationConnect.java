package project2.muabannhadat.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notification_connect")
public class NotificationConnect {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "no_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "message")
    private String message;

    @Column(name = "link")
    private String link;

    @Column(name = "seen")
    @Value("${someNumber:0}")
    private Integer seen;
}
