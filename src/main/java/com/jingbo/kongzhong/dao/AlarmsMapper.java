package com.jingbo.kongzhong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingbo.kongzhong.entity.Alarms;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 报警表 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Mapper
public interface AlarmsMapper extends BaseMapper<Alarms> {

}
