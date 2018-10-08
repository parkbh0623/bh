package ex0920.net.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 클라이언트 or 서버가 상대측에 
 * 키보드 입력받아 전송하기위한 스레드
 * */
public class SendThread extends Thread {
	Socket sk;
	String name;
    public SendThread(Socket sk , String name) {
    	this.sk=sk;
    	this.name=name;
    }
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
		  PrintWriter pw = new PrintWriter(sk.getOutputStream(),true);
		  while(true) {
		     String sendMessage = sc.nextLine();
		     if(sendMessage.equals("quit"))break;
		     pw.println(name+" : " + sendMessage);
		  }
		}catch (Exception e) {
			System.out.println(name+" 오류 발생 : " + e);
			e.printStackTrace();
		}finally {
			try {
			  if(sk!=null)sk.close();
			}catch (Exception e) {
				System.out.println(name+" 오류 발생 close() : " + e);
				e.printStackTrace();
			}
		}
	}
}











