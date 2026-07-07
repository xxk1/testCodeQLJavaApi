package com.lztech.site.until;


import com.lztech.site.constants.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回工具类
 * 1、传入resultMap代表将要附加数据，不传入使用方法默认值
 */
public class ResponseUtils {

    /**
     * 判断对象不为空
     */
    public static boolean checkObjectIsNotEmpty(Object object) {
        return object != null;
    }

    /**
     * 检查对象是否创建 并返回相应的结果
     *
     * @param object    传入需要检查的对象
     * @param resultMap 传进来的Map
     * @return 判断好的结果 不为空返回 true 为空返回 false
     */
    public static Map<String, Object> checkSaveObject(Object object, Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        if (checkObjectIsNotEmpty(object)) {
            resultMap = getSuccessResultMap(resultMap);
        } else {
            resultMap = getFailedResultMap(resultMap);
        }
        return resultMap;
    }

    /**
     * 检查对象是否创建 并返回相应的结果
     *
     * @param object 传入需要检查的对象
     * @return 判断好的结果
     */
    public static Map<String, Object> checkSaveObject(Object object) {
        return checkSaveObject(object, null);

    }

    /**
     * 返回验证失败状态码及提示语
     */
    public static Map<String, Object> getValidationFailResultMap(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put("response_status", ResponseStatus.VALIDATION_FAIL.getIndex());
        resultMap.put("message", ResponseStatus.VALIDATION_FAIL.getMessage());
        return resultMap;
    }

    /**
     * 返回验证失败状态码及提示语 不需要附加另外的消息
     */
    public static Map<String, Object> getValidationFailResultMap() {
        return getValidationFailResultMap(null);
    }

    /**
     * 返回接受参数异常的状态码及提示语
     */
    public static Map<String, Object> getParametersAbnormalResultMap(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put("response_status", ResponseStatus.PARAMETERS_ABNORMAL.getIndex());
        resultMap.put("message", ResponseStatus.PARAMETERS_ABNORMAL.getMessage());
        return resultMap;
    }

    /**
     * 返回接受参数异常的状态码及提示语 不需要附加另外的消息
     */
    public static Map<String, Object> getParametersAbnormalResultMap() {
        return getParametersAbnormalResultMap(null);
    }

    /**
     * 返回操作成功的状态码及提示语
     */
    public static Map<String, Object> getSuccessResultMap(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put("response_status", ResponseStatus.SUCCESS.getIndex());
        resultMap.put("message", ResponseStatus.SUCCESS.getMessage());
        return resultMap;
    }

    /**
     * 返回操作成功的状态码及提示语 不需要附加另外的消息
     */
    public static Map<String, Object> getSuccessResultMap() {
        return getSuccessResultMap(null);
    }

    /**
     * 返回操作失败的状态码及提示语
     */
    public static Map<String, Object> getFailedResultMap(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put("response_status", ResponseStatus.FAIL.getIndex());
        resultMap.put("message", ResponseStatus.FAIL.getMessage());
        return resultMap;
    }

    /**
     * 返回操作失败的状态码及提示语 不需要附加另外的消息
     */
    public static Map<String, Object> getFailedResultMap() {
        return getFailedResultMap(null);
    }

    /**
     * 返回接口出现异常的状态码及提示语
     */
    public static Map<String, Object> getExceptionResultMap(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put("response_status", ResponseStatus.EXCEPTION.getIndex());
        resultMap.put("message", ResponseStatus.EXCEPTION.getMessage());
        return resultMap;
    }

    /**
     * 返回接口出现异常的状态码及提示语 不需要附加另外的消息
     */
    public static Map<String, Object> getExceptionResultMap() {
        return getExceptionResultMap(null);
    }
}
