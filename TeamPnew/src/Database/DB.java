package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Controller.MemberController;
import Model.Member;

public class DB {
	// 파일 업로드
	public static void upLoad() {
		// memberKind1 -> 키오스크에서 좌석 예약할 때 사용할 번호
//		if(memberKind == 1) {
			try {
				String memberPath = "C:\\Users\\505\\git\\ezen_console2\\TeamProject\\src\\Database\\test.txt";
				FileOutputStream fileOutputStream = new FileOutputStream(memberPath);
				
				for(Member member : MemberController.memberlist) {
					
				String outString = member.getId() + "\t" + member.getPw() + "\t" + member.getName() + "\t" + member.getEmail() + member.getTime() + "\n";
				fileOutputStream.write(outString.getBytes());
				}
				
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (Exception e) {
				System.err.println("File Upload Fail");
				System.out.println(e.getMessage());
			}
	}
		// memberKind2 -> 회원에서 회원가입, 로그인할 때 사용할 번호
//		} else if(memberKind == 2) {
//			
//		}
//	}
	
	// 파일 다운로드
	// memberKind1 -> 키오스크에서 좌석 예약할 때 사용할 번호
	public static void downLoad() {
//		if(memberKind == 1) {
			try {
				String memberPath = "C:\\Users\\505\\git\\ezen_console2\\TeamProject\\src\\Database\\test.txt";
				FileInputStream fileInputStream = new FileInputStream(memberPath);
				
				byte[] bytes = new byte[10240];
				fileInputStream.read(bytes);
				String str = new String(bytes);
				
				String[] members = str.split("\n");
				
				for(int i = 0; i < members.length - 1; i++) {
					String[] memberField = members[i].split("\t");
					Member member = new Member(memberField[0], memberField[1], memberField[2], memberField[3]);
					MemberController.memberlist.add(member);
					
				}
				System.out.println("File Download Success");
			} catch (Exception e) {
				System.err.println("File Download Fail");
			}
		// memberKind2 -> 회원에서 회원가입, 로그인할 때 사용할 번호
//		} else if(memberKind == 2) {
//			
//		}
//	}
	}
}
