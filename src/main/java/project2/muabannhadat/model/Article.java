package project2.muabannhadat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long articleId;

    @NotEmpty(message = "*Không được để trống")
    private String title;

    private String title_unsigned;

    @NotEmpty(message = "*Không được để trống")
    private  String form;

    @NotEmpty(message = "*Không được để trống")
    private String species;

    @NotEmpty(message = "*Không được để trống")
    private String city;

    private String city_unsigned;

    @NotEmpty(message = "*Không được để trống")
    private String district;

    private String district_unsigned;

    @NotEmpty(message = "*Không được để trống")
    private String ward;

    private String ward_unsigned;

    private Float area;

    private Long price;

    @Value("${someNumber:0}")
    private Integer deleted;

    private Date date_up;

    private String detail;

    private String detail_unsigned;

}
