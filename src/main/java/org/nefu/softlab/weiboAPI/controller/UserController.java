package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.service.UserService;
import org.nefu.softlab.weiboAPI.model.RESTData;
import org.nefu.softlab.weiboAPI.utils.LogUtil;
import org.nefu.softlab.weiboAPI.utils.MD5Util;
import org.nefu.softlab.weiboAPI.utils.TokenUtil;
import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户控制器
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RESTData postLogin(@RequestBody User user, HttpServletRequest request) {
        logger.info("POST Post Login : " + LogUtil.getUserInfo(user));
        if (user.getNickname() == null || user.getNickname().trim().equals("") == true)
            return new RESTData(1, "用户名不能为空");
        user = userService.selectUserByUsernameAndPasswd(user);
        if (user == null)
            return new RESTData(1, "用户名或密码不匹配");
        Log lastLogRecord = userService.getLastLog(user);
        Log newLogRecord = LogUtil.getLog(request, lastLogRecord);
        newLogRecord.setUid(user.getUid());
        Map returnData = userService.addLoginRecord(user, newLogRecord, lastLogRecord);
        return returnData != null ? new RESTData(returnData)
                : new RESTData(1, "登录失败，请稍后重试");
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public RESTData postRegister(@RequestBody User user, HttpServletRequest request) {
        logger.info("POST Post Register : " + LogUtil.getUserInfo(user));
        if (user.getNickname() == null || user.getNickname().trim().equals("") == true)
            return new RESTData(1, "用户名不能为空");
        return userService.addUserRecord(user) == true ? new RESTData()
                : new RESTData(1, "请检查您的注册信息");
    }

    /**
     * 用户名判重
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public RESTData getRegister(@RequestParam("nickname") String nickname) {
        logger.info("GET Get Register : { nickname : " +  nickname + " } ");
        return userService.selectUserByUsername(nickname) == null ? new RESTData(true)
                : new RESTData(1, "用户名已存在", false);
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "passwd", method = RequestMethod.PUT)
    public RESTData putPasswd(@RequestParam("oldpasswd") String oldpasswd, @RequestParam("newpasswd") String newpasswd, HttpServletRequest request) {
        logger.info("PUT Put Passwd : { oldpasswd : " + oldpasswd + " , newpasswd : " + newpasswd + "}");
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        else if (oldpasswd.equals(newpasswd) == true)
            return new RESTData(1, "新密码必须和旧密码不同");
        else if (user.getPasswd().equals(MD5Util.MD5(oldpasswd)) == false)
            return new RESTData(1, "密码输入错误");
        return userService.setNewPasswd(user, newpasswd) == true ? new RESTData()
                : new RESTData(1, "密码修改失败，请稍后重试");
    }

    /**
     * 用户注销登录
     */
    @RequestMapping(value = "login", method = RequestMethod.DELETE)
    public RESTData deleteLogin(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        logger.info("DELETE Delete Login : " + LogUtil.getUserInfo(user));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        return userService.deleteLogin(user) == true ? new RESTData()
                : new RESTData(1, "注销失败，请稍后重试");
    }

    /**
     * 根据用户名查询用户
     * @param nickname 用户名
     * @return 用户对象
     */
    @GetMapping("/nickname/{nickname}")
    public User getUserByUsername(@PathVariable String nickname) {
        return userService.selectUserByUsername(nickname);
    }

    /**
     * 根据token查询用户
     * @param token 令牌
     * @return 用户对象
     */
    @GetMapping("/token/{token}")
    public User getUserByToken(@PathVariable String token) {
        return userService.selectUserByToken(token);
    }

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 影响的行数
     */
    @DeleteMapping("/{uid}")
    public int deleteUser(@PathVariable String uid) {
        return userService.deleteByPrimaryKey(uid);
    }

    /**
     * 新增用户
     * @param user 用户对象
     * @return 影响的行数
     */
    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.insert(user);
    }

    /**
     * 选择性新增用户
     * @param user 用户对象
     * @return 影响的行数
     */
    @PostMapping("/selective")
    public int addUserSelective(@RequestBody User user) {
        return userService.insertSelective(user);
    }

    /**
     * 根据ID查询用户
     * @param uid 用户ID
     * @return 用户对象
     */
    @GetMapping("/{uid}")
    public User getUserById(@PathVariable String uid) {
        return userService.selectByPrimaryKey(uid);
    }

    /**
     * 选择性更新用户
     * @param user 用户对象
     * @return 影响的行数
     */
    @PutMapping("/selective")
    public int updateUserSelective(@RequestBody User user) {
        return userService.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新用户
     * @param user 用户对象
     * @return 影响的行数
     */
    @PutMapping
    public int updateUser(@RequestBody User user) {
        return userService.updateByPrimaryKey(user);
    }
} 