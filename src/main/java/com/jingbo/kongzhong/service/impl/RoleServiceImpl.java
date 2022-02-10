package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.entity.Role;
import com.jingbo.kongzhong.dao.RoleMapper;
import com.jingbo.kongzhong.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
