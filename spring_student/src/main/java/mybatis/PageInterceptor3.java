package mybatis;

import java.sql.Connection;  
import java.util.Properties;  
  
import org.apache.ibatis.executor.statement.StatementHandler;  
import org.apache.ibatis.logging.Log;  
import org.apache.ibatis.logging.LogFactory;  
import org.apache.ibatis.mapping.BoundSql;  
import org.apache.ibatis.plugin.Interceptor;  
import org.apache.ibatis.plugin.Intercepts;  
import org.apache.ibatis.plugin.Invocation;  
import org.apache.ibatis.plugin.Plugin;  
import org.apache.ibatis.plugin.Signature;  
import org.apache.ibatis.reflection.MetaObject;  
import org.apache.ibatis.session.Configuration;  
import org.apache.ibatis.session.RowBounds;  
  
/** 
 *  
 * @author yuguiyang 
 * @description 然后就是实现mybatis提供的拦截器接口，编写我们自己的分页实现，原理就是拦截底层JDBC操作相关的Statement对象， 
 *              把前端的分页参数如当前记录索引和每页大小通过拦截器注入到sql语句中 
 *              ，即在sql执行之前通过分页参数重新生成分页sql,而具体的分页sql实现是分离到Dialect接口中去。 
 * @time 2013-8-23 
 * @version V1.0 
 */  
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })  
public class PageInterceptor3 implements Interceptor {  
  
    private final static Log log = LogFactory.getLog(PageInterceptor.class);  
    private String databaseType; 
    	
    
    public Object intercept(Invocation invocation) throws Throwable {  
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
        BoundSql boundSql = statementHandler.getBoundSql();  
  
        //元数据  
        MetaObject metaObject = MetaObject.forObject(statementHandler);  
        //分页参数  
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");  
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {  
            //如果没有提供RowBounds的，则不做操作  
        } else {  
            //获取配置文件参数  
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");  
            //读取配置文件中的配置，确认是什么分页实现
            MysqlDialect dialect = null;
            if("mysql".equalsIgnoreCase(databaseType)){
            	dialect = new MysqlDialect();  
            }
            
            String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");  
            metaObject.setValue("delegate.boundSql.sql",  
                    dialect.getMysqlPageSql(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));  
            metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);  
            metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
  
            //输出日志  
            if (log.isDebugEnabled()) {  
                log.debug("生成分页SQL : " + boundSql.getSql());  
            }  
        }  
          
        return invocation.proceed();  
          
    }  
  
    public Object plugin(Object target) {  
        return Plugin.wrap(target, this);  
    }  
  
    /** 
     * 设置注册拦截器时设定的属性 
     */  
    public void setProperties(Properties properties) {  
       this.databaseType = properties.getProperty("databaseType");  
    }  
}  