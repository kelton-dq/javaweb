/**
 * Copyright (C), 2020-2022, XDU
 * FileName: TopicServiceImpl
 * Author: Dingq
 * Date: 2022/4/25 14:42
 * Description:
 */
package zone.service.impl;

import zone.dao.TopicDAO;
import zone.pojo.Reply;
import zone.pojo.Topic;
import zone.pojo.UserBasic;
import zone.service.ReplyService;
import zone.service.TopicService;
import zone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    public Topic getTopic(Integer id){
        Topic topic = topicDAO.getTopic(id);
        UserBasic userBasic = userBasicService.getUserBasicById(topic.getAuthor().getId());
        topic.setAuthor(userBasic);
        return topic;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic =getTopic(id);

        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);

        return topic;
    }

    @Override
    public void delTopic(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);
        if(topic != null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topicId);
        }
    }

    public void addTopic(Integer userId, String title, String content){
        Topic topic = new Topic(title, content);
        UserBasic userBasic = userBasicService.getUserBasicById(userId);
        topic.setAuthor(userBasic);
        topicDAO.addTopic(topic);
    }
}