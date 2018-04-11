package testbpmn.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class ActivitiUtil {
	
	public static void main(String args[]){

		getActivitiProcessEngine();
	} 

	/**
	 * 获取activiti引擎；期间会初始化需要用到的23张表
	 */
	public static ProcessEngine getActivitiProcessEngine(){
		// 引擎配置
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
		        .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?serverTimezone=GMT%2b8").setJdbcUsername("root")
		        .setJdbcPassword("123456")
		        .setJdbcDriver("com.mysql.jdbc.Driver")
		        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 获取流程引擎对象
		ProcessEngine processEngine = cfg.buildProcessEngine();
		return processEngine;
	}
	
}
