package com.youyou.interfaces;

import com.youyou.moudules.user.dto.MovieDTO;

import java.util.List;

/**
 * Author 龙贵义
 * Date 2024/8/17 16:24
 * Description: 视频相关接口
 */
public interface IMovieRPCService {
    /**
     * 查询电影列表
     * @return
     */
    List<MovieDTO> getMovieList();
}
