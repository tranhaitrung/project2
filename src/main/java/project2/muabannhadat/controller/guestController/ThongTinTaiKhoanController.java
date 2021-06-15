package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThongTinTaiKhoanController {
    @Autowired
    private UserService userService;

    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private AvatarService avatarService;


    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/tai-khoan/{username}")
    public ModelAndView thongTinTaiKhoan(@PathVariable("username") String username){

        System.out.println("controller Thong tin tài khoan");

        InformationUser user = informationUserService.findInforUserByUsername(username);

        Avatar avatar = avatarService.findByUserName(username);

        List<Long> listArticleId = postService.findArticleIdByUsername(username);

        Article article;

        List<Article> articles = new ArrayList<>();
        List<Image> images = new ArrayList<>();

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
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        if (AuthenticationSystem.isLogged() && username.equals(AuthenticationSystem.getUsernameLogined())) {

            if (!listArticleId.isEmpty()){
                int size = listArticleId.size();
                for (int i = 0; i < size; i++){
                    article = articleService.findArticleById(listArticleId.get(i));
                    articles.add(article);
                    List<PostArticle> postArticle = postArticleService.findByArticleId(article.getArticleId());
                    if (!postArticle.isEmpty()){
                        Image image = imageService.findImageByImageId(postArticle.get(0).getImageId());
                        images.add(image);
                        System.out.println(i + "/" + size);
                    }
                }
                modelAndView.addObject("articles", articles);
                modelAndView.addObject("imgs", images);
                modelAndView.addObject("inforUser", user);
                modelAndView.addObject("avatar",avatar);
            }else {
                modelAndView.addObject("inforUser", user);
                modelAndView.addObject("avatar",avatar);
            }
            modelAndView.setViewName("user/thong-tin-tai-khoan");
        }else {
            if (!listArticleId.isEmpty()){
                int size = listArticleId.size();
                for (int i = 0; i < size; i++){
                    article = articleService.findArticleById(listArticleId.get(i));
                    if (article.getDeleted() == 1) continue;
                    articles.add(article);
                    List<PostArticle> postArticle = postArticleService.findByArticleId(article.getArticleId());
                    if (!postArticle.isEmpty()){
                        Image image = imageService.findImageByImageId(postArticle.get(0).getImageId());
                        images.add(image);
                        System.out.println(i + "/" + size);
                    }
                }
                modelAndView.addObject("articles", articles);
                modelAndView.addObject("imgs", images);
                modelAndView.addObject("inforUser", user);
                modelAndView.addObject("avatar",avatar);
            }else {
                modelAndView.addObject("inforUser", user);
                modelAndView.addObject("avatar",avatar);
            }
            modelAndView.setViewName("guest/thong-tin-tai-khoan");
        }

        return modelAndView;
    }


    @GetMapping("/transtion")
    public ModelAndView transtion(){
        Article article = new Article();
        modelAndView.addObject("article",article);
        modelAndView.setViewName("tmp");
        return modelAndView;
    }
}
