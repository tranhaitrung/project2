package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import org.thymeleaf.util.DateUtils;
import project2.muabannhadat.model.Article;

import project2.muabannhadat.repository.ArticleRepository;

import java.math.BigInteger;
import java.sql.Struct;
import java.util.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article addArticle(Article article){
        article.setDate_up(DateUtils.createNow().getTime());
        article.setDeleted(0);
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
        switch (gia) {
            case "1":
                gia1 = 1000000L;
                gia2 = 3000000L;
                break;
            case "2":
                gia1 = 3000000L;
                gia2 = 5000000L;
                break;
            case "3":
                gia1 = 5000000L;
                gia2 = 10000000L;
                break;
            case "4":
                gia1 = 10000000L;
                gia2 = 20000000L;
                break;
            case "5":
                gia1 = 20000000L;
                gia2 = 50000000L;
                break;
            case "6":
                gia1 = 50000000L;
                gia2 = 100000000L;
                break;
            case "7":
                gia1 = 100000000L;
                gia2 = 500000000L;
                break;
            case "8":
                gia1 = 500000000L;
                gia2 = 1000000000L;
                break;
            case "9":
                gia1 = 1000000000L;
                gia2 = 2000000000L;
                break;
            case "10":
                gia1 = 2000000000L;
                gia2 = 5000000000L;
                break;
            case "11":
                gia1 = 5000000000L;
                gia2 = 10000000000L;
                break;
            case "12":
                gia1 = 10000000000L;
                gia2 = 20000000000L;
                break;
            case "13":
                gia1 = 20000000000L;
                gia2 = 30000000000L;
                break;
            case "14":
                gia1 = 30000000000L;
                gia2 = 500000000000000L;
                break;
        }

        switch (dienTich) {
            case "20":
                dienTich1 = 0;
                dienTich2 = 20;
                break;
            case "30":
                dienTich1 = 20;
                dienTich2 = 30;
                break;
            case "50":
                dienTich1 = 30;
                dienTich2 = 50;
                break;
            case "80":
                dienTich1 = 50;
                dienTich2 = 80;
                break;
            case "100":
                dienTich1 = 80;
                dienTich2 = 100;
                break;
            case "150":
                dienTich1 = 100;
                dienTich2 = 150;
                break;
            case "200":
                dienTich1 = 150;
                dienTich2 = 200;
                break;
            case "300":
                dienTich1 = 200;
                dienTich2 = 300;
                break;
            case "500":
                dienTich1 = 300;
                dienTich2 = 500;
                break;
            case "501":
                dienTich1 = 500;
                dienTich2 = 50000000;
                break;
        }

        word = VNCharacterUtils.removeAccent(word);

        khuvuc = VNCharacterUtils.removeAccent(khuvuc);

        System.out.println("Hình thức : " +hinhThuc + "\n Loại: " + loai + "\n Khu vực : "+khuvuc +
                "\n Search : " + word + "\n Giá 1: " + gia1 + "\n Giá 2: "+gia2+ "\n Diện tích 1: "+dienTich1+
                "\n Diệc tích 2: "+dienTich2);

        List<Article> articles = articleRepository.findArticle(hinhThuc,loai,khuvuc,gia1,gia2,dienTich1,dienTich2);

        List<Article> list = new ArrayList<>();

        if (!articles.isEmpty()){
            if (!word.equals("")){
                String title, city, district, ward, detail;
                for (Article article : articles){
                    title = article.getTitle_unsigned();
                    city = article.getCity_unsigned();
                    district = article.getDistrict_unsigned();
                    ward = article.getWard_unsigned();
                    detail = article.getDetail_unsigned();

                    if (title.contains(word) || city.contains(word)
                            || district.contains(word) || ward.contains(word) || detail.contains(word)){
                        list.add(article);
                    }
                }
                return list;
            }
        }

        return articles;
    }

    public Long countArticle(){
        Long count = articleRepository.count();
        return count;
    }

    public Article updateArticle(Article article){
        String title = article.getTitle();
        String title_unsigned = VNCharacterUtils.removeAccent(title);
        String city = article.getCity();
        String city_u = VNCharacterUtils.removeAccent(city);
        String district = article.getDistrict();
        String district_u = VNCharacterUtils.removeAccent(district);
        String ward = article.getWard();
        String ward_u = VNCharacterUtils.removeAccent(ward);
        String detail = article.getDetail();
        String detail_u = VNCharacterUtils.removeAccent(detail);
        Date date_up = DateUtils.createNow().getTime();
        Float area = article.getArea();
        Long price = article.getPrice();
        String form = article.getForm();
        String species = article.getSpecies();

        article.setTitle_unsigned(title_unsigned);
        article.setDetail_unsigned(detail_u);
        article.setCity_unsigned(city_u);
        article.setDistrict_unsigned(district_u);
        article.setWard_unsigned(ward_u);
        article.setDate_up(date_up);
        article.setDeleted(0);

        //articleRepository.update(area,city,date_up,detail,district,form,price,species,title,ward,city_u,detail_u,district_u,title_unsigned,ward_u, article.getArticleId() );
        articleRepository.save(article);
        return article;
    }

    public List<Article> findByForm(String form){
        return articleRepository.findArticlesByForm(form);
    }

    public List<Article> findByPrice(String gia){
        Long gia1 = 0L;
        Long gia2 = 999999999999999999L;

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

        return articleRepository.findArticlesByPriceBetween(gia1, gia2);
    }

    public List<Article> findByArea(String dienTich){
        float dienTich1 = 0;
        float dienTich2 = 500000000;
        switch (dienTich) {
            case "20":
                dienTich1 = 0;
                dienTich2 = 20;
                break;
            case "30":
                dienTich1 = 20;
                dienTich2 = 30;
                break;
            case "50":
                dienTich1 = 30;
                dienTich2 = 50;
                break;
            case "80":
                dienTich1 = 50;
                dienTich2 = 80;
                break;
            case "100":
                dienTich1 = 80;
                dienTich2 = 100;
                break;
            case "150":
                dienTich1 = 100;
                dienTich2 = 150;
                break;
            case "200":
                dienTich1 = 150;
                dienTich2 = 200;
                break;
            case "300":
                dienTich1 = 200;
                dienTich2 = 300;
                break;
            case "500":
                dienTich1 = 300;
                dienTich2 = 500;
                break;
            case "501":
                dienTich1 = 500;
                dienTich2 = 50000000;
                break;
        }

        return articleRepository.findArticlesByAreaBetween(dienTich1, dienTich2);
    }

    public List<Article> findByCity(String city){
        city = VNCharacterUtils.removeAccent(city);
        return articleRepository.findArticlesByCityIgnoreCase(city);
    }

    public List<Article> findByTitle(String title){
        title = VNCharacterUtils.removeAccent(title);
        return articleRepository.findByTitleLIke(title);
    }

    public List<Article> findArticleDeleted(){
        return articleRepository.findArticlesByDeleted(1);
    }

    public void setDeleted(Long id){
        Article article = findArticleById(id);
        article.setDeleted(1);
        articleRepository.save(article);
    }

    public void setUndeleted(Long id){
        Article article = findArticleById(id);
        article.setDeleted(0);
        articleRepository.save(article);
    }
}
