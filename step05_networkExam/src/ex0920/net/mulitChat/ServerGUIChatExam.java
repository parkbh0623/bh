package ex0920.net.mulitChat;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerGUIChatExam {
	/**
	 * 클라이언트가 접속하면 클ㄹ라이언트를 스레드로 만들어 자료구조에 저장
	 * 특정 클라이언트가 데이터를 보내오면 그 데이터를 자료구조에 저장된 모든 클라이언
	 * 트에게 전송한다
	 */
	
	ServerSocket server;
	Socket sk;
	List<ClientSkThread>list = new ArrayList<>();

	
	public ServerGUIChatExam(){
		try {
		server = new ServerSocket(8000);
		while(true) {
			System.out.println("client 접속 대기중");
			sk=server.accept();//다른 스레드 오길 기다림
			System.out.println(sk.getInetAddress()+"님이 접속되었습니다.");
		ClientSkThread clientTh = new ClientSkThread();
		list.add(clientTh);
		clientTh.start();
		
		}
		
	}catch (Exception e) {
	e.printStackTrace();
	}
	}
	
	
	public static void main(String[] args) {
	new	ServerGUIChatExam();
	
	
	}/**
	 *자료 구조에 있는 스레드를 반목문을 돌려 printwriter로 데이터를 전송하는 메소드
	 *
	 */
	public void sendMessage(String message) {
		for(ClientSkThread th : list) {
			th.pw.println(message);
		}
	}
	    /**
		 * 
		 * 각각의 클라이언트에 해당하는 Thread
		 * (현재 스레드의 클랄이인트가 보내오는 데이터를 읽어서 
		 * 모든 클라이언트에게 전송한다.)
		 */
	class ClientSkThread extends Thread{
			BufferedReader br;
			PrintWriter pw;
			String name;
			String ip;
			@Override
			public void run(){
				try {
				br= new BufferedReader(new InputStreamReader(sk.getInputStream()))	;
				pw = new PrintWriter(sk.getOutputStream(),true);
				ip=sk.getInetAddress().toString();
				name = br.readLine();//대화명!!!
			
				
				sendMessage("{"+name+"}님 입장하셨습니다.");
				
				//보내오는 데이터를 읽어서 모든 user에게 전송한다
				String data = null;
				while((data=br.readLine())!=null) {
					sendMessage("{"+name+"}"+data);
					}
				
				}catch(Exception e){
					//현재 스레드를 list에서 제거한다
					list.remove(this);
					
					//모든 user에게 알린다.
					sendMessage("{"+name+"}님 퇴장하셨습니다.");
					System.out.println(sk.getInetAddress()+"주소의"+name+"나갔습니다.");
					e.printStackTrace();
				}
			}
		
	}//ClientSkThread끝
	
}//outer클래스끝
