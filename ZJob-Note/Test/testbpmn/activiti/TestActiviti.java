package testbpmn.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestActiviti {

	/**
	 * 1部署流程定义
	 */
	@Test
	public void deployProcessDefinition() {
		// 创建核心引擎对象
		ProcessEngine processEngine = ActivitiUtil.getActivitiProcessEngine();
		Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的service
				.createDeployment()// 创建一个部署对象
				.name("出差流程")// 添加部署的名称
				.addClasspathResource("testbpmn/activiti/BusinessTripProcess.bpmn")// classpath的资源中加载，一次只能加载一个文件
				.addClasspathResource("testbpmn/activiti/BusinessTripProcess.png")// classpath的资源中加载，一次只能加载一个文件
				.deploy();// 完成部署
		System.out.println("部署ID:" + deployment.getId());
		System.out.println("部署名称：" + deployment.getName());
	}

	/**
	 * 2启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		// 创建核心引擎对象
		ProcessEngine processEngine = ActivitiUtil.getActivitiProcessEngine();
		// 流程定义的key
		String processDefinitionKey = "bussinessTripProcess";
		ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的Service
				.startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应hellworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:" + pi.getId());// 流程实例ID 101
		System.out.println("流程定义ID:" + pi.getProcessDefinitionId()); // 流程定义ID
																		// HelloWorld:1:4
	}
	
	/**
     * 3查询当前人的个人任务
     */
    @Test
    public void findMyPersonTask() {
        String assignee = "ljy";
        List<Task> list = ActivitiUtil.getActivitiProcessEngine().getTaskService()// 与正在执行的认为管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                .taskAssignee(assignee)// 指定个人认为查询，指定办理人
                .list();

        if (list != null && list.size() > 0) {
            for (Task task:list) {
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间"+task);
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID:"+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("#################################");
            }
        }
    }	
    
    /**
     * 4完成我的任务
     */
    @Test
    public void completeMyPersonTask(){
        //任务Id
        String taskId="17504";
        ActivitiUtil.getActivitiProcessEngine().getTaskService()//与正在执行的认为管理相关的Service
                .complete(taskId);
        System.out.println("完成任务:任务ID:"+taskId);

    }	

	@Test
	public void test1() {
		// 启动流程
		ProcessInstance pi = ActivitiUtil.getActivitiProcessEngine().getRuntimeService()
				.startProcessInstanceByKey("myProcess");
		System.out.println("process id" + pi.getId());
		System.out.println("process name" + pi.getName());

		// 获取任务
		TaskService taskService = ActivitiUtil.getActivitiProcessEngine().getTaskService();
		List<Task> list = taskService.createTaskQuery().taskAssignee("lili").list();
		System.out.println("任务个数" + list.size());
		if (list != null && list.size() > 0) {
			for (Task t : list) {
				System.out.print(t.getId() + ",");
				System.out.print(t.getName() + ",");
				System.out.print(t.getAssignee() + ",");
				System.out.println(t.getProcessInstanceId());
			}
		}
	}

}
