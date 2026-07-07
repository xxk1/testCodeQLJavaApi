package com.lztech.site.component.validcode;


import com.lztech.site.constants.Result;
import com.lztech.site.until.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class ValidControllerCode {
    private Logger logger = LoggerFactory.getLogger(ValidControllerCode.class);
    private String validCodeName = "validecode";
    @Autowired
    private HttpServletRequest request;
    @Value("${signKey}")
    private String sinKey;

    @Pointcut("execution(public com.lztech.site.constants.Result com.lztech.site.controllers..*.*(..))&&" +
            "@annotation(com.lztech.site.component.validcode.ValidCode)&&args(param,bindingResult)")
    public void validCode(Object param, BindingResult bindingResult) {
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }

    private ValidCode getValid(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        return method.getDeclaredAnnotation(ValidCode.class);
    }

    private String getCode(ValidCode validCode) {
        String validKey = validCode.value();
        if (!StringUtils.isEmpty(validKey)) {
            String reqRes = getRequestParamByName(validKey);
            if (reqRes != null) {
                validKey = validKey + "=" + reqRes + sinKey;
            }
        } else {
            validKey = sinKey;
        }
        return validKey;

    }

    private boolean argsFromBody(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        Parameter[] parameter = method.getParameters();
        if (parameter == null || parameter.length == 0) {
            return false;
        }

        List<Parameter> parameterList = Stream.of(parameter)
                .filter(o -> Objects.nonNull(o.getAnnotation(RequestBody.class)) ||
                        Objects.nonNull(o.getAnnotation(ModelAttribute.class)))
                .collect(Collectors.toList());
        return !parameterList.isEmpty();
    }

    private String getRequestParamByName(String name) {
        return request.getParameter(name);
    }

    /**
     * @param joinPoint
     * @return
     */
    private Boolean validFromUrl(ProceedingJoinPoint joinPoint) throws ValidCodeIsNull {
        String validMdCode = getRequestParamByName(validCodeName);
        ValidCode validCode = getValid(joinPoint);
        String code = getCode(validCode);
        if (validCode.notValid()) {
            return true;
        }
        if (validMdCode == null) {
            throw new ValidCodeIsNull("验证码不可为空!");
        }
        return RequestUtils.checkValidCode(code, validMdCode);
    }

    private Boolean validFromBody(ProceedingJoinPoint joinPoint, Object param) {
        String validMdCode = getCodeFromArg(validCodeName, param);
        ValidCode validCode = getValid(joinPoint);
        String code = getCodeFromArg(validCode.value(), param);
        if (validCode.notValid()) {
            return true;
        }
        code = validCode.value() + "=" + code + sinKey;
        return RequestUtils.checkValidCode(code, validMdCode);

    }

    private String getCodeFromArg(String validCodeName, Object param) throws ValidCodeIsNull {
        validCodeName = validCodeName.trim();
        if (StringUtils.isEmpty(validCodeName)) {
            return "";
        }
        try {
            Field field = findField(param.getClass(), validCodeName);
            field.setAccessible(true);
            return field.get(param).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new ValidCodeIsNull("验证参数为空");
        }
    }

    private Field findField(Class<?> clazz, String name) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() == null) {
                throw new NoSuchFieldException();
            }
            return findField(clazz.getSuperclass(), name);
        }
    }

    private Result getErrorMsg(BindingResult bindingResult) {
        List<FieldError> error = bindingResult.getFieldErrors();
        List errorMap = error.stream().map(o -> new HashMap<String, String>() {{
            put(o.getField(), o.getDefaultMessage());
        }}).collect(Collectors.toList());
        return Result.error(errorMap);
    }

    /**
     * 暂时不支持body获取的方式进行参数的验证
     *
     * @param joinPoint
     * @param param
     * @return
     * @throws Throwable
     */

    @Around("validCode(param,bindingResult)")
    public Result doBefore(ProceedingJoinPoint joinPoint, Object param, BindingResult bindingResult) throws Throwable {
        Boolean checkResult;

        if (getValid(joinPoint).notValid()) {
            Object result = joinPoint.proceed();
            return (Result) result;
        }
        if (bindingResult.hasErrors()) {
            return getErrorMsg(bindingResult);
        }
        try {
            if (argsFromBody(joinPoint)) {
                checkResult = validFromBody(joinPoint, param);
            } else {
                checkResult = validFromUrl(joinPoint);
            }
        } catch (ValidCodeIsNull e) {
            return Result.error(e.getMessage());
        }

        if (!checkResult) {
            return Result.error("验证秘钥错误!");
        }

        Object result = joinPoint.proceed();
        return (Result) result;
    }


}
