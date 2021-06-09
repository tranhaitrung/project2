package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/quan-ly")
@Controller
public class QuanLyBaiVietController {
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

    @GetMapping("/quan-ly-bai-viet")
    public ModelAndView quanLyBaiViet(){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.getAll();
        modelAndView.addObject("articles",articles);

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

        modelAndView.setViewName("admin/danh-sach-bai-viet");
        return  modelAndView;
    }

    @GetMapping("/getListArticle")
    public ModelAndView timKiem(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = new ArrayList<>();

        String select = request.getParameter("select");

        switch (select){
            case "0":
                articles = articleService.getAll();
                break;
            case "1":
                Long id = Long.parseLong(request.getParameter("id"));
                articles.add(articleService.findArticleById(id));
                break;
            case "2":
                String title = request.getParameter("title");
                articles = articleService.findByTitle(title);
                break;
            case "3":
                String city = request.getParameter("city");
                articles = articleService.findByCity(city);
                break;
            case "4":
                String area = request.getParameter("area");
                articles = articleService.findByArea(area);
                break;
            case "5":
                String price = request.getParameter("price");
                articles = articleService.findByPrice(price);
                break;
            case "6":
                articles = articleService.findArticleDeleted();
                break;
        }

        modelAndView.addObject("articles",articles);
        modelAndView.setViewName("admin/ds-bai-tmp");
        return  modelAndView;
    }
}
