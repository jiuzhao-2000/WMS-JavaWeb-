package com.jiuzhao73.entity.bean;

public class Page {
    private int count;//总记录
    private int nowPageNum;//当前页面
    private int maxPageNum;//最大页数
    private static final int PAGING_NUMBER = 5;//每页记录数

    public Page(int noteNum) {
        count = Math.max(noteNum, 0);
        nowPageNum = 1;
        maxPageNum = (count - 1) / PAGING_NUMBER + 1;
    }

    public Page(int noteNum, int pageNum) {
        count = Math.max(noteNum, 0);
        nowPageNum = Math.max(pageNum, 1);
        maxPageNum = (count - 1) / PAGING_NUMBER + 1;
    }

    public Page() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        count = Math.max(count, 0);
        maxPageNum = (count - 1) / PAGING_NUMBER + 1;
    }

    public int getMaxPageNum() {
        return Math.max(0, maxPageNum);
    }

    public int getNowPageNum() {
        return nowPageNum;
    }

    public void setNowPageNum(int nowPageNum) {
        this.nowPageNum = Math.max(1, Math.min(nowPageNum, maxPageNum));
    }

    public int getPrePageNum() {
        return nowPageNum == 1 ? 1 : nowPageNum - 1;
    }

    public int getSufPageNum() {
        return nowPageNum < maxPageNum ? nowPageNum + 1 : maxPageNum;
    }

}