package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping(value = "/quan-ly")
@Controller
public class AdminXoaBaiController {
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

    @PostMapping("/xoa-bai-viet/{id}")
    @Transactional
    public ModelAndView xoaBaiViet(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        List<PostArticle> postArticles = postArticleService.findByArticleId(id);
        Article article = articleService.findArticleById(id);
        Post post = postService.findByArticleId(id);
        postService.delete(post);
        for (PostArticle postArticle : postArticles){
            Image image = imageService.findImageByImageId(postArticle.getImageId());
            imageService.deleteImage(image);
            postArticleService.delelePostArticle(postArticle);
        }
        articleService.deleteArticle(article);

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

        modelAndView.addObject("deleteSuccessMessage","Delete Successfull!");
        modelAndView.setViewName("admin/danh-sach-bai-viet");
        return modelAndView;
    }
}
