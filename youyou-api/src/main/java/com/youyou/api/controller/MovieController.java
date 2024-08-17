package com.youyou.api.controller;

import com.youyou.interfaces.IMovieRPCService;
import com.youyou.moudules.result.Result;
import com.youyou.moudules.user.dto.MovieDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author 龙贵义
 * Date 2024/8/16 15:24
 * Description: 视频相关接口
 */

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {
    @DubboReference(version = "1.0.0")
    private IMovieRPCService iMovieRPCService;

    @GetMapping("/movieList")
    public Result movieList(){
        List<MovieDTO> movieDTOList = iMovieRPCService.getMovieList();
        log.info("查询电影列表：{}", movieDTOList);
        return Result.success(movieDTOList);
    }
}
