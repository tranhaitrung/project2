package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.model.Image;
import project2.muabannhadat.model.PostArticle;
import project2.muabannhadat.repository.AvatarRepository;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
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
}
