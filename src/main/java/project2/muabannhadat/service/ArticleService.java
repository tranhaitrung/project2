package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import org.thymeleaf.util.DateUtils;
import project2.muabannhadat.model.Article;

import project2.muabannhadat.repository.ArticleRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article addArticle(Article article){
        article.setDate_up(DateUtils.createNow().getTime());
        return articleRepository.save(article);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article findArticleById(Long id){
        return articleRepository.findByArticleId(id);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    public List<Article> findArticle(String hinhThuc, String word, String loai, String khuvuc, String gia, String dienTich) {
        Long gia1 = 0L;
        Long gia2 = 999999999999999999L;

        float dienTich1 = 0;
        float dienTich2 = 500000000;
        if (gia.equals("1")){
            gia1 = 1000000L;
            gia2 = 3000000L;
        }else if (gia.equals("2")){
            gia1 = 3000000L;
            gia2 = 5000000L;
        }else if (gia.equals("3")){
            gia1 = 5000000L;
            gia2 = 10000000L;
        }else if (gia.equals("4")){
            gia1 = 10000000L;
            gia2 = 20000000L;
        }else if (gia.equals("5")){
            gia1 = 20000000L;
            gia2 = 50000000L;
        }else if (gia.equals("6")){
            gia1 = 50000000L;
            gia2 = 100000000L;
        }else if (gia.equals("7")){
            gia1 = 100000000L;
            gia2 = 500000000L;
        }else if (gia.equals("8")){
            gia1 = 500000000L;
            gia2 = 1000000000L;
        }else if (gia.equals("9")){
            gia1 = 1000000000L;
            gia2 = 2000000000L;
        }else if (gia.equals("10")){
            gia1 = 2000000000L;
            gia2 = 5000000000L;
        }else if (gia.equals("11")){
            gia1 = 5000000000L;
            gia2 = 10000000000L;
        }else if (gia.equals("12")){
            gia1 = 10000000000L;
            gia2 = 20000000000L;
        }else if (gia.equals("13")){
            gia1 = 20000000000L;
            gia2 = 30000000000L;
        }else if (gia.equals("14")){
            gia1 = 30000000000L;
            gia2 = 500000000000000L;
        }

        if (dienTich.equals("20")){
            dienTich1 = 0;
            dienTich2 = 20;
        }else if (dienTich.equals("30")){
            dienTich1 = 20;
            dienTich2 = 30;
        }else if (dienTich.equals("50")){
            dienTich1 = 30;
            dienTich2 = 50;
        }else if (dienTich.equals("80")){
            dienTich1 = 50;
            dienTich2 = 80;
        }else if (dienTich.equals("100")){
            dienTich1 = 80;
            dienTich2 = 100;
        }else if (dienTich.equals("150")){
            dienTich1 = 100;
            dienTich2 = 150;
        }else if (dienTich.equals("200")){
            dienTich1 = 150;
            dienTich2 = 200;
        }else if (dienTich.equals("300")){
            dienTich1 = 200;
            dienTich2 = 300;
        }else if (dienTich.equals("500")){
            dienTich1 = 300;
            dienTich2 = 500;
        }else if (dienTich.equals("501")){
            dienTich1 = 500;
            dienTich2 = 50000000;
        }

        word = VNCharacterUtils.removeAccent(word);

        khuvuc = VNCharacterUtils.removeAccent(khuvuc);

        System.out.println("Hình thức : " +hinhThuc + "\n Loại: " + loai + "\n Khu vực : "+khuvuc +
                "\n Search : " + word + "\n Giá 1: " + gia1 + "\n Giá 2: "+gia2+ "\n Diện tích 1: "+dienTich1+
                "\n Diệc tích 2: "+dienTich2);

        List<Article> article = articleRepository.findArticle(hinhThuc,loai,khuvuc,word,gia1,gia2,dienTich1,dienTich2);
        return article;
    }

    public Long countArticle(){
        Long count = articleRepository.count();
        return count;
    }
}
