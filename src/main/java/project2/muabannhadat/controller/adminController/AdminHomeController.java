package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.NotificationConnect;
import project2.muabannhadat.service.*;

import java.util.List;

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

    @Autowired
    private NotificationConnectService connectService;

    @GetMapping(value={"/home", ""})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        InformationUser user1 = informationUserService.findInforUserByUsername(user.getUserName());

        Long countUsers = userService.countUser();
        Long countArticles = articleService.countArticle();


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
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);


        modelAndView.addObject("countUser", countUsers);
        modelAndView.addObject("countArticle", countArticles);
        modelAndView.setViewName("admin/trang-chu-admin");
        return modelAndView;
    }
}
