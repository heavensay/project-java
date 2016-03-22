package testhttp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 如果反序列化的JavaBean带有内部类，那么你的内部类需要是静态化的
 * @author banana
 *
 */
public class TestJson {
	
	
	private static List list = new ArrayList();
	private static Bean bean = null;
	
	@Before
	public void before(){
		bean = new Bean();
		bean.setId(5);
		bean.setName("tom");
		bean.setAddr("深圳福田区");
		list.add(bean);
	}
	
	@Test
	public void test1Bean() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(bean);
		System.out.println(jsonstr);
		Assert.assertNotNull(jsonstr);
		
		Bean b1 = mapper.readValue(jsonstr, Bean.class);
		Assert.assertNotNull(b1.getName());
	}
	
	@Test
	public void test2List() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(list);
		Assert.assertNotNull(jsonstr);
//		System.out.println(jsonstr);
		List<Bean> list = (List<Bean>)mapper.readValue(jsonstr, new TypeReference<List<Bean>>() {});
		Assert.assertNotNull(list.get(0).getName());
		
	}
	
	@Test
	public void test3FundBean() throws JsonGenerationException, JsonMappingException, IOException{
		FundBean fb = new FundBean();
		
		FundItem fi = new FundItem();
		fi.setBuy(5.2D);
		fi.setCode("000322");
		fi.setDate("2013-01-01");
		List<FundItem> list = new ArrayList<FundItem>();
		list.add(fi);
		
		fb.setData(list);
		Map map = new HashMap();
		map.put("id", 1);
		map.put("msg", "ok");
		fb.setMap(map);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(fb);
		System.out.println(jsonstr);
		
		Object ob = mapper.readValue(jsonstr,FundBean.class );
		System.out.println(ob);
	}
	
	/**
	 * {"data":[{"code":"000322","date":"2013-01-01","buy":5.2,"name":null,"orgid":null,"net":null,"totalnet":null,"date1":null,"net1":null,"totalnet1":null,"range":null,"rate":null,"mark":null}],"error":{"id":1,"msg":"ok"}}
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void test4Stream() throws JsonGenerationException, JsonMappingException, IOException{
		
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(new File("Test/testhttp/json-fund1.txt"));
		System.out.println(jp.getCurrentName());
		System.out.println(jp.getText());	
		//{{[]}}
		while(jp.nextToken()!=null){
			if(jp.getCurrentToken()==JsonToken.START_OBJECT){
				if("data".equals(jp.getCurrentName())){
					
				}
			}
			
			System.out.println();
			System.out.println(jp.getCurrentName());
			System.out.println(jp.getText());		
//			System.out.println(jp.);
			System.out.println("**********");
		}
	}

	/**
	 * {"data":[{"code":"000322","date":"2013-01-01","buy":5.2,"name":null,
	 * "orgid"
	 * :null,"net":null,"totalnet":null,"date1":null,"net1":null,"totalnet1"
	 * :null,"range":null,"rate":null,"mark":null}],"error":{"id":1,"msg":"ok"}}
	 * 
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void test4Tree() throws JsonGenerationException,
			JsonMappingException, IOException {

		JsonFactory f = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(new File(
				"Test/testhttp/json-fund1.txt"));

		JsonNode data = rootNode.path("data");
		System.out.println(data.getTextValue());

		JsonNode ageNode = rootNode.path("error");
		System.out.println(ageNode.getIntValue());

		Iterator<JsonNode> elements = data.getElements();
		while (elements.hasNext()) {
			JsonNode temp = elements.next();
			System.out.println(temp.getTextValue());
			System.out.println(temp.get("code").getTextValue());
			System.out.println(temp.path("code").getTextValue());
		}

	}
	
	/**
	 * 修改Bean->jsonstr的顶层元素名称
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@Test 
	public void test5JsonRootName() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		//下面这行使@JsonRootName生效
		mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE,true);
//		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE,true);
		
		System.out.println(mapper.writeValueAsString(bean));
	}
	
	@Test
	public void test6Bean2() throws JsonGenerationException, JsonMappingException, IOException{
		Bean2 bean2 = new Bean2();
		bean2.setId(5);
		bean2.setName("tom");
		bean2.setAddr("深圳福田区");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(bean2);
		
		Bean2 ub2 = mapper.readValue(new File("Test/testhttp/json2.txt"), Bean2.class);
		
		System.out.println(jsonstr);
		Assert.assertNotNull(ub2.getName());
	}
	
	@Test
	public void test7Bean3() throws JsonGenerationException, JsonMappingException, IOException{
		Bean3 bean3 = new Bean3();
		bean3.setId(5);
		Map map = new HashMap();
		map.put("key-1", "val-1");
		map.put("key-2", 2);
		bean3.setMap(map);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(bean3);
		
		System.out.println(jsonstr);
	}
	
	/**
	 * null转化为空 null->""
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test
	public void test8Bean3() throws JsonGenerationException, JsonMappingException, IOException{
		Bean bean1 = new Bean();
		bean1.setAddr("abc");
		
		ObjectMapper mapper = new ObjectMapper();
		// 空值处理为空串 null->"";  
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()  
        {  
			@Override
			public void serialize(Object value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");  
			}  
        }); 
		
		String jsonstr = mapper.writeValueAsString(bean1);
		
		System.out.println(jsonstr);
	}
}




