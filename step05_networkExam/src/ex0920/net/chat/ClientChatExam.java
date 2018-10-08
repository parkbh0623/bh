package ex0920.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * ������ 1 : 1 ä���� ���� Client
 * */
public class ClientChatExam {
	Socket sk ;
    public ClientChatExam() {
    	try {
    	   sk = new Socket("127.0.0.1",8000);
    	   
    	   //�б⾲����
    	   new ClientReciveThread().start();
    	   
    	   //������ ������
    	   new SendThread(sk, "Client�� ���� ���� ").start();
    	   
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		new ClientChatExam();

	}
	
	/**
	 * ������ �������� ������ �б� ��� ������
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
	
}//Ŭ���� End








