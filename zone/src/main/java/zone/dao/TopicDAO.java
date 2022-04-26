package zone.dao;


import zone.pojo.Topic;
import zone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    //获取指定用户的日志列表
    public List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    public void addTopic(Topic topic);
    //删除日志
    void delTopic(Topic topic);

    void delTopic(Integer topicId);

    //获取特定日志信息
    Topic getTopic(Integer id);
}
