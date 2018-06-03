package testdatabase.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;

public class MongoDBHelper {

    private static MongoClient mongoClient = null;
    private static MongoDatabase mongoDatabase = null;

    static {
        // 连接到 mongodb 服务
        mongoClient = new MongoClient("127.0.0.1", 27017);
        // 连接到数据库
        mongoDatabase = mongoClient.getDatabase("diy");
    }

    public static MongoDatabase getMongoDatabase(){
        return mongoDatabase;
    }

    public static MongoCollection<Document> getCollection(String collectionName){
        MongoCollection<Document> documents =  mongoDatabase.getCollection(collectionName);
        return documents;
    }

}
