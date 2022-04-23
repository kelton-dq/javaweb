/**
 * Copyright (C), 2020-2022, XDU
 * FileName: FruitServiceImpl
 * Author: Dingq
 * Date: 2022/4/23 16:16
 * Description: 业务层BO对DAO调用，实现组合功能
 */
package fruit.biz.impl;

import fruit.biz.FruitService;
import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return (fruitDAO.getFruitCount(keyword) + 4) / 5;
    }
}