package ex0920.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Ŭ���̾�Ʈ�� 1 : 1 ä���� ���� SEVER
 * */
public class ServerChatExam {
	ServerSocket server;
	Socket sk;
    public ServerChatExam() {
    	try {
    	  server = new ServerSocket(8000);
    	  System.out.println("Ŭ���̾�Ʈ ������ ��ٸ��ϴ�...");
    	  sk = server.accept();
    	  System.out.println(sk.getInetAddress()+"�԰� ä�� �����մϴ�.");
    	  
    	  //�б�Thread --�ޱ�
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
    	  
    	  //���� Thread -- ������
    	  new SendThread(sk, "SERVER�� ���� ���� ").start();
    	  
    	  
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		new ServerChatExam();

	}

}
