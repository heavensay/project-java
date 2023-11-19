package tx;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class TestTx {

    /**
     * 不适用spring自带的事务管理
     */
    @Test
    public void test1() {
        DataSource dataSource = DataSourceManager.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update("INSERT INTO TUSER(name,password) "
                + " values('ttt', '20131212')");
        jdbcTemplate.update("INSERT INTO TUSER(name,password) "
                + " values('yyy', '20131212')");

    }

    /**
     * 进行初级的事务管理
     */
    @Test
    public void test2() {
        DataSource dataSource = DataSourceManager.getDataSource();
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        try {
            jdbcTemplate.update("INSERT INTO TUSER(name,password) " +
                    " values('ttt', '20131212')");
            jdbcTemplate.update("INSERT INTO TUSER(name,password) " +
                    " values('yyy', '20131212')");
        } catch (DataAccessException ex) {

            txManager.rollback(status); // 也可以執行status.setRollbackOnly();

            throw ex;
        }
        txManager.commit(status);
    }

    /**
     * 进行简单事务管理
     * txManager.rollback的时候，事务已经提交
     */
    @Test
    public void test3() {
        DataSource dataSource = DataSourceManager.getDataSource();
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = txManager.getTransaction(def);

        try {
            jdbcTemplate.update("INSERT INTO TUSER(id,name,password) " +
                    " values(1,'ttt', '20131212')");

        } catch (DataAccessException ex) {
            txManager.rollback(status); // 也可以執行status.setRollbackOnly();

            jdbcTemplate.update("INSERT INTO TUSER(name,password) " +
                    " values('yyy', '20131212')");
//			throw ex;
        }
        txManager.commit(status);
    }

    /**
     * 进行简单事务管理
     * txManager.rollback的时候，事务已经提交
     */
    @Test
    public void test4() {
//		final String sql  = "INSERT INTO TUSER(id,name,password) " +
//		" values(1,'ttt', '20131212')";
        final String sql = "INSERT INTO TUSER(id,name,password) " +
                " values(1,'ttt', '20131212')";

        final String sql2 = "INSERT INTO TUSER(name,password) " +
                " values('ttt', '20131212')";

        DataSource dataSource = DataSourceManager.getDataSource();
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final JdbcTemplate jdbcTemplate2 = new JdbcTemplate(DataSourceManager.getDataSource2());

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        transactionTemplate
                .setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcTemplate.update(sql2);
                    jdbcTemplate.update(sql);
                } catch (Exception e) { // 可使用具体业务异常代替
//					status.setRollbackOnly();
                }
//				jdbcTemplate.update(sql2);
//				jdbcTemplate2.update(sql2);
            }
        });
    }

    /**
     * 进行简单事务管理
     * txManager.rollback的时候，事务已经提交
     */
    @Test
    public void test5() {
        final String sql = "INSERT INTO TUSER(id,name,password) " +
                " values(1,'ttt', '20131212')";

        final String sql2 = "INSERT INTO TUSER(name,password) " +
                " values('ttt', '20131212')";

        DataSource dataSource = DataSourceManager.getDataSource();
        DataSource dataSource2 = DataSourceManager.getDataSource();


        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        PlatformTransactionManager txManager2 = new DataSourceTransactionManager(dataSource2);

        final TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        final TransactionTemplate transactionTemplate2 = new TransactionTemplate(txManager2);

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource2);


        transactionTemplate
                .setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate2
                .setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcTemplate.update(sql);
                } catch (Exception e) { // 可使用具体业务异常代替
                    status.setRollbackOnly();
                }

                transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        try {
                            jdbcTemplate2.update(sql2);
                        } catch (Exception e) { // 可使用具体业务异常代替
                            status.setRollbackOnly();
                        }
                    }
                });


            }
        });
    }

}
