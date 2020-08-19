package com.java.until.validate;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.java.moudle.common.message.JsonResult;
import com.java.until.CommonException;
import com.java.until.StringUtils;
import com.java.until.StringUtils;

public class ValidateUtil {
    /*public static void main(String[] args) {
        SignDto signdto = new SignDto();
        List<SignPackageDto> list = new ArrayList<>();
        SignPackageDto dto = new SignPackageDto();
        dto.setPackageId("1");
        list.add(dto);
        signdto.setSignPackageDtoList(list);
        String msg = validateField(signdto);
        System.out.println(msg);
    }*/

    //校验实体类及其父类
    public static String validateField(Object obj, String... isClash) {
        if (obj == null)
            return "请求参数不能为空！";
        String msg = "";
        msg = validateClass(obj, obj.getClass(), isClash);
        if (StringUtils.isNotBlank(msg))
            return msg;
        msg = validateClass(obj, obj.getClass().getSuperclass(), isClash);
        if (StringUtils.isNotBlank(msg))
            return msg;
        return msg;

    }

    //校验实体类
    public static String validateClass(Object obj, Class cla, String... isClash) {
        if (obj == null)
            return "请求参数不能为空！";
        String msg = "";
        try {
            Field[] fieldArray = cla.getDeclaredFields();
            for (Field field : fieldArray) {
                //获取字段上的注解
                Validate validate = field.getAnnotation(Validate.class);
                if (validate != null) {
                    //required为true则校验
                    if (validate.required()) {
                        //clash为true时，isClash必须为1才能校验
                        if (validate.clash()) {
                            if (isClash.length == 0 || !"1".equals(isClash[0])) {
                                continue;
                            }
                        }
                        field.setAccessible(true);
                        Object fieldValue = field.get(obj);
                        Class fieldType = field.getType();
                        //校验集合、基本类型和实体类
                        if (fieldType == List.class) {
                            List value = (List) field.get(obj);
                            if (value == null || value.size() == 0) {
                                return validate.message();
                            } else {
                                for (Object o : value) {
                                    msg = validateField(o);
                                    if (StringUtils.isNotBlank(msg))
                                        return msg;
                                }
                            }
                        } else if (fieldType == String.class || fieldType == Integer.class || fieldType == Long.class
                                || fieldType == Double.class || fieldType == Float.class || fieldType == Short.class || fieldType == Byte.class
                                || fieldType == Character.class || fieldType == Boolean.class || fieldType == Date.class){
                            Object value = field.get(obj);
                            if (value == null || value.equals(""))
                                return validate.message();
                        } else {
                            if (fieldValue == null)
                                return validate.message();
                            msg = validateField(fieldValue);
                            if (StringUtils.isNotBlank(msg))
                                return msg;
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;

    }

    public static void validate(Object obj, HttpServletResponse response) {
        VldObj validate = new VldObj();

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;

        try {
            onDateVldError(response, msg);
            throw new CommonException(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String validate(Object obj) {
        VldObj validate = new VldObj();

        String msg = validate.getVldMsg(obj);

        return msg;

    }

    /**
     * 转下划线 小写
     * <li>描述:</li>
     * <li>参数:@param obj</li>
     * <li>返回类型:void</li>
     * <li>最后更新作者:gaoqs</li>
     */
    public static void vldObjCamelLowerCase(Object obj, HttpServletResponse response) {
        VldObj validate = new VldObj(3, 2, null);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        try {
            onDateVldError(response, msg);
            throw new CommonException(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String vldObjCamelLowerCase(Object obj) {
        VldObj validate = new VldObj(3, 2, null);

        String msg = validate.getVldMsg(obj);

        return msg;
    }

    public static void vldObjCamelLowerCase(Object obj, String[] patternArray, HttpServletResponse response) throws Exception {
        VldObj validate = new VldObj(3, 2, patternArray);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        throw new CommonException(msg);
    }

    /**
     * 转驼峰
     * <li>描述:</li>
     * <li>参数:@param obj</li>
     * <li>返回类型:void</li>
     * <li>最后更新作者:gaoqs</li>
     */
    public static void vldObjUnderScoreLowerCase(Object obj, HttpServletResponse response) {
        VldObj validate = new VldObj(2, 1, null);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        try {
            onDateVldError(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void vldObjUnderScoreLowerCase(Object obj, String[] patternArray, HttpServletResponse response) {
        VldObj validate = new VldObj(2, 1, patternArray);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        try {
            onDateVldError(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转下划线 大写
     * <li>描述:</li>
     * <li>参数:@param obj</li>
     * <li>返回类型:void</li>
     * <li>最后更新作者:gaoqs</li>
     */
    public static void vldObjCamelUpperCase(Object obj, HttpServletResponse response) {
        VldObj validate = new VldObj(3, 3, null);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        try {
            onDateVldError(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void vldObjCamelUpperCase(Object obj, String[] patternArray, HttpServletResponse response) {
        VldObj validate = new VldObj(3, 3, patternArray);

        String msg = validate.getVldMsg(obj);
        if (StringUtils.isNull(msg)) return;
        try {
            onDateVldError(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <li>描述:数据校验错误</li>
     * <li>方法名称:onAuthFail</li>
     * <li>参数:@param response
     * <li>参数:@throws Exception</li>
     * <li>返回类型:void</li>
     * <li>最后更新作者:gaoqs</li>
     */
    private static void onDateVldError(ServletResponse response, String msg) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        response.setContentType("application/json;charset=UTF-8");
        JsonResult ret = new JsonResult("数据校验错误", 10000, String.format("数据校验错误:\n%s", msg));
        //	httpResponse.getWriter().write(ret.toJsonString());
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

//	public static void main(String[] args) {
//
//		VldTestArray tArray1 = new VldTestArray();
//		tArray1.setId("1");
//		tArray1.setTele("123");
//		VldTestArray tArray2 = new VldTestArray();
//		tArray2.setId("");
//		tArray2.setTele("0531-88881234");
//
//		List<VldTestArray> tArray = new ArrayList<VldTestArray>();
//		tArray.add(tArray1);
//		tArray.add(tArray2);
//
//		VldTest t = new VldTest();
//		t.setId("");
//		t.setPhone("00000000000");
//		t.setRemarks("11");
//		t.setTestArray(tArray);
//		t.setTest(tArray1);
//
//		VldObj validate = new VldObj(2, 2, new String[] { "acc", "*" });
//
//		String msg = validate.getVldMsg(t);
//
//		System.out.println(msg);
//
//	}

}
