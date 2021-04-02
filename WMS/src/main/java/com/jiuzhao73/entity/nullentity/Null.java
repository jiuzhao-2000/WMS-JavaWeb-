package com.jiuzhao73.entity.nullentity;

public interface Null {
    static boolean isNull(Object user){
        return user instanceof Null;
    }
}
