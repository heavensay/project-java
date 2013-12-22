package tx.service;

import tx.dao.DctDao;
import tx.entity.DctConstant;

/**
 * 辅助测试事务中运行事务
 * serviceA.method1()->serviceB.method2 
 * @author banana
 *
 */
public class DctService {
	
	protected DctDao dctDao;
	
	public void saveDctConstant(DctConstant constant){
		dctDao.saveDctConstant(constant);
	}
	
	public void saveBeThrowRuntimeException(){
		dctDao.saveBeThrowRuntimeException();
	}
	
	public void saveBeThrowException() throws Exception{
		dctDao.saveBeThrowException();
	}
	
	public void setDctDao(DctDao dctDao) {
		this.dctDao = dctDao;
	}
	
}
