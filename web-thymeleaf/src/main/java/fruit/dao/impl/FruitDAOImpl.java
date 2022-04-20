/**
 * Copyright (C), 2020-2022, XDU
 * FileName: FruitDAOImpl
 * Author: Dingq
 * Date: 2022/4/20 19:30
 * Description:
 */
package fruit.dao.impl;

import fruit.dao.FruitDAO;
import fruit.pojo.Fruit;
import myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}