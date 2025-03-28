package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.service.SpiderService;
import org.nefu.softlab.weiboAPI.service.UserService;
import org.nefu.softlab.weiboAPI.model.RESTData;
import org.nefu.softlab.weiboAPI.utils.LogUtil;
import org.nefu.softlab.weiboAPI.utils.TokenUtil;
import org.nefu.softlab.weiboAPI.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 爬虫监控控制器
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("api/spider")
@RestController
public class SpiderController {

    private final SpiderService spiderService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);

    @Autowired
    public SpiderController(SpiderService spiderService, UserService userService) {
        this.spiderService = spiderService;
        this.userService = userService;
    }

    /**
     * 获取IP池的相关信息
     */
    @RequestMapping(value = "ippool", method = RequestMethod.GET)
    public RESTData getIppoolData(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get Ippool Data : " + LogUtil.getUserInfo(user));
        Map returnMap = spiderService.getIppoolData();
        return returnMap == null ? new RESTData(1, "获取IP相关信息失败，请联系系统管理员")
                : new RESTData(returnMap);
    }

    /**
     * 获取爬虫的速率等相关信息
     */
    @RequestMapping(value = "status", method = RequestMethod.GET)
    public RESTData getStatus(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get Spider Status Data : " + LogUtil.getUserInfo(user));
        Map returnMap = spiderService.getStatus();
        return returnMap == null ? new RESTData(1, "获取爬虫状态信息失败，请联系系统管理员")
                : new RESTData(returnMap);
    }

    /**
     * 获取近七天内集群的容量数据
     */
    @RequestMapping(value = "sevenday", method = RequestMethod.GET)
    public RESTData getSevenDayDataRange(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get Seven Day Status Data : " + LogUtil.getUserInfo(user));
        List returnMap = spiderService.getSevenday();
        return returnMap == null ? new RESTData(1, "获取爬虫状态信息失败，请联系系统管理员")
                : new RESTData(returnMap);
    }

    /**
     * 查询数据库服务器内存占用率
     */
    @RequestMapping(value = "mem", method = RequestMethod.GET)
    public RESTData getMemoryStatus(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get Memory Status : " + LogUtil.getUserInfo(user));
        List returnData = spiderService.getMemoryStatus();
        return returnData != null ? new RESTData(returnData)
                : new RESTData(1, "当前系统繁忙请稍后重试");
    }

    /**
     * 获取当天的数据增量
     */
    @RequestMapping(value = "increase/today", method = RequestMethod.GET)
    public RESTData getTodayIncreasement(HttpServletRequest request) {
        User user = userService.selectUserByToken(TokenUtil.getToken(request));
        if (user == null)
            return new RESTData(1, "请检查当前登陆状态");
        logger.info("GET Get today increasement : " + LogUtil.getUserInfo(user));
        List returnData = spiderService.getTodayIncreasement();
        return returnData != null ? new RESTData(returnData)
                : new RESTData(1, "当前系统繁忙请稍后重试");
    }
} 