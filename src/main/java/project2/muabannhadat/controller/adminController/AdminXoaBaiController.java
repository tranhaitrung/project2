package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.model.Image;
import project2.muabannhadat.model.Post;
import project2.muabannhadat.model.PostArticle;
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
        modelAndView.addObject("deleteSuccessMessage","Delete Successfull!");
        modelAndView.setViewName("admin/danh-sach-bai-viet");
        return modelAndView;
    }
}
