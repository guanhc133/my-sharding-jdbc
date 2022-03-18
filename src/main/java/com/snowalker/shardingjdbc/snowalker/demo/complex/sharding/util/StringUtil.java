package com.snowalker.shardingjdbc.snowalker.demo.complex.sharding.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @date 2019/2/18
 * @desc
 */
public class StringUtil {

    /**
     * 指定字符串位数前缀补零
     * @param value
     * @param needLength
     * @return
     */
    public static String fillZero(String value, int needLength) {
        if (StringUtils.isBlank(value)) {
            StringBuilder result = new StringBuilder();
            for (int i=0;i<needLength;i++) {
                result.append("0");
            }
            return result.toString();
        }
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < needLength - value.length(); i++) {
            str.append("0");
        }
        return str.append(value).toString();
    }

    /**
     * 指定数字字符串位数前缀去零
     * @param value
     * @return
     */
    public static String deleteZero(String value) {
        Long number = Long.valueOf(value);
        return number.toString();
    }

    /**
     * 判断字符串是否是纯数字，从最后一位开始判断
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 转换字符串路由id为纯数字ascII
     * @param str
     * @return
     */
    public static String getAscII(String str) {
        if (str != null && !"".equals(str)) {
            StringBuilder indexSB = new StringBuilder();
            for (int i = 0; i < str.length(); ++i) {
                char[] strChar = str.substring(i, i + 1).toCharArray();
                char[] var4 = strChar;
                int var5 = strChar.length;
                for (int var6=0;var6<var5;++var6) {
                    char s = var4[var6];
                    indexSB.append((byte)s);
                }
            }
            return indexSB.toString();
        } else {
            return "0";
        }
    }

    /**
     * 先对指定对象取ASCII码后取模运算
     * @param obj
     * @param num
     * @return
     */
    public static long getModValue(Object obj,long num) {
        String str = getAscII(obj == null?"":obj.toString());
        BigDecimal bc = new BigDecimal(str);
        BigDecimal[] results = bc.divideAndRemainder(new BigDecimal(num));
        //[bc和num的商,bc和num取模]  这里取results[1]也就是取模之后的值，   任意数和num（表数量）取模，结果值范围在0~num-1 ，以t_user为例，共16张表，取模的范围为0~15
        return (long)results[1].intValue();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            BigDecimal[] results = new BigDecimal(i).divideAndRemainder(new BigDecimal(4));
            System.out.println(JSON.toJSONString(results[0]));

        }


    }

    /**
     * @param obj
     * @param dbCount
     * @param tbCount
     * @return
     */
    public static long getDbIndexByMod(Object obj,int dbCount,int tbCount) {
        long tbRange = getModValue(obj, tbCount);
        BigDecimal bc = new BigDecimal(tbRange);
        //这里拿到tbRange的值 为getModValue返回的表取模之后的值，不会超过表的数量，如t_user总共16张表
        BigDecimal[] results = bc.divideAndRemainder(new BigDecimal(dbCount));
        //[bc和dbCount的商,bc和dbCount取模]，这里取商值,范围在0到dbCount之间，不包含dbCount 这里就可以取到DB下标
        //eg,4个库，每个库4个user表，共16张user表，那么取模后tbRange范围为0到15，results取商后results[0]范围为0
        //0
        //0
        //0
        //1
        //1
        //1
        //1
        //2
        //2
        //2
        //2
        //3
        //3
        //3
        //3
        //可以看到数据库下标是均匀分布的0-3，和我们的库数量一致
        return (long)results[0].intValue();
    }

    /**
     *
     * @param obj
     * @param dbCount
     * @param tbCount
     * @return
     */
    public static long getTbIndexByMod(Object obj,int dbCount,int tbCount) {
        long tbRange = getModValue(obj, tbCount);
        BigDecimal bc = new BigDecimal(tbRange);
        BigDecimal[] results = bc.divideAndRemainder(new BigDecimal(tbCount/dbCount));
        return (long)results[1].intValue();
    }
}
