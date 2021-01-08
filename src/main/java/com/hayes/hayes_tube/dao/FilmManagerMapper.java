package com.hayes.hayes_tube.dao;

import com.alibaba.fastjson.JSONObject;
import com.hayes.hayes_tube.entity.FilmInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FilmManagerMapper {


    List<FilmInfo> getFilmByCondition(JSONObject json);

}
