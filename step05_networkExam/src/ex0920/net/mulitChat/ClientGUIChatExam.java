package ex0920.net.mulitChat;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUIChatExam extends JFrame {
	JTextArea textArea = new JTextArea();
	JScrollPane jsp = new JScrollPane(textArea);
	JTextField text = new JTextField();
	
	Socket sk;
	BufferedReader br;
	PrintWriter pw;
	
	
	/**
	 * ȭ�鱸��
	 * 
	 */
	
	public ClientGUIChatExam() {
	super("ClientGUIChat!!");
	Container con= super.getContentPane();
	con.add(jsp, "Center");
	con.add(text, "South");
	
	//�ɼǼ���
	textArea.setFocusable(false);
	textArea.setBackground(Color.PINK);
	text.requestFocus();//Ŀ������
	
	
	setSize(400,300);//ũ�� ����
	setLocationRelativeTo(null);//JFrame ���߾ӿ� ��ġ
	setVisible(true);//JFrame�����a
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//xŬ���� ���μ��� ����
	//�������ӽõ�
	this.connection();
	
	//�̺�Ʈ ���: �̺�Ʈ ��ü.addXxxListener(�̺�Ʈ����Ŭ����);
	text.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		System.out.println("����.....");
			//textField�� �б�
			String inputData = text.getText();
			
			//�������� ����
			pw.println(inputData);
			
			//textfield ����� 
			text.setText("");
				}
	});
	
	
	
	}//�����ڳ�
	public void connection() {
		try {
		sk =new Socket("192.168.0.111",8000);
		String name =JOptionPane.showInputDialog("��ȭ���� �Է����ּ���.");
		br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
		pw = new PrintWriter(sk.getOutputStream(),true);
		
		//��ȭ�� �����ϱ�
		pw.println(name);
		
		//������ �������� �����͸� �޾Ƽ� textArea�� �߰��Ѵ�. �͸����� �����
		new Thread() {
			public void run() {
				try {
				String data=null;
				while((data =br.readLine())!=null) {
					textArea.append(data+"\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			};
			
		}.start();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		new ClientGUIChatExam();

	}

}
