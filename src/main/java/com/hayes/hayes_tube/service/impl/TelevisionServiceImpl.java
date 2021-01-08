package com.hayes.hayes_tube.service.impl;


import com.hayes.hayes_tube.dao.TelevisionMapper;
import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.TelevisionService;
import com.hayes.hayes_tube.utils.GyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TelevisionServiceImpl implements TelevisionService {

    @Autowired
    private TelevisionMapper televisionMapper ;

    @Override
    public List<FilmInfo> getAll() {
        return televisionMapper.getAllTop10();
    }

    @Override
    public FilmInfo getFilmByUUid(String uuid) {
        return televisionMapper.getFilmByUUid(uuid);
    }

    @Override
    public List<FilmInfo> getBof(String uuid) {
        return televisionMapper.getBof(uuid);
    }

    @Override
    public List<FilmInfo> getTop10ByColumn(String column) {
        return televisionMapper.getTop10ByColumn(GyUtils.dm2Colum(column));
    }

    @Override
    public Map<String, Object> getAllByColumn(String column, int page) {
        int page_start = (page-1)*20 ;
        Map<String,Object> map = new HashMap<String, Object>() ;
        int totalCount = televisionMapper.getAllCountByColumn(GyUtils.dm2Colum(column));
        map.put("totalCount",totalCount) ;
        List<FilmInfo> list = televisionMapper.getAllByColumn(GyUtils.dm2Colum(column), page_start);
        map.put("data",list) ;
        return map ;
    }

    @Override
    public Map<String, Object> searchFilm(String want, int page) {
        int page_start = (page-1)*20 ;
        Map<String, Object> map = new HashMap<String, Object>() ;
        int totalCount = televisionMapper.searchFilmCount("%"+want+"%");
        map.put("totalCount",totalCount) ;
        List<FilmInfo> list = televisionMapper.searchFilm("%"+want+"%", page_start);
        map.put("data",list) ;
        return map ;
    }
}
