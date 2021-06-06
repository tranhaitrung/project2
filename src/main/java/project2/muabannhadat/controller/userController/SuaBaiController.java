package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Controller
public class SuaBaiController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private AvatarService avatarService;

    private String username;

    private Long articleId;
    @GetMapping("/sua-bai-viet/{id}")
    public ModelAndView suaBaiViet(@PathVariable("id") Long id){
        username = AuthenticationSystem.getUsernameLogined();

        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(id);
        String username = postService.findUsername(id);
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(article == null || informationUser == null){
            System.out.println("có cái null");
        }
        System.out.println("vào đây nè");
        modelAndView.addObject("article",article);
        modelAndView.addObject("inforUser", informationUser);
        modelAndView.setViewName("user/sua-bai");
        return modelAndView;
    }


    @PostMapping("/sua-bai-viet")
    public ModelAndView suaBaiViet(Article article, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(bindingResult.hasErrors() || informationUser == null){
            System.out.println("lỗi null");
            modelAndView.addObject("successMessage","Vui lòng đăng nhập");
            modelAndView.setViewName("user/sua-bai");
        }else{
            article.setArticleId(articleId);
            article.setDate_up(DateUtils.createNow().getTime());
            articleService.addArticle(article);
            Post post = new Post();
            post.setUserName(username);
            post.setArticleId(articleId);
            postService.addPost(post);
            if (files.length >= 1){
                Arrays.asList(files)
                        .forEach(f ->{
                            try {
                                byte[] fileContent = f.getBytes();
                                String content = Base64.getEncoder().encodeToString(fileContent);
                                Image image = new Image();
                                image.setImageContent(content);
                                imageService.addImage(image);
                                PostArticle postArticle = new PostArticle();
                                postArticle.setArticleId(article.getArticleId());
                                postArticle.setImageId(image.getImageId());
                                postArticleService.addPostArticle(postArticle);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
            }
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("article",article);
            modelAndView.addObject("successMessage","Bài viết đã được cập nhật");
            modelAndView.setViewName("user/sua-bai");
            System.out.println("success!!");
        }

        return modelAndView;
    }
}
