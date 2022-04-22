package fruit.dao;

import fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取所有库存列表信息
    List<Fruit> getFruitList();

    Fruit getFruitByFid(Integer fid);

    void updateFruit(Fruit fruit);

    void delFruit(Integer fid);

    void addFruit(Fruit fruit);
}
