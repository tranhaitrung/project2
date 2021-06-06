package project2.muabannhadat.controller.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.service.*;

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
        modelAndView.addObject("informationUsers",informationUsers);
        modelAndView.setViewName("admin/danh-sach-tai-khoan");
        return modelAndView;

    }
}
