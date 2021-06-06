package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Controller
public class DangBaiController {
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

    @GetMapping("/dang-tin-nha-dat")
    public ModelAndView dangTinThueNha(){
        username = AuthenticationSystem.getUsernameLogined();
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(informationUser.getFullName() == null){
            Avatar avatar = avatarService.findByUserName(username);
            String avt = avatar.getImage();
            modelAndView.addObject("avatars",avt);
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.setViewName("user/cap-nhat-thong-tin");
        }
        else {
            Article article = new Article();
            modelAndView.addObject("inforUser", informationUser);
            modelAndView.addObject("article", article);
            modelAndView.setViewName("user/dang-bai");
        }
        return modelAndView;
    }

    @PostMapping("/dang-tin-nha-dat")
    @Transactional
    public ModelAndView dangtin(Article article, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(bindingResult.hasErrors()){
            System.out.println("lỗi null");
            modelAndView.setViewName("user/dang-bai");
        }else{
            String p = VNCharacterUtils.removeAccent(article.getCity());
            article.setCity_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getTitle());
            article.setTitle_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getDistrict());
            article.setDistrict_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getWard());
            article.setWard_unsigned(p);

            System.out.println(article.getDetail());
            p = VNCharacterUtils.removeAccent(article.getDetail());
            article.setDetail_unsigned(p);


            articleService.addArticle(article);
            Post post = new Post();
            post.setUserName(username);
            post.setArticleId(article.getArticleId());
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
            modelAndView.addObject("article",new Article());
            modelAndView.addObject("successMessage","Đăng tin thành công!");
            modelAndView.setViewName("user/dang-bai");
            System.out.println("success!!");
        }

        return modelAndView;
    }
}
