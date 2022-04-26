package zone.dao.impl;


import myssm.basedao.BaseDAO;
import zone.dao.TopicDAO;
import zone.pojo.Topic;
import zone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ? " , userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        super.executeUpdate("insert into t_topic (title, content, topicDate, author) values (?,?,?,?)",topic.getTitle(),topic.getContent(),topic.getTopicDate(),topic.getAuthor().getId());
    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public void delTopic(Integer topicId) {
        super.executeUpdate("delete from t_topic where id = ?",topicId);
    }

    @Override
    public Topic getTopic(Integer id) {
        return super.load("select * from t_topic where id = ?", id);
    }
}
