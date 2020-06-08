package com.xdd.busserver.service;


import com.xdd.busserver.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    void addUser(User user);

    /**
     * 查询用户名是否已经存在
     * @param username
     * @return
     */
    int isUsernameExist(String username);

    /**
     * 修改密码
     */
    void modifyPassword(User user);

    /**
     * 检验二级密码
     * @param user
     * @return
     */
    int checkKeyword(User user);

    /**
     * 检查帐号和密码是否正确
     * @param user
     * @return
     */
    int checkPassword(User user);

    /**
     * 查询用户
     * @param username
     * @return
     */
    User selectUser(String username);

    /**
     * 修改用户信息
     * @param user
     */
    void modifyInformation(User user);

    /**
     * 添加管理员
     * @param user
     */
    void addAdmin(User user);
}
