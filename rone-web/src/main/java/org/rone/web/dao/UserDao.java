package org.rone.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.rone.web.model.entity.User;

import java.util.List;
import java.util.Map;

/**
 * mybatis
 * 使用步骤
 *  ●. pom.xml
 *     <dependency>
 *         <groupId>org.mybatis.spring.boot</groupId>
 *         <artifactId>mybatis-spring-boot-starter</artifactId>
 *     </dependency>
 *  ●. application.yml
 *     # mybatis的相关配置
 *     mybatis:
 *       # mapper配置文件，使用xml形式的映射语句的情况下该配置必要
 *       mapper-locations: classpath:mapper/*.xml
 *       # 指定映射的包名
 *       type-aliases-package: org.rone.web.model.entity
 *       configuration:
 *         # 开启驼峰命名
 *         map-underscore-to-camel-case: true
 *  ●. 注解的形式，步骤2中可不配置mapper-locations
 *      {@link UserDao#getUserByNo(String)}
 *  ●. 配置文件的形式，步骤2中可不配置type-aliases-package
 *     配置文件详见：UserDao.xml
 *      {@link UserDao#queryById(String)}
 *
 * 此处为：注解映射和xml映射混合使用
 * 其中xml映射形式中具体的sql详见resources下/mapper/UserDao.xml
 *
 * @author rone
 */
@Mapper
public interface UserDao {

    /**
     * 返回实体类的示例
     * 若sql的结果需要驼峰转换才能映射到POJO上时，需要在 application配置中谈价如下配置
     *  mybatis:
     *      configuration:
     *          # 开启驼峰命名
     *          map-underscore-to-camel-case: true
     * @param userNo
     * @return
     */
    @Select("select user_no,user_name,user_explain from web_user where user_no = #{userNo}")
    User getUserByNo(@Param("userNo") String userNo);

    /**
     * 返回集合示例
     * @param userName
     * @return
     */
    @Select("select user_no,user_name,user_explain from web_user where user_name like #{userName}")
    List<User> getUserByUserName(@Param("userName") String userName);

    /**
     * 返回map类型数据
     * @param userNo
     * @return
     */
    @Select("select user_no,user_name from web_user where user_no = #{userNo}")
    Map getPartUserDataByNo(@Param("userNo") String userNo);

    /**
     * 操作类型语句示例
     * @param userNo
     * @param userName
     * @return
     */
    @Update("update web_user set user_name=#{userName} where user_no = #{userNo}")
    int updateUserNameByNo(@Param("userNo") String userNo, @Param("userName") String userName);

    /**
     * 通过ID查询单条数据
     *
     * @param userNo 主键
     * @return 实例对象
     */
    User queryById(String userNo);

    /**
     * 查询指定行数据
     * ${}的使用示例
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @param order 排序规则
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit, String order);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userNo 主键
     * @return 影响行数
     */
    int deleteById(String userNo);

    /**
     * 根据id集合获取姓名集合
     * 当xml文件的sql中动态的使用参数时需要通过 @Param 注解来表明参数的变量名
     *  @Param("idList") 中表明的变量名为最终在xml文件中的可用名，可与形参名不一致
     * @param idList
     * @return
     */
    List<String> getNamesByIds(@Param("idList") List<String> idList);

}