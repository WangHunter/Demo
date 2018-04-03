package com.dao.impl;

import com.dao.RecommendDataDao;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2017/8/21.
 */
@Service(value = "recommendDataDao")
public class RecommendDataDaoImpl implements RecommendDataDao {

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(RecommendDataDaoImpl.class
            .getName());

    @Override
    public String queryAllItem(String userid) {
        return "SUCCESS";
    }
}
