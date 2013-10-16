package testio;

import java.io.ByteArrayOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.Socket;

/**
 * @author Administrator
 * @since 2011-03-31
 * the client to simulate  file upload of HTTP
 */
public class HttpClient {

    private String boundary ="----WebKitFormBoundaryApRdKZN02No4KxaG";

       private String contentType = "multipart/form-data; boundary="+boundary;

       private static final byte CR = (byte)'\r';
       private static final byte LF = (byte)'\n';
       
       private static final byte[] CRLF = new byte[]{CR,LF};

       private Socket socket;

       private String host;

       private int port;
       

       public static void main(String[] args) {
              try{
              HttpClient client = new HttpClient("localhost",8080);
                     //upload file array
                     File[] files = new File[1];
                     for(int i=0;i<files.length;i++){
                            files[i] = new File("C:\\Documents and Settings\\Administrator\\桌面\\aaa.txt");
                     }
                     client.uploadFile(files);
              }catch(Exception e){

                     e.printStackTrace();

              }
              System.out.println("over ");

       }

       public HttpClient(String host,int port){
              this.host = host;
              this.port = port;

       }

       private void openServer() throws Exception {
              socket = new Socket(host,port);
       }

       private void closeServer() throws Exception {
              if(socket!=null){
                     socket.close();
              }
       }

       private void addHead(long contentLength,OutputStream out) throws
                                                        IOException {
              //request line,end withd CRLF
              write(out,"POST http://127.0.0.1:8080/WebContent/fileupload/doUpload.action HTTP/1.1");
           //   write(out,"Referer: http://127.0.0.1:8080/myupload/index.jsp");
              
         //     write(out,"Request Method: POST");
              out.write(CRLF);
              write(out,"User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.204 Safari/534.16");
              out.write(CRLF);
              write(out,"Host: 127.0.0.1:8080");
              out.write(CRLF);
              write(out,"Origin: http://127.0.0.1:8080");
              out.write(CRLF);
              write(out,"Accept: application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"); 
              out.write(CRLF);
              write(out,"Accept-Encoding: gzip,deflate,sdch");
              out.write(CRLF);
              write(out,"Accept-Charset: GBK,utf-8;q=0.7,*;q=0.3");
              out.write(CRLF);
              write(out,"Referer: http://127.0.0.1:8080/WebContent/fileupload/doUpload.action");
              out.write(CRLF);          
              //entity head fields
              write(out,"Content-Type: "+contentType);
              out.write(CRLF);
              write(out,"Cookie: JSESSIONID=4B0790DCD339F777F3A7DF867BDEBAA1");
              out.write(CRLF);     
              write(out,"Content-Length: "+343/*contentLength*/);
              out.write(CRLF);
              write(out,"Connection: "+"Keep-alive");
              out.write(CRLF);
              out.write(CRLF);
       }

       private void addBody(File file,OutputStream out) throws IOException {
              //write boundary
    	      write(out,"--");
              write(out,boundary);
              out.write(CRLF);
              //write file info
              String disposition = "Content-Disposition:"
                                   +" form-data;"
                                   +" name=\"upload\";"
                                   +" filename=\""
                                   +file.getAbsolutePath()+"\"";
              write(out,disposition);
              out.write(CRLF);
              //write file content type info
              write(out,"Content-Type: text/plain");
              out.write(CRLF);
              //write SP(empty line)
              out.write(CRLF);
              //write file content
              
              InputStream is = new FileInputStream(file);
              byte[] b = new byte[1024];
              int count = is.read(b);
              while(count!=-1){
                     out.write(b,0,count);
                     byte[] realbyte = new byte[count];
                     for(int i = 0;i<count;i++){
                    	 realbyte[i] = b[i];
                     }
             //        System.out.println(new String(realbyte));                     
                     count = is.read(b);
              }
              is.close();
              //write crlf
              out.write(CRLF);
       }

       public void uploadFile(File[] files) throws Exception {
              //open server
              openServer();
              //open stream
              OutputStream out = socket.getOutputStream();
             
              ByteArrayOutputStream bos = new ByteArrayOutputStream();
              addHead(files[0].length(),bos);
              for(int i=0;i<files.length;i++){
                     addBody(files[i],bos);
              }
              write(bos,"--"+boundary+"--");
              bos.write(CRLF);
              bos.writeTo(out);
              bos.close();
              out.flush();
              //close server
//              InputStream in = socket.getInputStream();
//              byte[] b = new byte[1024*4];
//              int amount = in.read(b);
//              while(amount>-1){
//            	  System.arraycopy(b, 0, b, 0, amount);
//            	  System.out.print(new String(b));
//            	  in.read(b);
//              }
              
              closeServer();
       }

      

       private void write(OutputStream out,String msg) throws IOException

       {
              out.write(msg.getBytes());
       }

}