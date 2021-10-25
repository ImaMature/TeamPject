package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Member;
import java.util.Properties;



public class MemberController {
	
	public static ArrayList<Member> memberlist = new ArrayList<>();
	
	// ȸ������
	public static boolean signup(Member member) {
		
		if(member.getId().length() < 4) {
			System.out.println("[�˸�] ���̵�� 4���� �̻� �����մϴ�.");
			return false;
		}
		
		if(member.getName().length() < 2) {
			System.out.println("[�˸�] �̸��� 2���� �̻� �����մϴ�.");
			return false;
		}
		
		if(member.getPw().length() < 4) {
			System.out.println("[�˸�] �н������ 4���� �̻� �����մϴ�.");
		}
		
		if(!member.getEmail().contains("@")) {
			System.out.println("[�˸�] Email�� @�� �ݵ�� ���Խ����ּ���.");
		}
		
		for(Member temp : memberlist) {
			// temp.getid()�� temp�� member�� ���־��µ� �̷��� �Ǹ� ó�� ȸ�����Ը� �����ϰ� �� ���ķδ� � ���̵� �Է��ص� �ߺ�üũ���� �ɸ�
			if (temp.getId().equals(member.getId())){
				System.out.println("[�˸�] �ߺ��� ���̵� �Դϴ�.");
				return false;
			}
		}
		//����Ʈ�� ����
		memberlist.add(member);
		System.out.println("����Ʈ ����");
		return true;
	}//sign up ��
	
		//���� ó��?
		
		//�̸��� ����
	public static boolean signup() {
		
		return true;
	}
	
	// �α���
	public static boolean login(String id, String pw) {
		for(Member temp : memberlist) {
			if(temp.getId().equals(id) && temp.getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	// ���̵� ã��
	public static boolean forgotId(String name, String email) {
		for(Member temp : memberlist) {
			if(temp.getName().equals(name) && temp.getEmail().equals(email)) {
				System.out.println(temp.getId());
				return true;
			}
		}
		return false;
	}
	
	// ��й�ȣ ã��
	public static boolean forgotPd(String id, String email) {
		for(Member temp : memberlist) {
			if(temp.getId().equals(id) && temp.getEmail().equals(email)) {
				System.out.println(temp.getPw());
				return true;
			}
		}
		return false;
	}
	
	// ȸ������
	public static void info() {
		System.out.println("ȸ������");
		System.out.println("ID : ");
		System.out.println("NAME : ");
		System.out.println("E-mail : ");
		System.out.println("���� �ð� : ");
		// ������� ���
//		System.out.println("�� ��� �ݾ� : ");
//		System.out.println("�� ��� �ð� : ");
	}
	
	// ȸ�� ���� ����
	public static boolean update() {
		return true;
	}
	
	// ȸ��Ż��
	public static void fuck() {
		
	}
	
//	// 8. �������� �޼ҵ� 
//	public static void sendEmail( String tomail, int type, String contents) {
//								// �޴»�� �̸���  // type : ���̵� ã��(1), ��й�ȣ ã��(2), ���Ը���(3) 
//								// contents : ���Ͽ� ���� ���� 
//		
//		
//		String fromemail = "�̸���";
//		String frompassword = "��й�ȣ";
//		
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", "smtp.naver.com");  // �߰� ��� ���� ���� ���̹� �ڵ� �з�
//		properties.put("mail.smtp.port", 587); 
//		properties.put("mail.smtp.auth", true); 
//		
//		// 1. ����
//		Session session = Session.getDefaultInstance(properties, new Authenticator() {
//		
//			
//			@Override // �н����� ���� �޼ҵ� 
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//													// �н����� ���� 
//				return new javax.mail.PasswordAuthentication(fromemail, frompassword);
//															// ������ �̸��� , ������ �н����� 
//			}
//			
//		}); // ���� ��
//		
//		try {
//			// 2. ���� ������ 
//				// 1. ������ ����� ��������
//			MimeMessage message = new MimeMessage(session);
//				// 2. ������ ����� ���� 
//			message.setFrom(new InternetAddress(fromemail));
//				// 3. �޴� ����� �����ּ� ����
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
//
//			
//			// * type ����
//			if(type == 1) {
//				
//				// 4. ���� ����
//				message.setSubject("EZEN PC�濡 ���̵� ã�� �����Դϴ�.");
//				// 5. ���� ����
//				message.setText("ȸ������ ���̵� : "+ contents);
//			}
//			// type == 2 ��й�ȣ ã��
//			if(type == 2) {
//				message.setSubject("EZEN PC�濡 ��й�ȣ ã�� �����Դϴ�.");
//				message.setText("ȸ������ ��й�ȣ : "+ contents);
//			}
//			// type == 3 ���� ����
//			if(type == 3) {
//				message.setSubject("javaconsole.");
//				message.setText("EZEN PC�� ������ ȯ���մϴ�.");
//			}
//			// 6. ���� ���� 
//			Transport.send(message);
//				
//		}
//		catch (MessagingException e) {
//			e.printStackTrace();
//			System.out.println(" [�˸�] : ���� ���� ���� [�����ڿ��� ����]");
//		} 
//		
//	}
}
