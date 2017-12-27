package testtesttool.jmockit;

public class OrderService {

	
	public String getOrder(Integer orderId){
		OrderDaoImpl dao = new OrderDaoImpl();
		return dao.getOrder(orderId);
	}
	
	
	public String getOrder2(Integer orderId){
		OrderDaoImpl dao = new OrderDaoImpl(){
			@Override
			public String getOrder(Integer orderId) {
				// TODO Auto-generated method stub
				return "food order"+orderId;
			}
		};
		return dao.getOrder(orderId);
	}
	
}
