package testdatabase.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

public class MongoDBTest {

    /**
     * 连接测试
     */
    @Test
    public void connectionTest(){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("diy");
            System.out.println("Connect to database successfully");
            Assert.assertNotNull(mongoDatabase);

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * 简单的获取数据
     */
    @Test
    public void simpleGetDocumentTest(){
        MongoCollection<Document> students = MongoDBHelper.getCollection("student");
        FindIterable findIterable = students.find();
        MongoCursor iterator = findIterable.iterator();
        while (iterator.hasNext()){
            Document document = (Document) iterator.next();
            System.out.println(document);
            System.out.println("id:"+document.get("id"));
            System.out.println("name:"+document.get("name"));
        }
    }

    /**
     * mongodb-java-drive.jar客户端内部支持直接转换。
     */
    @Test
    public void document2JavabeanFailTest(){
        MongoCollection<Document> students = MongoDBHelper.getCollection("student");

        FindIterable<Student> findIterable = students.find(Student.class);
        MongoCursor<Student> iterator = findIterable.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    /**
     * document转换成javabean
     * @throws Exception
     */
    @Test
    public void document2Javabean() throws Exception{
        MongoCollection<Document> students = MongoDBHelper.getCollection("student");
        FindIterable findIterable = students.find();
        MongoCursor iterator = findIterable.iterator();
        while (iterator.hasNext()){
            Document document = (Document) iterator.next();
            Student student = new Student();
            student = MongoBeanUtil.dbObject2Bean(document,student);
            System.out.println(document);
            System.out.println("id:"+student.getId());
            System.out.println("name:"+student.getName());
        }

    }

}
