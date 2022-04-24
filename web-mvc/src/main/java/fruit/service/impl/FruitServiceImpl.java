/**
 * Copyright (C), 2020-2022, XDU
 * FileName: FruitServiceImpl
 * Author: Dingq
 * Date: 2022/4/23 16:16
 * Description: 业务层BO对DAO调用，实现组合功能
 */
package fruit.service.impl;

import fruit.service.FruitService;
import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import myssm.basedao.ConnUtil;

import java.util.List;

public class FruitServiceImpl implements FruitService {

//    private FruitDAO fruitDAO = new FruitDAOImpl();
    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
//        System.out.println("getFruitList -> " + ConnUtil.getConn());
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
//        System.out.println("getPageCount -> " + ConnUtil.getConn());
        return (fruitDAO.getFruitCount(keyword) + 4) / 5;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}