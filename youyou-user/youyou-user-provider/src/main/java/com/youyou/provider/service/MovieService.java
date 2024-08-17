package com.youyou.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.youyou.common.constants.IDConstant;
import com.youyou.common.utils.CopyBeanUtils;
import com.youyou.moudules.user.dto.MovieDTO;
import com.youyou.provider.entity.MovieDO;
import com.youyou.provider.mapper.MovieMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Author 龙贵义
 * Date 2024/8/17 16:27
 * Description:
 */
@Service
@Slf4j
public class MovieService {
    @Resource
    private MovieMapper movieMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final long expiration = 2;
    /**
     * 查询电影列表
     * @return
     */
    public List<MovieDTO> getMovieList() {

        //先去redis中查询是否存在
        List<MovieDTO> movieDTOList= (List<MovieDTO>) redisTemplate.opsForValue().get(IDConstant.MOVIE_KEY);
        if (null != movieDTOList){
            return movieDTOList;
        }
        List<MovieDO> movieDOList = movieMapper.selectList(Wrappers.lambdaQuery());
        if (null != movieDOList){
            movieDTOList = movieDOList.stream().map(this::convertToDTO).collect(Collectors.toList());
            redisTemplate.opsForValue().set(IDConstant.MOVIE_KEY, movieDTOList, expiration, TimeUnit.HOURS);
        }else {
            movieDTOList = Collections.emptyList();
        }
        log.info("查询的电影列表：{}", movieDTOList);
        return movieDTOList;
    }
    private MovieDTO convertToDTO(MovieDO movieDO){
        return CopyBeanUtils.copy(movieDO, MovieDTO.class);
    }
}
