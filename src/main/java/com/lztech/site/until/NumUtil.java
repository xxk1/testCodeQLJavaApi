package com.lztech.site.until;


public class NumUtil {


    /**
     * 检测int数组 数据是否连续
     * TRUE  是，FALSE 否
     */
    private static int two = 2, three = 3, ten = 10, twenty = 20;
    private static float tenf = 10f;

    public static boolean getArr(int[] arr) {
        if (arr.length < three) {
            return false;
        }

        boolean b = false;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] * two != arr[i - 1] + arr[i + 1]) {
                b = false;
                break;
            }
            if (Math.abs(arr[i + 1] - arr[i]) != 1) {
                b = false;
                break;
            }
            if ((arr[i + 1] - arr[i]) != (arr[i] - arr[i - 1])) {
                b = false;
                break;
            }
            b = true;
            continue;
        }
        return b;
    }

    /**
     * 将String数组转换成int数组
     */
    public static int[] stringToInt(String[] arrs) {

        int[] ints = new int[arrs.length];

        for (int i = 0; i < arrs.length; i++) {

            ints[i] = Integer.parseInt(arrs[i]);

        }

        return ints;

    }

    /**
     * 获取int数组最大值和最小值
     * 并返回 最小值 和 最大值
     */
    public static String getMaxAndMin(int[] series) {
        int i, mina, maxa;
        maxa = series[0];
        mina = series[0];
        for (i = 0; i < series.length; i++) {
            if (series[i] > maxa) {
                maxa = series[i];
            } // 判断最大值

            if (series[i] < mina) {
                mina = series[i];
            }   // 判断最小值

        }
        return mina + "-" + maxa;
    }


    /**
     * 获取 int数组  间接性连接
     * 例： 1,3,4,7,8,9,10,11,12
     * 结果：1,3-4,7-12
     */
    public static String getNumConsecutive(int[] noNum) {
        int state = 0;
        String result = "";
        for (int i = 0; i < noNum.length; i++) {
            if (i == noNum.length - 1) {
                state = two;
            }
            if (state == 0) {
                if (noNum[i + 1] == noNum[i] + 1) {
                    result += Integer.toString(noNum[i]);
                    result += "-";
                    state = 1;
                } else {
                    result += Integer.toString(noNum[i]);
                    result += ",";
                }
            } else if (state == 1) {
                if (noNum[i + 1] != noNum[i] + 1) {
                    result += Integer.toString(noNum[i]);
                    result += ",";
                    state = 0;
                }
            } else {
                result += Integer.toString(noNum[i]);
            }
        }
        return result;
    }


    public static String int2chineseNum(int src) {
        if (src >= ten && src < twenty) {
            return "十" + int2chineseNum(src % ten);
        }
        String[] chnNumChar = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] chnUnitChar = {"", "十", "百", "千"};
        StringBuilder chnStr = new StringBuilder();
        StringBuilder strIns = new StringBuilder();
        int unitPos = 0;
        boolean zero = true;
        while (src > 0) {
            int v = src % ten;
            if (v == 0) {
                if (!zero) {
                    zero = true;
                    chnStr.append(chnNumChar[v]).append(chnStr);
                }
            } else {
                zero = false;
                strIns.delete(0, strIns.length());
                strIns.append(chnNumChar[v]);
                strIns.append(chnUnitChar[unitPos]);
                chnStr.insert(0, strIns);
            }
            unitPos++;
            src = (int) Math.floor(src / tenf);
        }
        return chnStr.toString();

    }

}
