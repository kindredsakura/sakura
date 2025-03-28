package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.entity.WeiboUser;
import org.nefu.softlab.weiboAPI.service.WeiboUserService;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微博用户控制器
 */
@RestController
@RequestMapping("/api/weibo-user")
public class WeiboUserController {

    @Autowired
    private WeiboUserService weiboUserService;

    /**
     * 获取TOP50的微博大咖
     * @return 微博用户列表
     */
    @GetMapping("/famous")
    public List<WeiboUser> getFamousUsers() {
        return weiboUserService.getFamousUsers();
    }

    /**
     * 获取微博用户数
     * @return 用户数量
     */
    @GetMapping("/count")
    public Long getUserCount() {
        return weiboUserService.selectUserCount();
    }

    /**
     * 根据昵称查询用户ID
     * @param uname 用户昵称
     * @return 用户ID
     */
    @GetMapping("/uid/{uname}")
    public String getUidByName(@PathVariable String uname) {
        return weiboUserService.selectUidByName(uname);
    }

    /**
     * 根据昵称模糊查询用户ID列表
     * @param vo 查询条件
     * @return 用户ID列表
     */
    @PostMapping("/uids")
    public List<String> getUidsByName(@RequestBody RecordsSelectVo vo) {
        return weiboUserService.selectUidsbyName(vo);
    }

    /**
     * 删除微博用户
     * @param uid 用户ID
     * @return 影响的行数
     */
    @DeleteMapping("/{uid}")
    public int deleteUser(@PathVariable String uid) {
        return weiboUserService.deleteByPrimaryKey(uid);
    }

    /**
     * 新增微博用户
     * @param weiboUser 微博用户对象
     * @return 影响的行数
     */
    @PostMapping
    public int addUser(@RequestBody WeiboUser weiboUser) {
        return weiboUserService.insert(weiboUser);
    }

    /**
     * 选择性新增微博用户
     * @param weiboUser 微博用户对象
     * @return 影响的行数
     */
    @PostMapping("/selective")
    public int addUserSelective(@RequestBody WeiboUser weiboUser) {
        return weiboUserService.insertSelective(weiboUser);
    }

    /**
     * 根据ID查询微博用户
     * @param uid 用户ID
     * @return 微博用户对象
     */
    @GetMapping("/{uid}")
    public WeiboUser getUserById(@PathVariable String uid) {
        return weiboUserService.selectByPrimaryKey(uid);
    }

    /**
     * 选择性更新微博用户
     * @param weiboUser 微博用户对象
     * @return 影响的行数
     */
    @PutMapping("/selective")
    public int updateUserSelective(@RequestBody WeiboUser weiboUser) {
        return weiboUserService.updateByPrimaryKeySelective(weiboUser);
    }

    /**
     * 更新微博用户
     * @param weiboUser 微博用户对象
     * @return 影响的行数
     */
    @PutMapping
    public int updateUser(@RequestBody WeiboUser weiboUser) {
        return weiboUserService.updateByPrimaryKey(weiboUser);
    }
} 