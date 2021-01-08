package com.hayes.hayes_tube.ctrl;

import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.MediaService;
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
@RequestMapping("media")
public class MediaController {
    private static Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaService feignMediaService;

    @RequestMapping("/index")
    public String index(Model mode, HttpServletRequest request) {

        List<FilmInfo> filmNdzy = feignMediaService.getTop10ByColumn("ndzy");
        List<FilmInfo> filmGtzy = feignMediaService.getTop10ByColumn("gtzy");
        List<FilmInfo> filmRhzy = feignMediaService.getTop10ByColumn("rhzy");
        List<FilmInfo> filmOmzy = feignMediaService.getTop10ByColumn("omzy");
        mode.addAttribute("filmNdzy", filmNdzy);
        mode.addAttribute("filmGtzy", filmGtzy);
        mode.addAttribute("filmRhzy", filmRhzy);
        mode.addAttribute("filmOmzy", filmOmzy);
        logger.info(GyUtils.getLog(request) + ">>>> 进入综艺主页...");

        return "media/index";
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
        Map<String, Object> map = feignMediaService.getAllByColumn(column, page);
        Integer total = (Integer) map.get("totalCount");
        List<FilmInfo> data = (List<FilmInfo>) map.get("data");
        PageUtil pageInfo = new PageUtil(page, 20, total, data);
        model.addAttribute("filmCol", pageInfo.getList());
        model.addAttribute("column", GyUtils.dm2Colum(column));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wd", "");
        logger.info(GyUtils.getLog(request) + ">>>> 点击: " + GyUtils.dm2Colum(column).replaceAll("%", ""));
        return "media/colindex";
    }

    @RequestMapping("/watch/{uuid}")
    public String filmIndex(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
        FilmInfo filmInfo = feignMediaService.getFilmByUUid(uuid);
        model.addAttribute("filmInfo", filmInfo);
        logger.info(GyUtils.getLog(request)+">>>> 打开视频页: "+filmInfo.getFilmName());
        return "media/filmindex";
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
        return feignMediaService.getBof(uuid);
    }

    @RequestMapping("/watchVideo")
    @ResponseBody
    public void watchVideos(@RequestBody String film, HttpServletRequest request){
        logger.info(GyUtils.getLog(request)+">>>> 观看综艺: "+film);
    }

}
