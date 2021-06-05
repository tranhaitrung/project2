package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.PostArticle;
import project2.muabannhadat.repository.PostArticleRepository;

import java.util.List;

@Service
public class PostArticleService {
    @Autowired
    private PostArticleRepository repository;

    public PostArticle addPostArticle(PostArticle postArticle){
        return repository.save(postArticle);
    }

    public List<PostArticle> findByArticleId(Long id){
        return repository.findListByArticleId(id);
    }

    public void delelePostArticle(PostArticle postArticle){
        repository.delete(postArticle);
    }
}
