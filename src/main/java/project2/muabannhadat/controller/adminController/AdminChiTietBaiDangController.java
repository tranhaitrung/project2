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
public class AdminChiTietBaiDangController {
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

    @GetMapping("/chi-tiet-bai-viet/{id}")
    public ModelAndView chiTietBaiViet(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(id);
        String user_name = postService.findUsername(id);
        InformationUser informationUser = informationUserService.findInforUserByUsername(user_name);
        Avatar avatar = avatarService.findByUserName(user_name);
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

        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        avatar1.setImage("abc");
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            InformationUser user = informationUserService.findInforUserByUsername(username1);
            avatar1 = avatarService.findByUserName(username1);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);

        System.out.println("vào đây nè");
        modelAndView.addObject("avatar", avatar);
        modelAndView.addObject("article",article);
        modelAndView.addObject("inforUser", informationUser);
        modelAndView.setViewName("admin/chi-tiet-bai-viet");
        return modelAndView;
    }
}
