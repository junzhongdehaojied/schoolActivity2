package com.junjie.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junjie.demo.common.Constants;
import com.junjie.demo.common.Result;
import com.junjie.demo.controller.dto.UserDTO;
import com.junjie.demo.entity.User;
import com.junjie.demo.exception.ServiceException;
import com.junjie.demo.mapper.UserMapper;
import com.junjie.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;




/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈军杰
 * @since 2022-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one != null){

            BeanUtil.copyProperties(one,userDTO,true);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);  //吧copy完后的用户对象存储到数据库里
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }

        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userDTO.getUserName());
        queryWrapper.eq("userPassword",userDTO.getUserPassword());
        User one;
        try{
            one = getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
