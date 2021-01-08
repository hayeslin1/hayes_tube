package com.hayes.hayes_tube.dao;

import com.hayes.hayes_tube.entity.FilmInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimeMapper {
    /**
     * 查询所有
     * @return
     */
    List<FilmInfo> getAllTop10() ;

    /**
     * 通过UUID查询
     * @param uuid
     * @return
     */
    FilmInfo getFilmByUUid(@Param("uuid") String uuid) ;

    /**
     * 根据UUID查询播放的url地址
     * @param uuid
     * @return
     */
    List<FilmInfo> getBof(@Param("uuid") String uuid);

    /**
     * 按照不同种类 查询前10
     * @param column
     * @return
     */
    List<FilmInfo> getTop10ByColumn(@Param("column") String column) ;

    /**
     * 按照不通种类查询所有
     * @param column
     * @return
     */
    List<FilmInfo> getAllByColumn(@Param("column") String column, @Param("page_start") int page_start) ;
    /**
     * 根据类别获取数量
     * @param dm2Colum
     * @return
     */
    int getAllCountByColumn(String dm2Colum);
    /**
     * 搜索电影
     * @param want
     * @return
     */
    int searchFilmCount(@Param("want") String want) ;
    List<FilmInfo> searchFilm(@Param("want") String want, @Param("page_start") int page_start) ;



}
