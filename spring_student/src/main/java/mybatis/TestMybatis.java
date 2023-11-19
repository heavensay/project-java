package mybatis;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class TestMybatis {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static SqlSession sqlSession;

    @Before
    public void before() {
        try {
            reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 不需要dao，直接查询
     */
    @Test
    public void test1() {
        User dto = (User) sqlSession.selectOne("mybatis.IUserDao.getUserById", 1);
        System.out.println(dto.getName());
        System.out.println(dto.getMoke());
    }

    /**
     * dao层普通查询+更新
     */
    @Test
    public void test2() {

        IUserDao dao = sqlSession.getMapper(IUserDao.class);

        User dto = dao.getUserById(1);
        System.out.println(dto.getName());
        System.out.println(dto.getMoke());

        dto.setRemark("" + System.currentTimeMillis());
        dao.updateUser(dto);

        sqlSession.commit();
    }

    @Test
    public void test3Insert() {
        User dto = new User();
        dto.setName("tom");
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        dao.insertUser(dto);
        sqlSession.commit();
    }

    /**
     * 批量插入
     */
    @Test
    public void test3InsertBatch() {
        Random random = new Random(100);

        User dto1 = new User();
        dto1.setName("christ1");
        dto1.setRemark("" + random.nextInt());

        User dto2 = new User();
        dto2.setName("christ2");
        dto2.setRemark("" + random.nextInt());

        List<User> users = new ArrayList<User>();
        users.add(dto1);
        users.add(dto2);

        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        dao.batchInsertUser(users);
        sqlSession.commit();
    }

    /*
     * 测试Mybatis分页查询
     */
    @Test
    public void test3Page1() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Page page = new Page();
        List<User> list = dao.queryUserByPage(page);
        System.out.println(list.size());
    }

    /*
     * 测试Mybatis分页查询
     */
    @Test
    public void test3Page2() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Page page = new Page();
        List<User> list = dao.query2UserByPage(page, 1);
        System.out.println(list.size());
    }

    /*
     * 测试Mybatis分页查询
     */
    @Test
    public void test3Page3() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Page page = new Page();
        List<User> list = dao.queryUserByPage(page);
        System.out.println(list.size());
    }

    /*
     * 测试Mybatis分页查询
     */
    @Test
    public void test3PageByRowBounds() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Page page = new Page();

        RowBounds rowBound = new RowBounds(1, 1);

        List<User> list = dao.queryUserByPage(page);
        System.out.println(list.size());
    }

    /**
     * foreach可以通过配置文件的拼接sql语句， 观察2个foreach方法的区别
     * 此方法的参数，在mybatis解析的时候，像是从map中取(参照:<foreach collection="ids" index="index" item="id" .....>)
     */
    @Test
    public void test4queryUserByForeach() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Integer[] ids1 = new Integer[]{1, 3};
        Integer[] ids2 = null;
        Integer[] ids3 = new Integer[]{};

        List<User> list1 = dao.queryUserByForeach(ids1);

        List<User> list2 = dao.queryUserByForeach(ids2);

        List<User> list3 = dao.queryUserByForeach(ids3);

        org.junit.Assert.assertNotNull(list1.size());
    }

    @Test
    public void test4queryUserByForeach2() {
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        Integer[] ids1 = new Integer[]{1, 3};

        List<User> list4 = sqlSession.selectList("mybatis.IUserDao.queryUserByForeach2", ids1);

        org.junit.Assert.assertNotNull(list4.size());
    }
}
