package com.hayes.hayes_tube.ctrl;

import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.FilmService;
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
@RequestMapping("film")
public class FilmController {
    private static Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @RequestMapping("/index")
    public String index(Model mode, HttpServletRequest request) {
        List<FilmInfo> filmDz = filmService.getTop10ByColumn("dz");
        List<FilmInfo> filmXj = filmService.getTop10ByColumn("xj");
        List<FilmInfo> filmJq = filmService.getTop10ByColumn("jq");
        List<FilmInfo> filmKb = filmService.getTop10ByColumn("kb");
        List<FilmInfo> filmAq = filmService.getTop10ByColumn("aq");
        List<FilmInfo> filmKh = filmService.getTop10ByColumn("kh");
        List<FilmInfo> filmZz = filmService.getTop10ByColumn("zz");
        mode.addAttribute("filmDz", filmDz);
        mode.addAttribute("filmXj", filmXj);
        mode.addAttribute("filmJq", filmJq);
        mode.addAttribute("filmKb", filmKb);
        mode.addAttribute("filmAq", filmAq);
        mode.addAttribute("filmKh", filmKh);
        mode.addAttribute("filmZz", filmZz);
        logger.info(GyUtils.getLog(request) + ">>>> 进入电影主页...");

        return "film/index";
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
        Map<String, Object> map = filmService.getAllByColumn(column, page);
        Integer total = (Integer) map.get("totalCount");
        List<FilmInfo> data = (List<FilmInfo>) map.get("data");
        PageUtil pageInfo = new PageUtil(page, 20, total, data);
        model.addAttribute("filmCol", pageInfo.getList());
        model.addAttribute("column", GyUtils.dm2Colum(column));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wd", "");
        logger.info(GyUtils.getLog(request) + ">>>> 点击: " + GyUtils.dm2Colum(column).replaceAll("%", ""));
        return "film/colindex";
    }

    @RequestMapping("/watch/{uuid}")
    public String filmIndex(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        FilmInfo filmInfo = filmService.getFilmByUUid(uuid);
        model.addAttribute("filmInfo", filmInfo);
        logger.info(GyUtils.getLog(request)+">>>> 打开视频页: "+filmInfo.getFilmName());
        return "film/filmindex";
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
        List<FilmInfo> list = filmService.getBof(uuid);
        return list;
    }
    @RequestMapping("/watchVideo")
    @ResponseBody
    public void watchVideos(@RequestBody String film, HttpServletRequest request){
        logger.info(GyUtils.getLog(request)+">>>> 观看电影: "+film);
    }




}
