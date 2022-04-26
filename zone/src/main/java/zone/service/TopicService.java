package zone.service;

import zone.pojo.Topic;
import zone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList(UserBasic userBasic);

    public Topic getTopic(Integer id);

    Topic getTopicById(Integer id);

    void delTopic(Integer topicId);

    public void addTopic(Integer userId, String title, String content);
}
