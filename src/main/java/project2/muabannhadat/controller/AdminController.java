package project2.muabannhadat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/admin")
@Controller
public class AdminController {
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

    @GetMapping(value={"/home", ""})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        InformationUser user1 = informationUserService.findInforUserByUsername(user.getUserName());

        Long countUsers = userService.countUser();
        Long countArticles = articleService.countArticle();

        modelAndView.addObject("countUser", countUsers);
        modelAndView.addObject("countArticle", countArticles);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping("/quan-ly-tai-khoan")
    public ModelAndView quanLyTaiKhoan(){
        ModelAndView modelAndView = new ModelAndView();
        List<InformationUser> informationUsers = informationUserService.getAll();
        modelAndView.addObject("informationUsers",informationUsers);
        modelAndView.setViewName("admin/danhSachUser");
        return modelAndView;

    }

    @GetMapping("/tai-khoan/{username}")
    public  ModelAndView chiTietTaiKhoan(@PathVariable("username") String username){
        ModelAndView modelAndView = new ModelAndView();

        InformationUser user = informationUserService.findInforUserByUsername(username);

        Avatar avatar = avatarService.findByUserName(username);

        List<Long> listArticleId = postService.findArticleIdByUsername(username);

        Article article;

        List<Article> articles = new ArrayList<>();
        List<Image> images = new ArrayList<>();

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
        modelAndView.setViewName("admin/detailUser");
        return  modelAndView;
    }

    @GetMapping("quan-ly-bai-viet")
    public ModelAndView quanLyBaiViet(){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.getAll();
        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("admin/danhSachBaiViet");
        return  modelAndView;
    }

    @GetMapping("/chi-tiet-bai-viet/{id}")
    public ModelAndView chiTietBaiViet(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(id);
        String username = postService.findUsername(id);
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        Avatar avatar = avatarService.findByUserName(username);
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
        modelAndView.setViewName("admin/detailArticle");
        return modelAndView;
    }

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
        modelAndView.setViewName("admin/danhSachBaiViet");
        return modelAndView;
    }
}
