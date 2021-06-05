package project2.muabannhadat.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long imageId;

    @NotEmpty(message = "*Not null")
    @Column(name = "image_url")
    private String imageContent;

    public Image(String encodeString) {
        this.imageContent = encodeString;
    }

    public Image() {

    }
}
