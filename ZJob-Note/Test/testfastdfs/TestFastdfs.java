//package testfastdfs;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.ClientGlobal;
//import org.csource.fastdfs.StorageClient1;
//import org.csource.fastdfs.StorageServer;
//import org.csource.fastdfs.TrackerClient;
//import org.csource.fastdfs.TrackerServer;
//import org.junit.Test;
//
//public class TestFastdfs {
//
//	@Test
//	public void testUploadFile() throws IOException, MyException{
//		uploadFile(new File("E:/store/application.properties"));
//	}
//
//
//	private static void uploadFile(File file) throws IOException, MyException {
//		ClientGlobal.init("bin/testfastdfs/fdfs_client.properties");
//
//		InputStream inStream = new FileInputStream(file);
//		if(!file.exists()){
//			System.out.println("文件不存在："+file.getAbsolutePath());
//			return;
//		}
//
//	    byte[] fileBuff = new byte[(int)file.length()];//测试环境小文件，直接long->int
//	    inStream.read(fileBuff);
//
//	    String fileId = "";
//	    String fileExtName = "";
//        fileExtName = file.getName()+".extname";
//
//	    //建立连接
//	    TrackerClient tracker = new TrackerClient();
//	    TrackerServer trackerServer = tracker.getConnection();
//	    StorageServer storageServer = null;
//	    StorageClient1 client = new StorageClient1(trackerServer, storageServer);
//
//
//	    //设置元信息
//	    NameValuePair[] metaList = new NameValuePair[3];
//	    metaList[0] = new NameValuePair("fileName", file.getName());
//	    metaList[1] = new NameValuePair("fileExtName", fileExtName);
//	    metaList[2] = new NameValuePair("fileLength", String.valueOf(file.length()));
//
//
//	    //上传文件
//	    try {
//	        fileId = client.upload_file1(fileBuff, fileExtName, metaList);
//	    } catch (Exception e) {
//	        System.out.println("Upload file \"" + file.getName() + "\"fails");
//	    }
//	    trackerServer.close();
//	    System.out.println("上传成功，file："+file.getName()+" fileid="+fileId);
//	    return;
//	}
//}
