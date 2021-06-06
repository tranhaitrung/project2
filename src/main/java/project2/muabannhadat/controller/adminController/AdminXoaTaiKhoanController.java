package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/quan-ly")
@Controller
public class AdminXoaTaiKhoanController {
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
    @PostMapping("/xoa-tai-khoan/{username}")
    @Transactional
    public Boolean xoaTaiKhoan(@PathVariable("username") String username){
        User user = userService.findUserByUserName(username);
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        List<Post> posts = postService.findByUsername(username);
        List<Article> articles = new ArrayList<>();
        List<PostArticle> postArticles = new ArrayList<>();
        if (!posts.isEmpty()){
            for (Post post : posts){
                articles.add(articleService.findArticleById(post.getArticleId()));
                postArticles = postArticleService.findByArticleId(post.getArticleId());
                if (!postArticles.isEmpty()){
                    for (PostArticle postArticle : postArticles){
                        // Xóa dữ liệu bảng image
                        imageService.deleteById(postArticle.getImageId());
                        // Xóa dữ liệu bảng post-articles
                        postArticleService.delelePostArticle(postArticle);
                    }
                }
                // Xóa dữ liệu bảng articles
                for (Article article : articles){
                    articleService.deleteArticle(article);
                }
                // Xóa dự liệu bảng post-user
                postService.delete(post);
            }
        }
        // Xóa avatar
        avatarService.deleteByUsername(username);
        informationUserService.deleteInformationUserByUsername(username);
        userService.deleteByUsername(username);
        return true;
    }
}
