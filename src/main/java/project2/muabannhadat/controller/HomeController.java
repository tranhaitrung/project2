package project2.muabannhadat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.*;
import project2.muabannhadat.repository.AvatarRepository;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private AvatarRepository avatarRepository;

    @GetMapping("/")
    public ModelAndView getHome(){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.getAll();
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
        modelAndView.addObject("imgs",images);
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

    @GetMapping("/chi-tiet-bai-viet/{id}")
    public ModelAndView getDetail(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(id);
        String username = postService.findUsername(id);
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        Avatar avatar = avatarRepository.findByUsername(username);
        List<PostArticle> postArticleList = postArticleService.findByArticleId(id);
        if(article == null || informationUser == null){
            System.out.println("có cái null");
        }
        List<Image> images = new ArrayList<>();
        if (!postArticleList.isEmpty()){
            for (PostArticle pa : postArticleList){
                Image image = imageService.findImageByImageId(pa.getImageId());
                images.add(image);
            }
            modelAndView.addObject("images",images);
        }
        System.out.println("vào đây nè");
        modelAndView.addObject("avatar", avatar);
        modelAndView.addObject("article",article);
        modelAndView.addObject("inforUser", informationUser);
        modelAndView.setViewName("guest/chi-tiet-bai-viet");
        return modelAndView;
    }
}
