package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.service.*;

@RequestMapping(value = "/quan-ly")
@Controller
public class AdminHomeController {
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
        modelAndView.setViewName("admin/trang-chu-admin");
        return modelAndView;
    }
}
