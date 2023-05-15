package com.junjie.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junjie.demo.common.Constants;
import com.junjie.demo.common.Result;
import com.junjie.demo.controller.dto.UserDTO;
import com.junjie.demo.entity.User;
import com.junjie.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈军杰
 * @since 2022-11-12
 */

@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        //新增或者修改
        return Result.success(userService.saveOrUpdate(user));
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        //注册
        String userName = userDTO.getUserName();
        String userPassword = userDTO.getUserPassword();
        if(StrUtil.isBlank(userName) || StrUtil.isBlank(userPassword)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String userName = userDTO.getUserName();
        String userPassword = userDTO.getUserPassword();
        if(StrUtil.isBlank(userName) || StrUtil.isBlank(userPassword)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody  List<String> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/userName/{userName}")
    public Result finduserName(@PathVariable String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userName);
        return Result.success(userService.getOne(queryWrapper));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String userName,
                                @RequestParam(defaultValue = "") String userAge,
                                @RequestParam(defaultValue = "") String userLevel) {
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.or().like(!userName.equals(""),"userName",userName);
        queryWrapper.or().like(!userAge.equals(""),"userAge",userAge);
        queryWrapper.or().like(!userLevel.equals(""),"userLevel",userLevel);
        return Result.success(userService.page(page,queryWrapper));
    }

    //导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //查找数据
        List<User> list = userService.list();

        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //  自定义标题别名
        writer.addHeaderAlias("userId","用户ID");
        writer.addHeaderAlias("userName","用户姓名");
        writer.addHeaderAlias("userPhone","用户电话号码");
        writer.addHeaderAlias("userPassword","用户密码");
        writer.addHeaderAlias("userAge","用户年龄");
        writer.addHeaderAlias("userLevel","用户等级");
        writer.addHeaderAlias("userPicture","用户头像");
        writer.addHeaderAlias("createTime","创建时间");

        //  一次性写出list内的对象到excel
        writer.write(list,true);

        //  设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

    //导入
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
        userService.saveBatch(list);
        return Result.success(true);
    }


}

