package com.hayes.hayes_tube.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/manager/15628783286")
public class ManagerController {
    @Autowired
    private FilmManagerService filmManagerService ;


    @RequestMapping("/")
    public String index(){
        return "manager/index" ;
    }

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject list(HttpServletRequest request){
        //http://127.0.0.1:9090/admin/manager/15628783286/list?page=1&limit=10

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("",request.getParameter(""));

        List<FilmInfo> all = filmManagerService.getFilmByCondition(jsonObject);

        return new JSONObject(){
            {
                put("code","0");
                put("msg","success");
                put("count",all.size());
                put("data",all);
            }
        };





    }

}
