package com.dao;

/**
 * Created by Administrator on 2017/8/21.
 */
public interface RecommendDataDao {

    /**
     * 查询用户的详细标签以及得分
     * @param userid
     * @return
     */
    public String queryAllItem(String userid);
}
