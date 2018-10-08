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
	 * Ŭ���̾�Ʈ�� �����ϸ� Ŭ�����̾�Ʈ�� ������� ����� �ڷᱸ���� ����
	 * Ư�� Ŭ���̾�Ʈ�� �����͸� �������� �� �����͸� �ڷᱸ���� ����� ��� Ŭ���̾�
	 * Ʈ���� �����Ѵ�
	 */
	
	ServerSocket server;
	Socket sk;
	List<ClientSkThread>list = new ArrayList<>();

	
	public ServerGUIChatExam(){
		try {
		server = new ServerSocket(8000);
		while(true) {
			System.out.println("client ���� �����");
			sk=server.accept();//�ٸ� ������ ���� ��ٸ�
			System.out.println(sk.getInetAddress()+"���� ���ӵǾ����ϴ�.");
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
	 *�ڷ� ������ �ִ� �����带 �ݸ��� ���� printwriter�� �����͸� �����ϴ� �޼ҵ�
	 *
	 */
	public void sendMessage(String message) {
		for(ClientSkThread th : list) {
			th.pw.println(message);
		}
	}
	    /**
		 * 
		 * ������ Ŭ���̾�Ʈ�� �ش��ϴ� Thread
		 * (���� �������� Ŭ������Ʈ�� �������� �����͸� �о 
		 * ��� Ŭ���̾�Ʈ���� �����Ѵ�.)
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
				name = br.readLine();//��ȭ��!!!
			
				
				sendMessage("{"+name+"}�� �����ϼ̽��ϴ�.");
				
				//�������� �����͸� �о ��� user���� �����Ѵ�
				String data = null;
				while((data=br.readLine())!=null) {
					sendMessage("{"+name+"}"+data);
					}
				
				}catch(Exception e){
					//���� �����带 list���� �����Ѵ�
					list.remove(this);
					
					//��� user���� �˸���.
					sendMessage("{"+name+"}�� �����ϼ̽��ϴ�.");
					System.out.println(sk.getInetAddress()+"�ּ���"+name+"�������ϴ�.");
					e.printStackTrace();
				}
			}
		
	}//ClientSkThread��
	
}//outerŬ������
