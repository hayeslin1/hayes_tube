package com.hayes.hayes_tube.service;


import com.hayes.hayes_tube.entity.FilmInfo;

import java.util.List;
import java.util.Map;

public interface MediaService {

    List<FilmInfo> getAll() ;
    FilmInfo getFilmByUUid(String uuid) ;
    List<FilmInfo> getBof(String uuid);
    List<FilmInfo> getTop10ByColumn(String column);
    Map<String,Object> getAllByColumn(String column, int page);
    Map<String, Object> searchFilm(String want, int page);

}
