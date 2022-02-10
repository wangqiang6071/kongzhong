package com.jingbo.kongzhong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingbo.kongzhong.entity.Role;
import com.jingbo.kongzhong.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> selectRoleListByUserAccount(String account);

    User selectUserByAccount(@Param("account") String account);

}