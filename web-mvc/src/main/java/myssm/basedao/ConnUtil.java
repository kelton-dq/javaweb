/**
 * Copyright (C), 2020-2022, XDU
 * FileName: ConnUtil
 * Author: Dingq
 * Date: 2022/4/24 16:26
 * Description: 获取数据库连接
 */
package myssm.basedao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection createConn(){
        try {
            InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            //1.加载驱动
            Class.forName(driverClass);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn == null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn == null){
            return;
        }
        if (!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}