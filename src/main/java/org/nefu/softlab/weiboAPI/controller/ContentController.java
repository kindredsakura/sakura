package org.nefu.softlab.weiboAPI.controller;


import com.github.pagehelper.PageInfo;
import org.nefu.softlab.weiboAPI.service.WeiboDataService;
import org.nefu.softlab.weiboAPI.service.UserService;
import org.nefu.softlab.weiboAPI.model.RESTData;
import org.nefu.softlab.weiboAPI.utils.LogUtil;
import org.nefu.softlab.weiboAPI.utils.TokenUtil;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.nefu.softlab.weiboAPI.entity.User;
import org.nefu.softlab.weiboAPI.entity.WeiboData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 数据检索控制器
 * 该控制器用于进行数据库中数据的检索
 * 包括用户的信息检索和微博的信息检索
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("api/content")
@RestController
public class ContentController {

    private final UserService userService;
    private final WeiboDataService weiboDataService;
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    public ContentController(UserService userService, WeiboDataService weiboDataService) {
        this.userService = userService;
        this.weiboDataService = weiboDataService;
    }

    /**
     * 获取粉丝数TOP的微博大咖
     */
    @RequestMapping(value = "user/famous", method = RequestMethod.GET)
    public RESTData getFamousSinaUser(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get famous Sina User Data : " + LogUtil.getUserInfo(user));
        List returnList = weiboDataService.getFamousSinaUser();
        return returnList == null ? new RESTData(1, "获取微博TOP用户相关信息失败，请联系系统管理员")
                : new RESTData(returnList);
    }

    /**
     * 获取记录数
     * 可以根据参数自行选择获取哪种参数
     * 因为获取参数还是挺费时间的
     */
    @RequestMapping(value = "number", method = RequestMethod.GET)
    public RESTData getRecordsNumber(@RequestParam("user") Boolean haveUser, @RequestParam("weibo") Boolean haveWeibo, HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (haveUser == null || haveWeibo == null)
            return new RESTData(1, "请检查您输入的参数是否正确");
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get famous Sina User Data : " + LogUtil.getUserInfo(user));
        Map returnMap = weiboDataService.getRecordsNumber(haveUser, haveWeibo);
        return returnMap == null ? new RESTData(1, "获取系统中用户或微博数失败，请联系系统管理员")
                : new RESTData(returnMap);
    }

    /**
     * 获取特定记录
     */
    @RequestMapping(value = "records", method = RequestMethod.GET)
    public RESTData getSpecificRecords(RecordsSelectVo vo, HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get specific weibo records, User : " + LogUtil.getUserInfo(user) + " | params : " + vo);
        PageInfo<WeiboData> page = weiboDataService.getSpecificRecords(vo);
        return page == null ?
                new RESTData(1, "查询微博数据失败，请联系系统管理员")
                : weiboDataService.getUid(vo.getUid()) == null ? new RESTData(0, "未查询到当前用户的信息", page) : new RESTData(page);
    }
}