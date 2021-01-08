package com.hayes.hayes_tube.ctrl;

import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.AnimeService;
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
@RequestMapping("anime")
public class AnimeController {
    private static Logger logger = LoggerFactory.getLogger(AnimeController.class);

    @Autowired
    private AnimeService feignAnimeService;

    @RequestMapping("/index")
    public String index(Model mode, HttpServletRequest request) {

        List<FilmInfo> filmGcdm = feignAnimeService.getTop10ByColumn("gcdm");
        List<FilmInfo> filmRhdm = feignAnimeService.getTop10ByColumn("rhdm");
        List<FilmInfo> filmOmdm = feignAnimeService.getTop10ByColumn("omdm");
        List<FilmInfo> filmGtdm = feignAnimeService.getTop10ByColumn("gtdm");
        List<FilmInfo> filmHwdm = feignAnimeService.getTop10ByColumn("hwdm");
        mode.addAttribute("filmGcdm", filmGcdm);
        mode.addAttribute("filmRhdm", filmRhdm);
        mode.addAttribute("filmOmdm", filmOmdm);
        mode.addAttribute("filmGtdm", filmGtdm);
        mode.addAttribute("filmHwdm", filmHwdm);
        logger.info(GyUtils.getLog(request) + ">>>> 进入动漫主页...");

        return "anime/index";
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
        Map<String, Object> map = feignAnimeService.getAllByColumn(column, page);
        Integer total = (Integer) map.get("totalCount");
        List<FilmInfo> data = (List<FilmInfo>) map.get("data");
        PageUtil pageInfo = new PageUtil(page, 20, total, data);
        model.addAttribute("filmCol", pageInfo.getList());
        model.addAttribute("column", GyUtils.dm2Colum(column));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wd", "");
        logger.info(GyUtils.getLog(request) + ">>>> 点击: " + GyUtils.dm2Colum(column).replaceAll("%", ""));
        return "anime/colindex";
    }

    @RequestMapping("/watch/{uuid}")
    public String filmIndex(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        FilmInfo filmInfo = feignAnimeService.getFilmByUUid(uuid);
        model.addAttribute("filmInfo", filmInfo);
        logger.info(GyUtils.getLog(request)+">>>> 打开视频页: "+filmInfo.getFilmName());
        return "anime/filmindex";
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
        return feignAnimeService.getBof(uuid);
    }

    @RequestMapping("/watchVideo")
    @ResponseBody
    public void watchVideos(@RequestBody String film, HttpServletRequest request){
        logger.info(GyUtils.getLog(request)+">>>> 观看动漫: "+film);
    }

}
