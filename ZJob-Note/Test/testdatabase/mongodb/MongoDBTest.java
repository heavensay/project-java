package testdatabase.mongodb;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

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

    /**
     * 连接测试
     */
    @Test
    public void connectionReplicationTest(){
        try{
            // 连接到 mongodb 服务

            ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
            List<ServerAddress> seeds = new ArrayList<ServerAddress>();
            seeds.add(serverAddress);


            MongoClientOptions.Builder builder = MongoClientOptions.builder();
            builder.connectionsPerHost(50);
            builder.threadsAllowedToBlockForConnectionMultiplier(50);
            builder.maxWaitTime(1000*60*2);
            builder.readPreference(ReadPreference.secondary());
            builder.connectTimeout(1000*60*1);
//            builder.requiredReplicaSetName("hqmongodb"); //不是集群的话，配置了就连接不上
            MongoClientOptions mco = builder.build();

            MongoCredential cred = MongoCredential
                    .createScramSha1Credential("banana","diy","banana".toCharArray());
            List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
            credentialsList.add(cred);

            MongoClient mongoClient = new MongoClient( seeds,credentialsList,mco);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("diy");
            System.out.println("Connect to database successfully");

            List<String> collectionNames = new ArrayList<>();
            mongoDatabase.listCollectionNames().into(collectionNames);
            collectionNames.stream().forEach( e -> System.out.println("collectionName:"+e));

            System.out.println(mongoDatabase.getName());
            MongoCollection<Document>  documents = mongoDatabase.getCollection("student");
            Document student = documents.find().first();
            System.out.println(student.get("name"));

            Assert.assertNotNull(mongoDatabase);

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    @Test
    public void ttttt() throws Exception{
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("D:/1")));

        Iterator i = prop.entrySet().iterator();
        while(i.hasNext()){
            final Map.Entry entry = (Map.Entry) i.next();
            System.out.println((String) entry.getKey());
            System.out.println((String) entry.getValue());
        }
    }
}
