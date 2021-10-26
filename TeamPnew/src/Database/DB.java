package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Controller.AdminController;
import Controller.MemberController;
import Model.Member;
import Model.Orderlist;

public class DB {
	// 파일 업로드
	public static void upLoad(int memberKind) {
		// memberKind1 -> 키오스크에서 좌석 예약할 때 사용할 번호
		if(memberKind == 1) {
			try {
				String memberPath = "C:\\Users\\505\\git\\TeamPject\\TeamPnew\\src\\Database/test.txt";
				FileOutputStream fileOutputStream = new FileOutputStream(memberPath);
				
				for(Member member : MemberController.memberlist) {
					
				String outString = member.getId() + "\t" + member.getPw() + "\t" + member.getName() + "\t" + member.getEmail() + "\t" + member.getTime() + "\t" + member.getMoney() + "\n";
				fileOutputStream.write(outString.getBytes());
				}
				
				fileOutputStream.flush();
				fileOutputStream.close();
				System.err.println("Member File Upload Success");
			} catch (Exception e) {
				System.err.println("Member File Upload Fail");
			}
		// memberKind2 -> 회원에서 회원가입, 로그인할 때 사용할 번호
		} else if(memberKind == 2) {
			try {
				String foodPath = "C:\\Users\\505\\git\\TeamPject\\TeamPnew\\src\\Database/Food.txt";
				FileOutputStream fileOutputStream = new FileOutputStream(foodPath);
				
				AdminController ac = new AdminController();
				
				for(Orderlist order : ac.orders) {
					String outFood = order.getFood() + "\t" + order.getFood_num() + "\t" + order.getPrice() + "\n";
					fileOutputStream.write(outFood.getBytes());
				}
				fileOutputStream.flush();
				fileOutputStream.close();
				System.out.println("Food File Upload Success");
			} catch (Exception e) {
				System.out.println("Food File Upload Faill");
			}
		}
	}
	
	// 파일 다운로드
	// memberKind1 -> 키오스크에서 좌석 예약할 때 사용할 번호
	public static void downLoad(int memberKind) {
		if(memberKind == 1) {
			try {
				String memberPath = "C:\\Users\\505\\git\\TeamPject\\TeamPnew\\src\\Database/test.txt";
				FileInputStream fileInputStream = new FileInputStream(memberPath);
				
				byte[] bytes = new byte[10240];
				fileInputStream.read(bytes);
				String str = new String(bytes);
				
				String[] members = str.split("\n");
				
				for(int i = 0; i < members.length - 1; i++) {
					String[] memberField = members[i].split("\t");
					Member member = new Member(memberField[0], memberField[1], memberField[2], memberField[3],Integer.parseInt(memberField[4]), Integer.parseInt(memberField[5]) );
					MemberController.memberlist.add(member);
					
				}
				System.out.println("Member File Download Success");
			} catch (Exception e) {
				System.err.println("Member File Download Fail");
			}
		// memberKind2 -> 회원에서 회원가입, 로그인할 때 사용할 번호
		} else if(memberKind == 2) {
			try {
			String foodPath ="C:\\Users\\505\\git\\TeamPject\\TeamPnew\\src\\Database/Food.txt"; 
				FileInputStream fileInputStream = new FileInputStream(foodPath);
				
				byte[] bytes = new byte[10240];
				
				fileInputStream.read(bytes);
				
				String inString = new String(bytes);
				
				String[] foods = inString.split("\n");
				for(int i = 0; i < foods.length - 1; i++) {
					String[] foodsField = foods[i].split("\t");
					Orderlist order = new Orderlist(foodsField[0], foodsField[1], foodsField[2]);
					AdminController.orders.add(order);
				}
				
				System.out.println("Food File Download Success");
			} catch (Exception e) {
				System.out.println("Food File Download Fail");
			}
		}
	}
}
