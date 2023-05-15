package com.junjie.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.junjie.demo.service.IPlaceService;
import com.junjie.demo.entity.Place;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈军杰
 * @since 2023-05-08
 */

@RestController

@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private IPlaceService placeService;

    @PostMapping
    public Boolean save(@RequestBody Place place) {
        //新增或者修改
        return placeService.saveOrUpdate(place);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return placeService.removeById(id);
    }

    @PostMapping("/del/batch")
    public Boolean deleteBatch(@RequestBody  List<String> ids) {
        return placeService.removeByIds(ids);
    }

    @GetMapping
    public List<Place> findAll() {
        return placeService.list();
    }

    @GetMapping("/{id}")
    public Place findOne(@PathVariable String id) {
        return placeService.getById(id);
    }

    @GetMapping("/page")
    public IPage<Place> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        IPage<Place> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();

        return placeService.page(page,queryWrapper);
    }


}

