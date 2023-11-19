package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface IUserDao {

    public User getUserById(@Param("id") Integer id);

    public void updateUser(User user);

    public void insertUser(User user);

    public void batchInsertUser(@Param("users") List<User> users);

    public List<User> queryUserByPage(Page page);

    public List<User> query2UserByPage(@Param("page") Page page, @Param("id") Integer id);

    public List<User> queryUserByRowRounds(RowBounds rowBounds);

    public List<User> queryUserByForeach(@Param("ids") Integer[] ids);
}
