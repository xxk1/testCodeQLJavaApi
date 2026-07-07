package com.lztech.site.until;

import com.lztech.site.component.result.ResultData;
import com.lztech.site.component.result.Status;
import com.lztech.site.config.Access;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;

@Component
public class ValidUtils {

    @Value("${signKey}")
    private String signKey;


    public static boolean checkAuthCode(String requestAuthCode) {
        return Md5Utils.md5(Access.signKey).equals(requestAuthCode);
    }


    public static boolean checkAuthCode(String checkName, String value, String requestAuthCode) {
        return Md5Utils.md5(checkName + "=" + value + Access.signKey).equals(requestAuthCode);
    }

    public ResultData getOpenIdParamResult(String openId, String validCode, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultData.create(Status.ERROR_PARAM_NULL_OR_FORMAT_ERROR);
        }
        if (!checkAuthCode("openId", openId, validCode)) {
            return ResultData.create(Status.ERROR_VALID);
        }
        return null;
    }


    public ResultData getTeacherIdParamResult(String teacherId, String validCode, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultData.create(Status.ERROR_PARAM_NULL_OR_FORMAT_ERROR);
        }
        if (!checkAuthCode("teacherId", teacherId, validCode)) {
            return ResultData.create(Status.ERROR_VALID);
        }
        return null;
    }

    public ResultData getCourseInfoIdParamResult(String courseInfoId, String validCode, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultData.create(Status.ERROR_PARAM_NULL_OR_FORMAT_ERROR);
        }
        if (!checkAuthCode("courseInfoId", courseInfoId, validCode)) {
            return ResultData.create(Status.ERROR_VALID);
        }
        return null;
    }

    public static boolean checkValidCode(String checkName, String value, String requestAuthCode) {
        String md5String = checkName + "=" + value + Access.signKey;
        return DigestUtils.md5DigestAsHex(md5String.getBytes()).equals(requestAuthCode);
    }

}
