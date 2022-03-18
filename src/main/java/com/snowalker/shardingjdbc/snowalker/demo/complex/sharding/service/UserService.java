package com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.service;

import com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.entity.OrderNewInfoEntity;
import com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.entity.UserInfoEntity;
import com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.mapper.UserMapper;
import groovy.util.logging.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:54
 * @className UserService
 * @desc
 */
@Service
@Log4j2
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderNewSerivce.class);

    @Autowired
    UserMapper userMapper;

    public int addUser(UserInfoEntity orderInfo) {
        return userMapper.addUser(orderInfo);
    }
}
