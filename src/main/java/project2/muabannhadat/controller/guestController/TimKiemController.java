package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

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

    @Autowired
    UserService userService;

    @Autowired
    private NotificationConnectService connectService;

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
                image.setImageContent("Kh??ng c?? ???nh");
                images.add(image);
            }

        }

        /**
         * ??o???n ki???m tra login
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

        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }
}
