package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.entity.Alarms;
import com.jingbo.kongzhong.dao.AlarmsMapper;
import com.jingbo.kongzhong.service.IAlarmsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报警表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Service
public class AlarmsServiceImpl extends ServiceImpl<AlarmsMapper, Alarms> implements IAlarmsService {

}
