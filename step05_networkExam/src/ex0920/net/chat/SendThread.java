package ex0920.net.chat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Ŭ���̾�Ʈ or ������ ������� 
 * Ű���� �Է¹޾� �����ϱ����� ������
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
			System.out.println(name+" ���� �߻� : " + e);
			e.printStackTrace();
		}finally {
			try {
			  if(sk!=null)sk.close();
			}catch (Exception e) {
				System.out.println(name+" ���� �߻� close() : " + e);
				e.printStackTrace();
			}
		}
	}
}











