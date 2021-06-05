package project2.muabannhadat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/nha-dat-cho-thue")
public class HireController {
    @GetMapping("/")
    public ModelAndView search(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String hinhThuc = request.getParameter("btnradio");
        String word = request.getParameter("text-input");
        String loai = request.getParameter("loai");
        String khuvuc = request.getParameter("khu-vuc");
        String gia = request.getParameter("gia");
        String dienTich = request.getParameter("dien-tich");

        modelAndView.setViewName("index");
        return modelAndView;
    }
}
