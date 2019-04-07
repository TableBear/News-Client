package com.hzx.news.listener;

import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/5 23:19
 * @param:
 * @return:
 * @throws:
 */
public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
