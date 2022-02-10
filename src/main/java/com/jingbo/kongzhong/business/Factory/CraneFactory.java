package com.jingbo.kongzhong.business.Factory;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.jingbo.kongzhong.business.entity.Model.MatchingGroup;
import com.jingbo.kongzhong.exception.StringException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.AssignableTypeFilter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangqiang
 * @Date: 2022/1/21 14:42
 * 吊车的工厂类：加载所有的吊车实例对象
 */
@Slf4j
public abstract class CraneFactory implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 吊车工厂单元
     **/
    private static Map<String, CraneFactory> CRANE_STRATEGY = new ConcurrentHashMap<>();
    /**
     * 配组表数据
     */
    private static Map<String, MatchingGroup> MATCH_GROUP = new ConcurrentHashMap<>();

    /**
     * @author ：wangqiang
     * @date ：2022-01-21 14:46
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            // 初始化类路径扫描组件
            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
            // 指定要过滤的类型
            provider.addIncludeFilter(new AssignableTypeFilter(CraneFactory.class));
            // 获取扫描结果
            Set<BeanDefinition> components = provider.findCandidateComponents("com.jingbo.kongzhong.business.cranes");

            for (BeanDefinition component : components) {
                // 通过类类名称获取类型
                Class<?> classes = Class.forName(component.getBeanClassName());
                // 获取类全名称,例如：com.zelu.authorizecode.plug.factory.StrategyFactory
                String simpleName = classes.getSimpleName();
                // 截取出类实现名，例如StrategyFactory = strategyFactory
                String firstLowerName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                // 通过类名从上下文中获取类
                CraneFactory crane = (CraneFactory) event.getApplicationContext().getBean(classes);
                // 填充到工厂中
                CraneFactory.CRANE_STRATEGY.put(firstLowerName, crane);
            }
        } catch (Exception e) {
            log.error(String.format("加载插件工厂时发生异常，异常信息：%s", e.getMessage()));
            e.printStackTrace();
        }
        if (CollectionUtil.isEmpty(CraneFactory.CRANE_STRATEGY)) {
            log.error("插件工厂加载异常，请查看系统加载日志。");
        }
    }

    /**
     * 根据key获取策略
     * @author ：wangqiang
     * @date 2022-1-21 16:47
     */
    public static CraneFactory getPlugStrategy(String craneType) {

        if (StringUtils.isBlank(craneType)) {
            throw new StringException("策略模块名称不能为空.");
        }
        if (!CraneFactory.checkPlugStrategy(craneType)) {
            throw new StringException("根据策略模块名称未匹配到策略,入参 ==> " + craneType);
        }
        return CraneFactory.CRANE_STRATEGY.get(craneType);
    }

    /**
     * 根据key校验插件策略是否存在
     * @author :wangqiang
     * @date 2022-1-21 16:47
     */
    public static boolean checkPlugStrategy(String strategyModule) {
        return CRANE_STRATEGY.containsKey(strategyModule);
    }

    /**
     * 装车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    public void entrunk(JSONObject json) {}

    /**
     * 卸车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    public void unload(JSONObject json) {}

    /**
     * 地面平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */

    public void groundtranslation(JSONObject json) {}

    /**
     * 车厢平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    public void carriagetranslation(JSONObject json) {
        /**
         * 转化对应的对象
         */

    }

    /**
     * 单类型装车清单
     */
    public void entruckDetailedList() {}

    /**
     * 单类型卸车清单
     */
    public void unloadDetailedList() {}

    /**
     * 总装卸车清单
     */
    public void AllDetaileList() {
        System.out.println("");
    }
}
