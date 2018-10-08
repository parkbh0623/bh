package ex0920.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExam {
		public ServerExam() {
			try {
				ServerSocket server= new ServerSocket(8000);
				while(true) {
					System.out.println("client������ ��ٸ��ϴ�.");
					Socket sk = server.accept();//Ŭ���̾�Ʈ ���� �����;
					System.out.println(sk.getInetAddress()+"�� ���ӵǾ����ϴ�...");
					
					//Ŭ���̾�Ʈ�� �����´� ������ �б�
					BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
					String clientMessage = br.readLine();
					System.out.println("client������ ����:"+clientMessage);
					
					//Ŭ���̾�Ʈ���� ������ �����ϱ�(������)
					sk.getOutputStream();
					//OutputStreamWriter->Bufferedwirter �ѹ��� ���� PrintWriter
					PrintWriter pw = new PrintWriter(sk.getOutputStream(),true);
					pw.println("�����帳�ϴ�");
					sk.close();
					
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	public static void main(String[] args) {
			new ServerExam();

	}

}
