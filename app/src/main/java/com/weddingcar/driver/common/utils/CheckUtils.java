package com.weddingcar.driver.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {

    public static boolean checkMobile(String mobile, boolean allowTel) {

        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Pattern p;
        if (allowTel) {
            //允许输入固话
            p = Pattern.compile("^(1\\d{10})|(0\\d{10})|(0\\d{11})|(4\\d{9})|(8\\d{9})$");
        } else {
            //不允许输入固话
            p = Pattern.compile("^(1\\d{10})$");
        }
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean checkCode(String code) {
        if (StringUtils.isEmpty(code) || code.length() != 6) {
            return false;
        }

        String str = "^[0-9]*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(code);

        return m.matches();
    }


    public static boolean checkNumber(String number) {
        if (StringUtils.isEmpty(number)) {
            return false;
        }
        String str = "^[0-9]*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(number);

        return m.matches();
    }

    public static boolean checkPassword(String password) {
        if (StringUtils.isEmpty(password) || password.length() < 8) {
            return false;
        }
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,40}$");
//        Pattern p = Pattern.compile("^(?![a-zA-z]+$)(?!\\\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\\\d!@#$%^&*]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * 条码校验
     *
     * @param str 条码
     * @return 通过为[true], 否则为[false]
     */
    public static boolean checkBarCode(String str) {
        String barcode = str.trim();
        if (StringUtils.isEmpty(barcode) || "0".equals(barcode.substring(0, 1))) {
            return false;
        }

        Pattern p1 = Pattern.compile("^\\d{13}$");
        Pattern p2 = Pattern.compile("^\\d{18}$");
        Pattern p3 = Pattern.compile("^\\d{24}$");

        Matcher m1 = p1.matcher(barcode);
        Matcher m2 = p2.matcher(barcode);
        Matcher m3 = p3.matcher(barcode);


        if (!m1.matches() && !m2.matches() && !m3.matches()) {
            return false;
        }

        if ((barcode.length() != 13) && (barcode.length() != 18) && (barcode.length() != 24)) {
            return false;
        }

        String code;
        String pakistanGunCode;

        if (barcode.length() > 13) {
            code = barcode.substring(0, 13);
            String checkCode = barcode.substring(13, 17);
            if (barcode.length() > 18) {
                //巴枪校验码
                pakistanGunCode = barcode.substring(23, 24);
            } else {
                //巴枪校验码
                pakistanGunCode = barcode.substring(17, 18);
            }

            int sum = 0;
            //校验规则
            for (int i = 0; i < code.length(); i++) {
                String s = code.substring(i, i + 1);
                sum = sum + Integer.parseInt(s) * Integer.parseInt(checkCode);
            }
            //运算得到的结果，必须和截取的巴枪校验码对比，两者相等，才会继续下面的程序
            int result = sum % 10;
            if (!(result == Integer.parseInt(pakistanGunCode))) {
                return false;
            }

        }

        return true;
    }

    // 验证重量是否合法：要求范围为0.10到60.00。
    public static boolean checkWeight(String weight, float max, float min) {
        if (StringUtils.isEmpty(weight)) {
            return false;
        }

        // 验证格式：至多2位小数的正实数
        Pattern p = Pattern.compile("^[0-9]+\\.?[0-9]{0,2}$");
        Matcher m = p.matcher(weight);
        // 比较物品重量是否合法
        if (m.matches()) {
            if (Float.parseFloat(weight) > max || Float.parseFloat(weight) < min) {
                return false;
            }
            // 去除末尾为.的情况
            if (".".equals(String.valueOf(weight.charAt(weight.length() - 1)))) {
                return false;
            }
            return true;
        }

        return false;
    }


    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean checkChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (char c : ch) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /************************************身份证号码验证：start*****************************************/
    /**
     * 功能：身份证的有效验证
     * @param idStr 身份证号
     * @return 有效：返回"" 无效：返回String信息
     */
    public static String checkIDCardValid(String idStr)  {
        if (StringUtils.isEmpty(idStr)) return "省份证号不能为空";
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String ai = "";
        //号码的长度 15位或18位
        if (idStr.length() != 15 && idStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }

        // 数字 除最后以为都为数字
        if (idStr.length() == 18) {
            ai = idStr.substring(0, 17);
        } else if (idStr.length() == 15) {
            ai = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
        }
        if (isNumeric(ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }

        // 出生年月是否有效
        String strYear = ai.substring(6, 10);// 年份
        String strMonth = ai.substring(10, 12);// 月份
        String strDay = ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }

        try {
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                errorInfo = "身份证月份无效";
                return errorInfo;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                errorInfo = "身份证日期无效";
                return errorInfo;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 地区码时候有效
        Hashtable h = getAreaCode();
        if (h.get(ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // 判断最后一位的值
        int totalMulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            totalMulAiWi = totalMulAiWi
                    + Integer.parseInt(String.valueOf(ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = totalMulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        ai = ai + strVerifyCode;

        if (idStr.length() == 18) {
            if (ai.equals(idStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        return "";
    }

    /**
     * 功能：判断字符串是否为数字
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：设置地区编码
     * @return Hashtable 对象
     */
    private static Hashtable getAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**验证日期字符串是否是YYYY-MM-DD格式
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str){
        boolean flag=false;
        String regStr="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1= Pattern.compile(regStr);
        Matcher isNo=pattern1.matcher(str);
        if(isNo.matches()){
            flag=true;
        }
        return flag;
    }

    /************************************身份证号码验证：end*****************************************/


    /**
     * 防止重复点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick(int delayTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < delayTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 金额验证
      * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 检测手机是否安装微博App
     * @param context
     * @return
     */
    public static boolean isWeiboInstalled(@NonNull Context context) {
        PackageManager pm;
        if ((pm = context.getApplicationContext().getPackageManager()) == null) {
            return false;
        }
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo info : packages) {
            String name = info.packageName.toLowerCase(Locale.ENGLISH);
            if ("com.sina.weibo".equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验运单号
     * @param num
     * @return
     */
    public static boolean isWaybillNo(String num) {
        if(StringUtils.isEmpty(num)){
            return false;
        }
        Pattern pattern= Pattern.compile("^[1-8]((\\d{12})|(\\d{17}))$"); // 判断不能以0,9开头的13或者18位数
        Matcher match=pattern.matcher(num);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 校验运单号是否可以获取手机号
     * @param num
     * @return
     */
    public static boolean isGetNameWaybillNo(String num) {
        if (!isWaybillNo(num)){
            return false;
        }
        String substring = num.substring(0, 2);
        if (StringUtils.equals(substring, "31") || StringUtils.equals(substring, "50")
                || StringUtils.equals(substring, "60") || StringUtils.equals(substring, "77")) {
            return true;
        }
        return false;
    }
}

