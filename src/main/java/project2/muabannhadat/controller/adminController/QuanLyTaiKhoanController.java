package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/quan-ly")
@Controller
public class QuanLyTaiKhoanController {
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

    @GetMapping("/quan-ly-tai-khoan")
    public ModelAndView quanLyTaiKhoan(){
        ModelAndView modelAndView = new ModelAndView();
        List<InformationUser> informationUsers = informationUserService.getAll();


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

        modelAndView.addObject("informationUsers",informationUsers);
        modelAndView.setViewName("admin/danh-sach-tai-khoan");
        return modelAndView;

    }

    @GetMapping("/getListUser")
    public ModelAndView getListUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<InformationUser> informationUsers = new ArrayList<>();
        String wordSearch = request.getParameter("text_search");
        String select = request.getParameter("select");
        switch (select){
            case "0":
                informationUsers = informationUserService.getAll();
                break;
            case "1":
                Long id = Long.parseLong(wordSearch);
                informationUsers = informationUserService.findByIdLike(id);
                break;

            case "2":
                String username = wordSearch;
                informationUsers = informationUserService.findByUsername(username);
                break;
            case "3":
                String fullname = wordSearch;
                informationUsers = informationUserService.findByFullName(fullname);
                break;
            case "4":
                String phone = wordSearch;
                informationUsers = informationUserService.findByPhone(phone);
                break;
            case "5":
                String email = wordSearch;
                informationUsers = informationUserService.findByEmail(email);
                break;
            case "6":
                informationUsers = informationUserService.findInforDeleted();
                break;

        }
        modelAndView.addObject("informationUsers",informationUsers);
        modelAndView.setViewName("admin/listUser-tmp");
        return modelAndView;
    }


}
