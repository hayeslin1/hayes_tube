package com.hayes.hayes_tube.service;

import com.alibaba.fastjson.JSONObject;
import com.hayes.hayes_tube.entity.FilmInfo;

import java.util.List;

public interface FilmManagerService {

    List<FilmInfo> getFilmByCondition(JSONObject json );

}
