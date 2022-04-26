/**
 * Copyright (C), 2020-2022, XDU
 * FileName: UserBasicServiceImpl
 * Author: Dingq
 * Date: 2022/4/25 14:10
 * Description:
 */
package zone.service.impl;

import zone.dao.UserBasicDAO;
import zone.pojo.UserBasic;
import zone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO = null;

    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userFriendList = userBasicDAO.getUserBasicList(userBasic);
        return userFriendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }
}