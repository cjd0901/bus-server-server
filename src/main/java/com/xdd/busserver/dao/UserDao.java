package com.xdd.busserver.dao;

import com.xdd.busserver.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    /**
     * 注册用户
     * @param user
     */
    @Insert("" +
            "insert into user " +
            "(username,password,nickname,gender,phone_num,email,keyword,role_id,realname,idcard) " +
            "values" +
            "(#{username},#{password},#{nickname},#{gender},#{phoneNum},#{email},#{keyword},#{roleId},#{realname},#{idcard})")
    void addUser(User user);

    /**
     * 检测用户名是否已经被注册
     * @param username
     * @return
     */
    @Select("select count(1) from user where username = #{username}")
    int isUsernameExist(String username);

    /**
     * 修改密码
     * @param user
     */
    @Update("update user set password = #{password} where username = #{username}")
    void modifyPassword(User user);

    /**
     * 检验二级密码是否正确
     * @param user
     * @return
     */
    @Select("select count(1) from user where username = #{username} and keyword = #{keyword}")
    int checkKeyword(User user);

    /**
     * 检查帐号密码是否正确
     * @param user
     * @return
     */
    @Select("select count(1) from user where username = #{username} and password = #{password}")
    int checkPassword(User user);

    /**
     * 查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User selectUser(String username);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set " +
            "nickname = #{nickname},gender = #{gender},phone_num = #{phoneNum},email = #{email} " +
            "where username = #{username}")
    void modifyInformation(User user);

    @Update("update user set role_id = #{roleId} where username = #{username}")
    void addAdmin(User user);
}
