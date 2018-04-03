package com.service.impl;

import com.dao.RecommendDataDao;
import com.service.QueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/29.
 */
@Service(value = "queryServiceImpl")
public class QueryServiceImpl implements QueryService {
    private static final Logger log = LogManager.getLogger(QueryServiceImpl.class
            .getName());

    @Autowired
    private RecommendDataDao recommendDataDao;

    @Override
    public String queryItemByUserid(String userid) {
        String doc = recommendDataDao.queryAllItem(userid);
        return doc;
    }
}
