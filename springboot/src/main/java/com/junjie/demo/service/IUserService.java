package com.junjie.demo.service;

import com.junjie.demo.controller.dto.UserDTO;
import com.junjie.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈军杰
 * @since 2022-11-12
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
