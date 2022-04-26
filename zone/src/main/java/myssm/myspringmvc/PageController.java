/**
 * Copyright (C), 2020-2022, XDU
 * FileName: PageController
 * Author: Dingq
 * Date: 2022/4/25 21:57
 * Description:
 */
package myssm.myspringmvc;

public class PageController {

    public String refresh(String page){
        return page;//转到Dispatcher，执行thymeleaf渲染
    }
}