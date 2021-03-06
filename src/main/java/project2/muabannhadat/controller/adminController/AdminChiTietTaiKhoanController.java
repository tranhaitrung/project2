package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/quan-ly")
@Controller
public class AdminChiTietTaiKhoanController {
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

    @Autowired
    private NotificationConnectService connectService;

    @GetMapping("/tai-khoan/{username}")
    public ModelAndView chiTietTaiKhoan(@PathVariable("username") String username){
        ModelAndView modelAndView = new ModelAndView();

        InformationUser user = informationUserService.findInforUserByUsername(username);

        Avatar avatar = avatarService.findByUserName(username);

        List<Long> listArticleId = postService.findArticleIdByUsername(username);

        Article article;

        List<Article> articles = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        System.out.println(listArticleId.size());
        System.out.println(user.getFullName());
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


        /**
         * ??o???n ki???m tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        avatar1.setImage("abc");
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.setViewName("admin/thong-tin-tai-khoan");
        return  modelAndView;
    }
}
