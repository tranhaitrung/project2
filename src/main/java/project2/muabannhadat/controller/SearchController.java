package project2.muabannhadat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/tim-kiem")
public class SearchController {

    @Autowired
    private ArticleService articleService;



    @GetMapping("/")
    public ModelAndView search(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String hinhThuc = request.getParameter("btnradio");
        String word = request.getParameter("text-input");
        String loai = request.getParameter("loai");
        String khuvuc = request.getParameter("khu-vuc");
        String gia = request.getParameter("gia");
        String dienTich = request.getParameter("dien-tich");

        List<Article> articles = articleService.findArticle(hinhThuc, word, loai, khuvuc, gia, dienTich);
        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("guest/index");
        return modelAndView;
    }

}
