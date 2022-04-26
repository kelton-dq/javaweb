/**
 * Copyright (C), 2020-2022, XDU
 * FileName: UserController
 * Author: Dingq
 * Date: 2022/4/25 14:59
 * Description:
 */
package zone.controller;

import zone.pojo.Topic;
import zone.pojo.UserBasic;
import zone.service.TopicService;
import zone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session){
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);
            //保存当前进入的界面是哪个用户
            session.setAttribute("friend", userBasic);
            return "index";
        }else {
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session){
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currFriend);

        currFriend.setTopicList(topicList);

        session.setAttribute("friend", currFriend);

        return "index";
    }
}