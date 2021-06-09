package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tim-kiem")
public class TimKiemController {
    @Autowired
    private ArticleService articleService;
    ModelAndView modelAndView = new ModelAndView();

    @Autowired
    ImageService imageService;

    @Autowired
    PostArticleService postArticleService;

    @Autowired
    AvatarService avatarService;

    @GetMapping("/")
    public ModelAndView search(HttpServletRequest request){

        String hinhThuc = request.getParameter("btnradio");
        String word = request.getParameter("text-input");
        String loai = request.getParameter("loai");
        String khuvuc = request.getParameter("khu-vuc");
        String gia = request.getParameter("gia");
        String dienTich = request.getParameter("dien-tich");

        List<Article> articles = articleService.findArticle(hinhThuc, word, loai, khuvuc, gia, dienTich);

        articles.removeIf(article -> article.getDeleted() == 1);

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
        avatar1.setImage("abc");
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("imgs",images);

        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }
}
