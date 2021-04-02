package com.jiuzhao73.entity.bean;

import com.jiuzhao73.constant.LogQueryParamsState;

public class LogQueryParams {
    //根据传入参数判断状态
    //过于冗杂
    //后续修改
    public static LogQueryParamsState getParamsState(String name, String isAdd, String beginTime, String lastTime) {
        boolean isName = name == null || name.isEmpty();
        boolean isIsAdd = isAdd == null || isAdd.isEmpty() || isAdd.equals("null");
        boolean isTime = beginTime == null || beginTime.isEmpty() || lastTime == null || lastTime.isEmpty();
        if (isName) {
            if (isIsAdd) {
                if (isTime) {
                    return LogQueryParamsState.NO_ONE;
                }
                return LogQueryParamsState.HAVE_TIME;
            }
            if (isTime) {
                return LogQueryParamsState.HAVE_IS_ADD;
            }
            return LogQueryParamsState.NO_NAME;
        }
        if (isIsAdd) {
            if (isTime) {
                return LogQueryParamsState.HAVE_NAME;
            }
            return LogQueryParamsState.NO_IS_ADD;
        }
        if (isTime) {
            return LogQueryParamsState.NO_TIME;
        }
        return LogQueryParamsState.ALL;
    }
}
