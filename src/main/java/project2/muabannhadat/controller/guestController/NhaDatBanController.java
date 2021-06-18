package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nha-dat-ban")
public class NhaDatBanController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    UserService userService;

    @Autowired
    private NotificationConnectService connectService;

    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("")
    public ModelAndView nhaDatBan(){

        List<Article> articles = articleService.findByForm("Bán");
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
