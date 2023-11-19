package tx.service;

import tx.dao.UserDao;
import tx.entity.DctConstant;
import tx.entity.User;


public class UserService {

    protected UserDao userDao;

    protected DctService dctService;

    public User getUserById(Integer id) {
        User user = userDao.getUserById(id);
        return user;
    }

    public void saveTuser(User user) {
        userDao.saveUser(user);
    }

    public void saveTuser2(User user) {
        userDao.saveUser(user);
        System.out.println("**************开始插入第二个用户**************");
        this.saveTuser3(user);
    }

    private void saveTuser3(User user) {
        userDao.saveUser(user);
    }


    public void saveMix(User user, DctConstant dctConstant) {
        System.out.println("**************insert user **************");
        userDao.saveUser(user);

//		userDao.saveBeThrowException();
        System.out.println("**************insert dctconstant1**************");
        dctService.saveDctConstant(dctConstant);

        System.out.println("**************insert dctconstant2**************");
        dctService.saveDctConstant(dctConstant);
        System.out.println("**************insert over**************");
    }

    public void saveMixHaveRuntimeException(User user, DctConstant dctConstant) {
        System.out.println("**************insert user **************");
        userDao.saveUser(user);
        System.out.println("**************insert saveBeThrowException **************");
        try {
            dctService.saveBeThrowRuntimeException();
        } catch (Exception e) {
            System.out.println("**************catch Exception **************");
        }
        System.out.println("**************insert dctconstant2**************");
        dctService.saveDctConstant(dctConstant);
        System.out.println("**************insert over**************");
    }

    public void saveMixHaveException(User user, DctConstant dctConstant) throws Exception {
        System.out.println("**************insert user **************");
        userDao.saveUser(user);
        System.out.println("**************insert saveBeThrowException **************");
//		try{
        dctService.saveBeThrowException();
//		}catch(Exception e){
//			System.out.println("**************catch Exception **************");	
//		}
        System.out.println("**************insert dctconstant2**************");
        dctService.saveDctConstant(dctConstant);
        System.out.println("**************insert over**************");
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDctService(DctService dctService) {
        this.dctService = dctService;
    }

}
