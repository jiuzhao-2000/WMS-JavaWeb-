package com.jiuzhao73.service;

import com.jiuzhao73.dao.warehousedao.WareHouseDao;
import com.jiuzhao73.dao.warehousedao.WareHouseHelper;
import com.jiuzhao73.entity.bean.Goods;

import java.util.List;

public class GoodsService {
    private final WareHouseDao goodsDao;

    public GoodsService() {
        goodsDao = new WareHouseHelper();
    }

    public int getCountByTerms() {
        return goodsDao.getCountByTerms();
    }

    public int getCountByTerms(String name) {
        return goodsDao.getCountByTerms(name);
    }

    public int getCountByTerms(String name, int id) {
        return goodsDao.getCountByTerms(name, id);
    }

    public int getCountByTerms(int id) {
        return goodsDao.getCountByTerms(id);
    }

    public List<Goods> getGoodsPageByPageNum(int pageNum) {
        return goodsDao.getGoodsPageByPageNum(pageNum);
    }

    public List<Goods> getGoodsPageByPageNum(String name, int pageNum) {
        return goodsDao.getGoodsPageByPageNum(name, pageNum);
    }

    public List<Goods> getGoodsPageByPageNum(int id, int pageNum) {
        return goodsDao.getGoodsPageByPageNum(id, pageNum);
    }

    public List<Goods> getGoodsPageByPageNum(String name, int id, int pageNum) {
        return goodsDao.getGoodsPageByPageNum(name, id, pageNum);
    }

    public boolean addNumberByName(String name, int num) {
        return goodsDao.addNumberByName(name, num);
    }

    public boolean isUniqueName(String name) {
        return goodsDao.isUniqueName(name);
    }

    public boolean addNewGoodsByGoods(Goods goods) {
        return goodsDao.addNewGoodsByGoods(goods);
    }
}
