package project2.muabannhadat.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "image")
    private String  image;
}
