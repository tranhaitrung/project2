package project2.muabannhadat.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post_user")
@Data
public class Post {
    @Id
    @Column(name = "post_user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postUserId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "article_id")
    private Long articleId;

    public Post(String userName, Long articleId) {
        this.userName = userName;
        this.articleId = articleId;
    }

    public Post() {

    }
}
