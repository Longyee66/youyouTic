package com.youyou.provider.impl;

import com.youyou.interfaces.IMovieRPCService;
import com.youyou.moudules.user.dto.MovieDTO;
import com.youyou.provider.service.MovieService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Author 龙贵义
 * Date 2024/8/17 16:25
 * Description: 视频实现接口
 */
@DubboService(version = "1.0.0")
public class MovieRPCServiceImpl implements IMovieRPCService {
    @Resource
    private MovieService movieService;
    @Override
    public List<MovieDTO> getMovieList() {

        return movieService.getMovieList();
    }
}
