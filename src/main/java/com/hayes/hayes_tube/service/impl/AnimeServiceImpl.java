package com.hayes.hayes_tube.service.impl;


import com.hayes.hayes_tube.dao.AnimeMapper;
import com.hayes.hayes_tube.entity.FilmInfo;
import com.hayes.hayes_tube.service.AnimeService;
import com.hayes.hayes_tube.utils.GyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeMapper animeMapper ;

    @Override
    public List<FilmInfo> getAll() {
        return animeMapper.getAllTop10();
    }

    @Override
    public FilmInfo getFilmByUUid(String uuid) {
        return animeMapper.getFilmByUUid(uuid);
    }

    @Override
    public List<FilmInfo> getBof(String uuid) {
        return animeMapper.getBof(uuid);
    }

    @Override
    public List<FilmInfo> getTop10ByColumn(String column) {
        return animeMapper.getTop10ByColumn(GyUtils.dm2Colum(column));
    }

    @Override
    public Map<String, Object> getAllByColumn(String column, int page) {
        int page_start = (page-1)*20 ;
        Map<String,Object> map = new HashMap<String, Object>() ;
        int totalCount = animeMapper.getAllCountByColumn(GyUtils.dm2Colum(column));
        map.put("totalCount",totalCount) ;
        List<FilmInfo> list = animeMapper.getAllByColumn(GyUtils.dm2Colum(column), page_start);
        map.put("data",list) ;
        return map ;
    }

    @Override
    public Map<String, Object> searchFilm(String want, int page) {
        int page_start = (page-1)*20 ;
        Map<String, Object> map = new HashMap<String, Object>() ;
        int totalCount = animeMapper.searchFilmCount("%"+want+"%");
        map.put("totalCount",totalCount) ;
        List<FilmInfo> list = animeMapper.searchFilm("%"+want+"%", page_start);
        map.put("data",list) ;
        return map ;
    }
}
