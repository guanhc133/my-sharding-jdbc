package com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.mapper;

import com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.entity.OrderNewInfoEntity;
import com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.entity.UserInfoEntity;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:52
 * @className UserMapper
 * @desc 用户Mapper
 */
public interface UserMapper {

    int addUser(UserInfoEntity userInfoEntity);

}
