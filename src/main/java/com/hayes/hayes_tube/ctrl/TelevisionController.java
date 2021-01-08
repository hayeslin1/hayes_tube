package com.hayes.hayes_tube.ctrl;

import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.TelevisionService;
import com.hayes.hayes_tube.utils.GyUtils;
import com.hayes.hayes_tube.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("television")
public class TelevisionController {
    private static Logger logger = LoggerFactory.getLogger(TelevisionController.class);

    @Autowired
    private TelevisionService feignTelevisionService;

    @RequestMapping("/index")
    public String index(Model mode, HttpServletRequest request) {
        List<FilmInfo> filmGc = feignTelevisionService.getTop10ByColumn("gc");
        List<FilmInfo> filmXg = feignTelevisionService.getTop10ByColumn("xg");
        List<FilmInfo> filmHg = feignTelevisionService.getTop10ByColumn("hg");
        List<FilmInfo> filmOm = feignTelevisionService.getTop10ByColumn("om");
        List<FilmInfo> filmTw = feignTelevisionService.getTop10ByColumn("tw");
        List<FilmInfo> filmRb = feignTelevisionService.getTop10ByColumn("rb");
        List<FilmInfo> filmHw = feignTelevisionService.getTop10ByColumn("hw");
        mode.addAttribute("filmGc", filmGc);
        mode.addAttribute("filmXg", filmXg);
        mode.addAttribute("filmHg", filmHg);
        mode.addAttribute("filmOm", filmOm);
        mode.addAttribute("filmTw", filmTw);
        mode.addAttribute("filmRb", filmRb);
        mode.addAttribute("filmHw", filmHw);
        logger.info(GyUtils.getLog(request) + ">>>> 进入电视主页...");

        return "television/index";
    }

    /**
     * 根据类别查询
     *
     * @param column
     * @param model
     * @return
     */
    @RequestMapping("column/{column}")
    public String filmColumn(@PathVariable("column") String column, Model model, HttpServletRequest request) {
        String pageStr = request.getParameter("currentPage");
        if (GyUtils.isNull(pageStr)) {
            pageStr = "1";
        }
        int page = Integer.parseInt(pageStr);
        Map<String, Object> map = feignTelevisionService.getAllByColumn(column, page);
        Integer total = (Integer) map.get("totalCount");
        List<FilmInfo> data = (List<FilmInfo>) map.get("data");
        PageUtil pageInfo = new PageUtil(page, 20, total, data);
        model.addAttribute("filmCol", pageInfo.getList());
        model.addAttribute("column", GyUtils.dm2Colum(column));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wd", "");
        logger.info(GyUtils.getLog(request) + ">>>> 点击: " + GyUtils.dm2Colum(column).replaceAll("%", ""));
        return "television/colindex";
    }

    @RequestMapping("/watch/{uuid}")
    public String filmIndex(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        FilmInfo filmInfo = feignTelevisionService.getFilmByUUid(uuid);
        model.addAttribute("filmInfo", filmInfo);
        logger.info(GyUtils.getLog(request)+">>>> 打开视频页: "+filmInfo.getFilmName());
        return "television/filmindex";
    }
    /**
     * 根据UUID查询播放链接
     *
     * @param uuid
     * @return
     */
    @RequestMapping("/getBof/{uuid}")
    @ResponseBody
    public List<FilmInfo> getBof(@PathVariable("uuid") String uuid) {
        return feignTelevisionService.getBof(uuid);
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
        return "redirect:/film/about/sayHi";
    }
    @RequestMapping("/watchVideo")
    @ResponseBody
    public void watchVideos(@RequestBody String film, HttpServletRequest request){
        logger.info(GyUtils.getLog(request)+">>>> 观看电视: "+film);
    }

}
