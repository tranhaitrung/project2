package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.repository.AvatarRepository;
import project2.muabannhadat.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChiTietBaiVietController {
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

    @Autowired
    private AvatarService avatarService;

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

        if (AuthenticationSystem.isLogged() && username.equals(AuthenticationSystem.getUsernameLogined()) ){
            modelAndView.setViewName("user/chi-tiet-bai-viet");
        }else {
            modelAndView.setViewName("guest/chi-tiet-bai-viet");
        }
        return modelAndView;
    }


}
