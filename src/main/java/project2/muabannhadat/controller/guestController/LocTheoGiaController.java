package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LocTheoGiaController {
    @Autowired
    ArticleService articleService;

    @Autowired
    PostArticleService postArticleService;

    @Autowired
    ImageService imageService;

    @Autowired
    AvatarService avatarService;

    @Autowired
    UserService userService;

    @Autowired
    private NotificationConnectService connectService;

    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/gia-tu-1-den-3-trieu")
    public ModelAndView locGia1(){
        Long gia1 = 0L;
        Long gia2 = 3000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
            modelAndView.addObject("role", roleid);
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

    @GetMapping("/gia-tu-3-den-5-trieu")
    public ModelAndView locGia2(){
        Long gia1 = 3000000L;
        Long gia2 = 5000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
            modelAndView.addObject("role", roleid);
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
    @GetMapping("/gia-tu-5-den-10-trieu")
    public ModelAndView locGia3(){
        Long gia1 = 5000000L;
        Long gia2 = 10000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
            modelAndView.addObject("role", roleid);
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
    @GetMapping("/gia-tu-10-den-15-trieu")
    public ModelAndView locGia4(){
        Long gia1 = 10000000L;
        Long gia2 = 15000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
            modelAndView.addObject("role", roleid);
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
    @GetMapping("/gia-tu-15-den-30-trieu")
    public ModelAndView locGia5(){
        Long gia1 = 15000000L;
        Long gia2 = 30000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-30-den-50-trieu")
    public ModelAndView locGia6(){
        Long gia1 = 30000000L;
        Long gia2 = 50000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-50-den-100-trieu")
    public ModelAndView locGia7(){
        Long gia1 = 50000000L;
        Long gia2 = 100000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-100-den-200-trieu")
    public ModelAndView locGia8(){
        Long gia1 = 100000000L;
        Long gia2 = 200000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-200-den-500-trieu")
    public ModelAndView locGia9(){
        Long gia1 = 200000000L;
        Long gia2 = 500000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-500-trieu-den-1-ti")
    public ModelAndView locGia10(){
        Long gia1 = 500000000L;
        Long gia2 = 1000000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-1-den-3-ti")
    public ModelAndView locGia11(){
        Long gia1 = 1000000000L;
        Long gia2 = 3000000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-3-den-5-ti")
    public ModelAndView locGia12(){
        Long gia1 = 3000000000L;
        Long gia2 = 5000000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-5-den-10-ti")
    public ModelAndView locGia13(){
        Long gia1 = 5000000000L;
        Long gia2 = 10000000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-tu-10-den-20-ti")
    public ModelAndView locGia14(){
        Long gia1 = 10000000000L;
        Long gia2 = 20000000000L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
    @GetMapping("/gia-lon-hon-20-ti")
    public ModelAndView locGia15(){
        Long gia1 = 20000000000L;
        Long gia2 = 999999999999999999L;
        List<Article> articles = articleService.getAricleByPriceBetween(gia1, gia2);
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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
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
