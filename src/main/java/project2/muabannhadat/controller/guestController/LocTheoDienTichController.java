package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.Image;
import project2.muabannhadat.model.PostArticle;
import project2.muabannhadat.service.ArticleService;
import project2.muabannhadat.service.AvatarService;
import project2.muabannhadat.service.ImageService;
import project2.muabannhadat.service.PostArticleService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LocTheoDienTichController {
    @Autowired
    ArticleService articleService;

    @Autowired
    PostArticleService postArticleService;

    @Autowired
    ImageService imageService;

    @Autowired
    AvatarService avatarService;

    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/dien-tich-10-den-20-m2")
    public ModelAndView loc1(){
        float dt1 = 0;
        float dt2 = 20;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-20-den-30-m2")
    public ModelAndView loc2(){
        float dt1 = 20;
        float dt2 = 30;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-30-den-50-m2")
    public ModelAndView loc3(){
        float dt1 = 30;
        float dt2 = 50;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-50-den-100-m2")
    public ModelAndView loc4(){
        float dt1 = 50;
        float dt2 = 100;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-100-den-150-m2")
    public ModelAndView loc5(){
        float dt1 = 100;
        float dt2 = 150;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-150-den-200-m2")
    public ModelAndView loc6(){
        float dt1 = 150;
        float dt2 = 200;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-200-den-250-m2")
    public ModelAndView loc7(){
        float dt1 = 200;
        float dt2 = 250;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-250-den-300-m2")
    public ModelAndView loc8(){
        float dt1 = 250;
        float dt2 = 300;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-300-den-500-m2")
    public ModelAndView loc9(){
        float dt1 = 300;
        float dt2 = 500;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/dien-tich-lon-hon-500-m2")
    public ModelAndView loc10(){
        float dt1 = 500;
        float dt2 = 999999999;
        List<Article> articles = articleService.getArticleByAreaBetween(dt1, dt2);
        List<Image> images = new ArrayList<>();
        for (Article article : articles){
            List<PostArticle> postArticles = postArticleService.findByArticleId(article.getArticleId());
            if (!postArticles.isEmpty()){
                Image image = imageService.findImageByImageId(postArticles.get(0).getImageId());
                images.add(image);
            }else {
                Image image = new Image();
                image.setImageContent("Không có ảnh");
                images.add(image);
            }

        }


        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

}
