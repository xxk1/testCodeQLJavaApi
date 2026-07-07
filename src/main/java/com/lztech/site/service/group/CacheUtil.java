package com.lztech.site.service.group;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    //默认大小
    private static final int DEFAULT_CAPACITY = 10240;

    // 最大缓存大小
    private static final int MAX_CAPACITY = 102400;

    //默认缓存过期时间
    private static final long DEFAULT_TIMEOUT = 999999999;

    //1000毫秒
    private static final long SECOND_TIME = 1000;

    //存储缓存的Map
    private static final ConcurrentHashMap<String, Object> MAP;

    private static final Timer TIMER;

    static {
        MAP = new ConcurrentHashMap<>(DEFAULT_CAPACITY);
        TIMER = new Timer();
    }

    //私有化构造方法
    private CacheUtil() {

    }

    /**
     * <pre>
     *     缓存任务清除类
     * </pre>
     */
    static class ClearTask extends TimerTask {
        private String key;

        public ClearTask(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            CacheUtil.remove(key);
        }

    }

    //==================缓存的增删改查

    /**
     *     添加缓存
     */
    public static boolean put(String key, Object object) {
        if (checkCapacity()) {
            MAP.put(key, object);
            //默认缓存时间
            TIMER.schedule(new ClearTask(key), DEFAULT_TIMEOUT);
            return true;
        }
        return false;
    }

    /**
     *     添加缓存
     */
    public static boolean put(String key, Object object, int time_out) {
        if (checkCapacity()) {
            MAP.put(key, object);
            //默认缓存时间
            TIMER.schedule(new ClearTask(key), time_out * SECOND_TIME);
        }
        return false;
    }


    /**
     *     判断容量大小
     */
    public static boolean checkCapacity() {
        return MAP.size() < MAX_CAPACITY;
    }

    /**
     *     批量增加缓存
     */
    public static boolean put(Map<String, Object> m, int time_out) {
        if (MAP.size() + m.size() <= MAX_CAPACITY) {
            MAP.putAll(MAP);
            for (String key : m.keySet()) {
                TIMER.schedule(new ClearTask(key), time_out * SECOND_TIME);
            }
            return true;
        }
        return false;
    }

    /**
     *     删除缓存
     */
    public static void remove(String key) {
        MAP.remove(key);
    }

    /**
     *     清除所有缓存
     */
    public static void clearAll() {
        if (MAP.size() > 0) {
            MAP.clear();
        }
    }

    /**
     *     获取缓存
     */
    public static Object get(String key) {
        return MAP.get(key);
    }

    /**
     *     是否包含某个缓存
     */
    public static boolean isContainKey(String key) {
        return MAP.containsKey(key);
    }
}