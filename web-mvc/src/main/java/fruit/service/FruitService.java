package fruit.service;

import fruit.pojo.Fruit;

import java.util.List;

public interface FruitService {

    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword, Integer pageNo);
    //添加库存记录
    void addFruit(Fruit fruit);
    //根据id查看指定库存记录
    Fruit getFruitByFid(Integer fid);
    //删除库存记录
    void delFruit(Integer fid);
    //获取总页数
    Integer getPageCount(String keyword);

    void updateFruit(Fruit fruit);
}
