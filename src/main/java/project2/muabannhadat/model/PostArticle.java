package project2.muabannhadat.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_articles")
public class PostArticle {
    @Id
    @Column(name = "post_article_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postArticleId;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "image_id")
    private Long imageId;
}
