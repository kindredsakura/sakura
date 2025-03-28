package org.nefu.softlab.weiboAPI.vo;

/**
 * 数据查询条件VO
 */
public class RecordsSelectVo {
    private String uname;  // 用户昵称
    private Integer pageNum;  // 页码
    private Integer pageSize;  // 每页大小
    private String uid;  // 用户ID

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "RecordsSelectVo{" +
                "uname='" + uname + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", uid='" + uid + '\'' +
                '}';
    }
} 