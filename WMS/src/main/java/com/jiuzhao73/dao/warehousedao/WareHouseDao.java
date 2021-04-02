package com.jiuzhao73.dao.warehousedao;

import com.jiuzhao73.entity.bean.Goods;

import java.util.List;

public interface WareHouseDao {

    int getCountByTerms();

    int getCountByTerms(String name);

    int getCountByTerms(int id);

    int getCountByTerms(String name, int id);

    List<Goods> getGoodsPageByPageNum(int pageNum);

    List<Goods> getGoodsPageByPageNum(String name, int pageNum);

    List<Goods> getGoodsPageByPageNum(int id, int pageNum);

    List<Goods> getGoodsPageByPageNum(String name, int id, int pageNum);

    boolean addNumberByName(String name, int num);

    boolean isUniqueName(String name);

    boolean addNewGoodsByGoods(Goods goods);
}