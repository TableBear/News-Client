package com.hzx.news.utils;

import java.util.List;

/**
 * @Description: 和list相关的工具方法
 * @Author: TableBear
 * @Date: 2019/4/2 23:05
 * @param:
 * @return:
 * @throws:
 */
public class ListUtils {
    public static boolean isEmpty(List list) {
        if (list == null) {
            return true;
        }
        return list.size() == 0;
    }
}

