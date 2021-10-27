package Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import Model.Member;
import View.Kiosk;

import java.util.Properties;

import Database.DB;



public class MemberController {
	
	public static ArrayList<Member> memberlist = new ArrayList<>();
	
	// 시간관리
	public static void timeControll() {
		int saveTime = memberlist.get(KioskController.idx_1).getTime();
		System.out.println("남은 시간");
		System.out.printf("%02d : %02d : %02d", (saveTime/3600), (saveTime/60)%60, (saveTime%60));
		System.out.println();
	}
	
	// 회원가입
	public static boolean signup(Member member) {
		
		if(member.getId().length() < 4) {
			System.out.println("[알림] 아이디는 4글자 이상만 가능합니다.");
			return false;
		}
		
		if(member.getName().length() < 2) {
			System.out.println("[알림] 이름은 2글자 이상만 가능합니다.");
			return false;
		}
		
		if(member.getPw().length() < 4) {
			System.out.println("[알림] 패스워드는 4글자 이상만 가능합니다.");
		}
		
		if(!member.getEmail().contains("@")) {
			System.out.println("[알림] Email에 @를 반드시 포함시켜주세요.");
		}
		
		for(Member temp : memberlist) {
			// temp.getid()에 temp가 member로 돼있었는데 이렇게 되면 처음 회원가입만 가능하고 그 이후로는 어떤 아이디를 입력해도 중복체크에서 걸림
			if (temp.getId().equals(member.getId())){
				System.out.println("[알림] 중복된 아이디 입니다.");
				return false;
			}
		}
		//리스트에 저장
		memberlist.add(member);
		System.out.println("리스트 저장");
		return true;
	}//sign up 끝
	
		//파일 처리?
		
		//이메일 전송
	public static boolean signup() {
		
		return true;
	}
	
	// 로그인
	public static boolean login(String id, String pw) {
		for(Member temp : memberlist) {
			if(temp.getId().equals(id) && temp.getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	// 아이디 찾기
	public static boolean forgotId(String name, String email) {
		for(Member temp : memberlist) {
			if(temp.getName().equals(name) && temp.getEmail().equals(email)) {
				System.out.println(temp.getId());
				return true;
			}
		}
		return false;
	}
	
	// 비밀번호 찾기
	public static boolean forgotPd(String id, String email) {
		for(Member temp : memberlist) {
			if(temp.getId().equals(id) && temp.getEmail().equals(email)) {
				System.out.println(temp.getPw());
				return true;
			}
		}
		return false;
	}
	
	// 회원정보
	public static void info() {
		System.out.println("아이디 리스트");
		for(int i = 0; i<memberlist.size(); i++) {
			
			System.out.println(memberlist.get(i).getId());
		}
		//받아올 객체 (id)
		System.out.println("회원정보의 아이디를 입력하세요"); String id = Kiosk.sc.next();
		
		
		for(int i =0; i<memberlist.size(); i++) {
			if(memberlist.get(i).getId().equals(id)) {
				System.out.println("회원정보 " + (i-1) + "번");
				System.out.println("ID : " + memberlist.get(i).getId());
				System.out.println("이름 : " + memberlist.get(i).getName());
				System.out.println("E-mail : " + memberlist.get(i).getEmail());
				int save = memberlist.get(i).getTime();
				System.out.printf("남은 시간\n%02d : %02d : %02d",(save/3600), (save/60)%60, (save%60));
				System.out.println();
				System.out.println("총 사용금액 : " + memberlist.get(i).getMoney() + "원");
			}
		}
//		if(member.getId().equals(id)) {
//			int i = 1;
//		//i번째면 = i번째 출력
//			for(Member t : memberlist) {
//			
//			System.out.println("ID : " + t.getId());
//			System.out.println("NAME : " + t.getName() );
//			System.out.println("E-mail : " + t.getEmail());
//			System.out.println("남은 시간 : " + t.getTime());
//			i++;
//		// 여기부턴 욕심
////		System.out.println("총 사용 금액 : ");
////		System.out.println("총 사용 시간 : ");
//			}
//		}
	}
	
	// 회원 정보 수정
	public static boolean update() {
		for(Member t2 : memberlist) {
			System.out.println("회원 정보 수정");
		}
		return true;
	}
	
	// 회원탈퇴
	public static void signout() {
		
	}
	
//	// 8. 메일전송 메소드 
//	public static void sendEmail( String tomail, int type, String contents) {
//								// 받는사람 이메일  // type : 아이디 찾기(1), 비밀번호 찾기(2), 가입메일(3) 
//								// contents : 메일에 넣을 정보 
//		
//		
//		String fromemail = "이메일";
//		String frompassword = "비밀번호";
//		
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", "smtp.naver.com");  // 추가 기능 예상 구글 네이버 자동 분류
//		properties.put("mail.smtp.port", 587); 
//		properties.put("mail.smtp.auth", true); 
//		
//		// 1. 인증
//		Session session = Session.getDefaultInstance(properties, new Authenticator() {
//		
//			
//			@Override // 패스워드 인증 메소드 
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//													// 패스워드 인증 
//				return new javax.mail.PasswordAuthentication(fromemail, frompassword);
//															// 인증할 이메일 , 인증할 패스워드 
//			}
//			
//		}); // 인증 끝
//		
//		try {
//			// 2. 메일 보내기 
//				// 1. 보내는 사람의 인증정보
//			MimeMessage message = new MimeMessage(session);
//				// 2. 보내는 사람의 설정 
//			message.setFrom(new InternetAddress(fromemail));
//				// 3. 받는 사람의 메일주소 설정
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));
//
//			
//			// * type 구분
//			if(type == 1) {
//				
//				// 4. 메일 제목
//				message.setSubject("EZEN PC방에 아이디 찾기 정보입니다.");
//				// 5. 메일 내용
//				message.setText("회원님의 아이디 : "+ contents);
//			}
//			// type == 2 비밀번호 찾기
//			if(type == 2) {
//				message.setSubject("EZEN PC방에 비밀번호 찾기 정보입니다.");
//				message.setText("회원님의 비밀번호 : "+ contents);
//			}
//			// type == 3 가입 메일
//			if(type == 3) {
//				message.setSubject("javaconsole.");
//				message.setText("EZEN PC방 가입을 환영합니다.");
//			}
//			// 6. 메일 전송 
//			Transport.send(message);
//				
//		}
//		catch (MessagingException e) {
//			e.printStackTrace();
//			System.out.println(" [알림] : 메일 전송 실패 [관리자에게 문의]");
//		} 
//		
//	}
}
