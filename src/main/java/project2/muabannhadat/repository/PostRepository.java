package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByUserName(String username);

    Post findPostByArticleId(Long id);

    List<Post> findAllByUserName(String username);
}
