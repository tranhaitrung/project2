package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Image;
import project2.muabannhadat.model.PostArticle;

import java.util.List;

@Repository
public interface PostArticleRepository extends JpaRepository<PostArticle, Long> {
    PostArticle findPostArticleByArticleId(Long articleId);


    List<PostArticle> findListByArticleId(Long id);
}
