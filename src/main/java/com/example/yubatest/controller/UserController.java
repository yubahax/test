package com.example.yubatest.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.yubatest.entity.domainobject.UserDo;
import com.example.yubatest.entity.page.UserPageQry;
import com.example.yubatest.mapper.UserMapper;
import com.example.yubatest.repository.UserRepository;
import com.example.yubatest.service.UserService;
import cn.soboys.restapispringbootstarter.Result;
import com.example.yubatest.util.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * user(User)表控制层
 *
 * @author makejava
 * @since 2024-10-11 09:54:46
 */
@RestController
@Api(tags = "【V1.0.0】测试")
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    @Resource
    UserMapper userMapper;

    @GetMapping("/selectPage")
    public Result selectPage(UserPageQry userPageQry) {


        IPage<UserDo> iPage =userMapper.selectPage(PageHelper.queryToPage(userPageQry),null);
        return Result.buildSuccess(iPage);


    }


    @GetMapping("/update")
    @ApiOperation("update")
    public Result updateUser() {

//        UpdateWrapper<UserDo> wrapper = new UpdateWrapper<>();
//        wrapper.set("age","30");
//        wrapper.eq("name","yuba");
//        userMapper.update(null,wrapper);
        UserDo selectUser = new UserDo();
        selectUser.setId(1843480572768731138L);
        selectUser.setAge(11);
        userRepository.updateById(selectUser);
//        UpdateWrapper<UserDo> wrapper = new UpdateWrapper<>();
//        wrapper.set("age","30");
//        wrapper.eq("name","yuba");
//        UserDo userDo = new UserDo();
//        userMapper.update(null,wrapper);

        return Result.buildSuccess();
    }

    @GetMapping("/insert")
    @ApiOperation("add")
    public Result addUser() {
        UserDo user = new UserDo();
        user.setAge(22);
        user.setName("tom");
        userMapper.insert(user);

        return Result.buildSuccess();
    }
//
//    /**
//     * 分页查询
//     *
//     * @param user        筛选条件
//     * @param pageRequest 分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public Result<Page<UserCo>> queryByPage(UserCo user, PageRequest pageRequest) {
//        return Result.buildSuccess(this.userService.queryByPage(user, pageRequest));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public Result<UserCo> queryById(@PathVariable("id") Long id) {
//        return Result.buildSuccess(this.userService.queryById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param user 实体
//     * @return 新增结果
//     */
//    @PostMapping
//    public Result<UserCo> add(UserCo user) {
//        return Result.buildSuccess(this.userService.insert(user));
//    }
//
//    /**
//     * 编辑数据
//     *
//     * @param user 实体
//     * @return 编辑结果
//     */
//    @PutMapping
//    public Result<UserCo> edit(UserCo user) {
//        return Result.buildSuccess(this.userService.update(user));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id 主键
//     * @return 删除是否成功
//     */
//    @DeleteMapping
//    public Result<Boolean> deleteById(Long id) {
//        return Result.buildSuccess(this.userService.deleteById(id));
//    }

}

