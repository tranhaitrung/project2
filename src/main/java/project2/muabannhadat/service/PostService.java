package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.Post;
import project2.muabannhadat.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public void addPost(Post post){
        List<Post> post1 = repository.findAllByUserName(post.getUserName());
        for (Post p : post1){
            if (p.getArticleId().equals(post.getArticleId())){
                post.setPostUserId(p.getPostUserId());
            }
        }
        repository.save(post);
    }

    public String findUsername(Long id){
        Post post = repository.findPostByArticleId(id);
        return post.getUserName();
    }

    public List<Long> findArticleIdByUsername(String username){
        List<Post> posts = repository.findAllByUserName(username);
        if(!posts.isEmpty()){
            List<Long> articleIds = new ArrayList<>();
            for (Post p : posts){
                articleIds.add(p.getArticleId());
            }
            return articleIds;
        }
        return new ArrayList<>();
    }

    public Post findByArticleId(Long id){
        Post post = repository.findPostByArticleId(id);
        return post;
    }

    public List<Post> findByUsername(String username){
        List<Post> posts =  repository.findAllByUserName(username);
        if (!posts.isEmpty()) return posts;
        return new ArrayList<>();
    }


    public void delete(Post post){
        repository.delete(post);
    }
}
