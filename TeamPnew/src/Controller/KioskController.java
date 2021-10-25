package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Database.DB;
import Model.Member;
import View.Kiosk;

public class KioskController {
	public static void seatStatus() {
		ArrayList<String> seat = new ArrayList<>();
		AdminController ac = new AdminController();
		Member member = new Member();
		DB.downLoad();
		int ch2 = 0;
		int ch3;
		
		
		String seatEmpty = "□\t";
		String seatEnough = "■\t";
		
		
		
		while(true) {
			System.out.println("================   PC방 키오스크   ===============");
			System.out.println("------------------  좌석 현황  ------------------");
	
			for(int i = 0; i < 6; i++) {
				System.out.print((i + 1) + "\t");
			}
			System.out.println();
			for(int i = 0; i < 6; i ++) {
				seat.add("□\t");
				System.out.print(seat.get(i));
			}
			System.out.println("\n-----------------------------------------------");
			System.out.println("\t1. 좌석선택\t2. 선택한 자리로 이동");
			System.out.print("----->");
			
			int ch = Kiosk.sc.nextInt();
			
			if(ch == 1) {
				System.out.print("ID : ");
				String id = Kiosk.sc.next();
				System.out.print("PW : ");
				String pw = Kiosk.sc.next();
				
				
				boolean Logcheck = MemberController.login(id, pw);
				
				if(Logcheck) {
					System.out.println("------------------  좌석 선택  ------------------");
					System.out.println("\t원하는 좌석 번호를 고르세요.");
					System.out.print("----->");
					ch2 = Kiosk.sc.nextInt();

					for(int i = 1; i < seat.size(); i++) {
						if(ch2 == i) {
							if(seat.get(i - 1).equals(seatEnough)) {
								System.out.println("선택한 자리가 사용중입니다.");
							} else {
								seat.set(i - 1, seatEnough).replace(seatEmpty, seatEnough);
								System.out.println("선택한 자리에서 로그인 해주세요.");
							}
						}
					}
				} else {
					System.out.println("회원정보가 틀립니다.");
				}
				
			} else if(ch == 2) {
				System.out.println("자리 확인을 위한 로그인 필요");
				System.out.print("ID : ");
				String id = Kiosk.sc.next();
				System.out.print("PW : ");
				String pw = Kiosk.sc.next();
				
				boolean Logcheck = MemberController.login(id, pw);
				boolean run = true;
				if(Logcheck) {
					long firstTime = System.nanoTime();
					int kkkkk1 = (int)(firstTime/1000000000);
					System.out.println(firstTime / 1000000000);
					while(run) {
						if(id.equals("admin")) {
							System.out.println("[알림] 관리자 로그인 성공");
							System.out.println("1. 회원가입 2. 회원정보 3. 재고확인 4. 재고등록 5. 시간추가 6. 매출확인");
							int adminCh = Kiosk.sc.nextInt();
								
							if(adminCh == 1){
								System.out.println("[[[회원가입 페이지 입니다.]]]");
								System.out.println("사용할 ID를 입력해주세요"); String adminId = Kiosk.sc.next();
								System.out.println("사용할 비밀번호를 입력해주세요"); String adminPw = Kiosk.sc.next();
								System.out.println("사용할 이름을 입력해주세요"); String adminName = Kiosk.sc.next();
								System.out.println("사용할 이메일을 입력해주세요"); String adminMail = Kiosk.sc.next();
								Member guest = new Member(adminId, adminPw, adminName, adminMail);
								
								boolean resultAdmin = MemberController.signup(guest);
								DB.upLoad();
							
								if(resultAdmin) {
									System.out.println("[알림] 회원가입 성공");
								}else {
									System.err.println("[알림] 회원가입 실패");
								}//회원가입 실패
								break;
							} else if(adminCh == 2) {
								MemberController mc = new MemberController();
								mc.info();
							} else if(adminCh == 3){
								ac.order_status();
							} else if(adminCh == 4) {
								ac.enroll();
								break;
							} else if(adminCh == 5) {
								// 시간추가(결제)
							} else if(adminCh == 6){
								ac.sales();
							} else {
								run = false;
							}
						}
				String[] gamement = {"로스트아크 : 에포나 의뢰중", "롤 : 영혼의 한타중", "GTA5 : 습격 미션 진행중", "디아블로2 : 메피스토 앵벌중"
						, "피파4 : 카드깡 하는중", "스타크래프트 : 손빠르기 측정중", "배틀그라운드 : TOP10이라 존버중", "네이버웹툰 : 싸움독학 보는중"
						, "유튜브 : 개그채널 보면서 피식대는중", "집 가려고 짐 챙기는 중"};
				
						
						System.out.println("------------------  개인 좌석  ------------------");
						System.out.println("좌석 번호 : " + (seat.indexOf(seatEnough) + 1));
						System.out.println("EZEN PC방에 오신것을 환영합니다.");
						System.out.println("코로나 확산 방지를 위해 마스크를 항시 착용바랍니다.");
						System.out.println("1. 게임\t2. 먹거리\t3. 로그아웃");
						System.out.print("원하는 메뉴 선택 : ");
						ch3 = Kiosk.sc.nextInt();
						
						
						if(ch3 == 1) {
							try {
								 for(int i = 0; i < 10; i++){
									System.out.println(gamement[i]);
									Thread.sleep(500);
								}
								 
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if(ch3 == 2) {
							System.out.println("------------------  먹고 하자  ------------------");
							
							ac.client_order(); // *********
						} else {
							for(int i = 0; i < seat.size(); i++) {
								if(ch2 == i) {
									if(seat.get(i - 1).equals(seatEnough)) {
										seat.set((i - 1), seatEmpty).replace(seatEnough, seatEmpty);
										System.out.println("로그아웃 되었습니다.");
									}
								}
							}
							break;
						}
					}
					long endTime = System.nanoTime();
					int kkkkk2 = (int)(endTime/1000000000);
					int kkkkk = (int)(kkkkk2 - kkkkk1);
					System.out.println("사용시간 : " + kkkkk  + "초");
				}
			}
		}
	
	}
}
