package io.choerodon.asgard.api.eventhandler;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hailuo.liu@choerodon.io on 2019/5/15.
 */
@Component
public class SagaInstanceEventPublisher {
    final public static String SAGA_INSTANCE_TOPIC="saga_instance";
    final public static String TAST_INSTANCE_PREFIX="TASK";
    final public static String QUARTZ_INSTANCE_PREFIX="QUARTZ";
    final public static String PLACEHOLDER="#";
    private StringRedisTemplate stringRedisTemplate;

    public SagaInstanceEventPublisher(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void sagaTaskInstanceEvent(String sagaTaskInstanceService){
        this.stringRedisTemplate.convertAndSend(SAGA_INSTANCE_TOPIC,String.format("%s%s%s",TAST_INSTANCE_PREFIX,PLACEHOLDER, sagaTaskInstanceService));
    }

    public void quartzInstanceEvent(String quartzInstanceService){
        this.stringRedisTemplate.convertAndSend(SAGA_INSTANCE_TOPIC,String.format("%s%s%s",QUARTZ_INSTANCE_PREFIX,PLACEHOLDER, quartzInstanceService));
    }

    public static String getMessageKey(String type,String service){
        return String.format("%s%s%s",type,PLACEHOLDER,service);
    }
}
