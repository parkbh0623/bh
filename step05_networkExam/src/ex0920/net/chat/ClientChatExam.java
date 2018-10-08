package ex0920.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * 서버와 1 : 1 채팅을 위한 Client
 * */
public class ClientChatExam {
	Socket sk ;
    public ClientChatExam() {
    	try {
    	   sk = new Socket("127.0.0.1",8000);
    	   
    	   //읽기쓰레드
    	   new ClientReciveThread().start();
    	   
    	   //보내기 스레드
    	   new SendThread(sk, "Client가 보낸 내용 ").start();
    	   
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		new ClientChatExam();

	}
	
	/**
	 * 서버가 보내오는 데이터 읽기 기능 스레드
	 * 
	 * */
     class ClientReciveThread extends Thread{
    	 @Override
    	public void run() {
    		 try {
    	       BufferedReader br = new BufferedReader(
    			   new InputStreamReader(sk.getInputStream()));
    	       String data=null;
			    while((data = br.readLine())!=null) {
    	    	   System.out.println(data);
    	       }
    	       
    		 }catch (Exception e) {
    			 System.out.println("ClientRecive  Error : " + e);
				e.printStackTrace();
			}finally {
				try {
				  if(sk!=null)sk.close();
				}catch (Exception e) {
					System.out.println("ClientRecive  Error close : " + e);
					e.printStackTrace();
				}
			}
    	   
    	}
     }
	
}//클래스 End








