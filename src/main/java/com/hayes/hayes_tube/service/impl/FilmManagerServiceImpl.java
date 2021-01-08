package com.hayes.hayes_tube.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hayes.hayes_tube.dao.FilmManagerMapper;
import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.FilmManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilmManagerServiceImpl implements FilmManagerService {

    @Autowired
    private FilmManagerMapper filmManagerMapper ;

    @Override
    public List<FilmInfo> getFilmByCondition(JSONObject json) {
        return filmManagerMapper.getFilmByCondition(json);
    }
}
