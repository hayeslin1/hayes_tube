package com.hayes.hayes_tube.ctrl;

import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.AnimeService;
import com.hayes.hayes_tube.service.FilmService;
import com.hayes.hayes_tube.service.MediaService;
import com.hayes.hayes_tube.service.TelevisionService;
import com.hayes.hayes_tube.utils.GyUtils;
import com.hayes.hayes_tube.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TelevisionService televisionService;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private AnimeService animeService;
    @Autowired
    private FilmService filmService;



    @RequestMapping("/")
    public String index(Model model , HttpServletRequest request){
        model.addAttribute("film", filmService.getAll());
        model.addAttribute("television", televisionService.getAll());
        model.addAttribute("media", mediaService.getAll());
        model.addAttribute("anime", animeService.getAll());
        logger.info(GyUtils.getLog(request) + ">>>> 进入首页...");
        return "home" ;
    }

    @RequestMapping("search")
    public String searchFilm(Model model , HttpServletRequest request) {
        String pageStr = request.getParameter("currentPage");
        if (GyUtils.isNull(pageStr)) {
            pageStr = "1";
        }
        int page = Integer.parseInt(pageStr);
        String want = request.getParameter("wd");
        Map<String, Object> map = filmService.searchFilm(want,page);
        String total = map.get("totalCount").toString();
        PageUtil pageInfo = new PageUtil(page, 20, Integer.parseInt(total), (List<FilmInfo>) map.get("data"));
        model.addAttribute("filmCol", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("column", "搜索结果");
        model.addAttribute("wd", want);
        logger.info(GyUtils.getLog(request)+">>>> 搜索: "+want);
        return "film/colindex";
    }

    @RequestMapping("/about/sayHi")
    public String leaveMessage(HttpServletRequest request) {
        logger.info(GyUtils.getLog(request) + ">>>> 点击留言 ");
        return "film/sayHi";
    }

    @RequestMapping("/about/sendMail")
    public String sendMail(HttpServletRequest request) {
        String mailContent = request.getParameter("mail");
        logger.info(GyUtils.getLog(request) + ">>>> 留言内容:  " + mailContent);
        return "redirect:/about/sayHi";
    }

}
