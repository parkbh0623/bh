package ex0920.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 클라이언트와 1 : 1 채팅을 위한 SEVER
 * */
public class ServerChatExam {
	ServerSocket server;
	Socket sk;
    public ServerChatExam() {
    	try {
    	  server = new ServerSocket(8000);
    	  System.out.println("클라이언트 접속을 기다립니다...");
    	  sk = server.accept();
    	  System.out.println(sk.getInetAddress()+"님과 채팅 시작합니다.");
    	  
    	  //읽기Thread --받기
    	   new Thread() {
    		   public void run() {
    			   try {
    			    BufferedReader br = new BufferedReader(
    					   new InputStreamReader(sk.getInputStream()));
    			    
    			    String data=null;
    			    while((data = br.readLine())!=null) {
    			    	System.out.println(data);
    			    }
    			    
    			   }catch (Exception e) {
    				  System.out.println("Server Recive Error : " + e);
					 e.printStackTrace();
				  }
    			   
    		   };
    	   }.start();
    	  
    	  //쓰기 Thread -- 보내기
    	  new SendThread(sk, "SERVER가 보낸 내용 ").start();
    	  
    	  
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		new ServerChatExam();

	}

}
