package Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Database.DB;
import Model.Member;
import View.Kiosk;

public class KioskController {
	
	static int kkkkk1 = 0;
	static int idx_1 = 0;
	public static void seatStatus() {
		ArrayList<String> seat = new ArrayList<>();
		AdminController ac = new AdminController();
		MemberController mc = new MemberController();
		Member member = new Member();
		
		DB.downLoad(1);
		DB.downLoad(2);
		int ch2 = 0;
		int ch3;
		
		boolean sw = true;
		
		
		String seatEmpty = "□\t";
		String seatEnough = "■\t";
		
		// 시간 계산 및 저장할때는 int형으로 받아서 표시할때만 string으로 표현  
		
		while(true) {
			System.out.println("=============================  PC방 키오스크  ==============================");
			System.out.println("|\t-----------------------  좌석 현황  -----------------------\t|");
	
			System.out.print("|\t|\t");
			for(int i = 0; i < 6; i++) {
				System.out.print((i + 1) + "\t");
			}
			System.out.print("|\t|");
			System.out.println();
			System.out.print("|\t|\t");
			for(int i = 0; i < 6; i++) {
				seat.add("□\t");
				System.out.print(seat.get(i));
			}
			System.out.print("|\t|");
			
			System.out.println("\n|\t---------------------------------------------------------\t|");
			System.out.println("|\t| 1. 회원\t | 2. 좌석선택 |\t3. 선택한 자리로 이동  |\t\t|\t|");
			System.out.print("|\t| -----> ");
		
			int ch = Kiosk.sc.nextInt();
			System.out.println("|\t|-------------------------------------------------------|\t|");
			if(ch == 1) {
				System.out.println("|\t| 1. 회원 가입 | 2.아이디 찾기 | 3.비밀번호 찾기 | 4. 시간결제\t|\t|");
				System.out.print("|\t| -----> ");
				int mainCh = Kiosk.sc.nextInt();
				
				if(mainCh == 1) {
					
					System.out.println("|\t-----------------------  회원 가입  -----------------------\t|");
					System.out.print("|\t| 사용할 ID를 입력해주세요 : "); String adminId = Kiosk.sc.next();
					System.out.print("|\t| 사용할 비밀번호를 입력해주세요 : "); String adminPw = Kiosk.sc.next();
					System.out.print("|\t| 사용할 이름을 입력해주세요 : "); String adminName = Kiosk.sc.next();
					System.out.print("|\t| 사용할 이메일을 입력해주세요 : "); String adminMail = Kiosk.sc.next();
					
					Member guest = new Member(adminId, adminPw, adminName, adminMail, 0, 0);
					
					boolean resultAdmin = MemberController.signup(guest);
					System.out.println("|\t|-------------------------------------------------------|\t|");
					DB.upLoad(1);
				
					if(resultAdmin) {
						for(int i = 0; i < 100; i++) {
							System.out.println();
						}
						System.out.println("[알림] 회원가입 성공");
					}else {
						for(int i = 0; i < 100; i++) {
							System.out.println();
						}
						System.err.println("[알림] 회원가입 실패");
					}//회원가입 실패
					//continue;
					
					
				}
				if(mainCh == 2) {
					System.out.println("|\t|----------------------  아이디찾기  ----------------------|\t|");
					System.out.print("|\t| 이름 : "); 	String name = Kiosk.sc.next();
					System.out.print("|\t| 이메일 : ");	String email = Kiosk.sc.next();
					mc.forgotId(name, email);
					System.out.println("|\t|-------------------------------------------------------|\t|");
					continue;
				}
				if(mainCh == 3) {
					System.out.println("|\t|---------------------  비밀번호찾기  ----------------------|\t|");
					System.out.println("|\t| 아이디 : ");	String id2 = Kiosk.sc.next();
					System.out.println("|\t| 이메일 : ");	String email2 = Kiosk.sc.next();
					mc.forgotPd(id2, email2);
					System.out.println("|\t|-------------------------------------------------------|\t|");
					continue;
				}
				if(mainCh == 4) {
					System.out.println("결제를 위한 로그인");
					System.out.print("ID : ");
					String id = Kiosk.sc.next();
					System.out.print("PW : ");
					String pw = Kiosk.sc.next();
					
					boolean logincheck = MemberController.login(id, pw);
					
					
					
					if(logincheck) {
						
						System.out.println("시간충전을 선택해주세요 ");
						System.out.println("1. 1시간/1000원\t2. 2시간/2000원\t3. 5시간/5000원\t4. 10시간/10000원");
						
						int timech = Kiosk.sc.nextInt();
						
						if(timech == 1) {
							for(int i =0; i<MemberController.memberlist.size(); i++) {
								if(MemberController.memberlist.get(i).getId().equals(id)) {
									idx_1 = i;
									MemberController.memberlist.get(idx_1).setTime(MemberController.memberlist.get(idx_1).getTime() + 3600);
									MemberController.memberlist.get(idx_1).setMoney(MemberController.memberlist.get(idx_1).getMoney() + 1000);
									ac.total_price += 1000;
									DB.upLoad(1);
									break;
								}
							}
						}
						if(timech == 2) {
							for(int i =0; i<MemberController.memberlist.size(); i++) {
								if(MemberController.memberlist.get(i).getId().equals(id)) {
									idx_1 = i;
									MemberController.memberlist.get(idx_1).setTime(MemberController.memberlist.get(idx_1).getTime() + 7200);
									MemberController.memberlist.get(idx_1).setMoney(MemberController.memberlist.get(idx_1).getMoney() + 2000);
									ac.total_price += 2000;
									DB.upLoad(1);
									break;
								}
							}
						}
						if(timech == 3) {
							for(int i =0; i<MemberController.memberlist.size(); i++) {
								if(MemberController.memberlist.get(i).getId().equals(id)) {
									idx_1 = i;
									MemberController.memberlist.get(idx_1).setTime(MemberController.memberlist.get(idx_1).getTime() + 18000);
									MemberController.memberlist.get(idx_1).setMoney(MemberController.memberlist.get(idx_1).getMoney() + 5000);
									ac.total_price += 5000;
									DB.upLoad(1);
									break;
								}
							}
						}
						if(timech == 4) {
							for(int i =0; i<MemberController.memberlist.size(); i++) {
								if(MemberController.memberlist.get(i).getId().equals(id)) {
									idx_1 = i;
									MemberController.memberlist.get(idx_1).setTime(MemberController.memberlist.get(idx_1).getTime() + 36000);
									MemberController.memberlist.get(idx_1).setMoney(MemberController.memberlist.get(idx_1).getMoney() + 10000);
									ac.total_price += 10000;
									DB.upLoad(1);
									break;
								}
							}
						}
					}
					mc.timeControll();
				}
			}
		
			if(ch == 2) {
					System.out.print("ID : ");
					String id = Kiosk.sc.next();
					System.out.print("PW : ");
					String pw = Kiosk.sc.next();
					
					boolean Logcheck = MemberController.login(id, pw);
					
					for(int i = 0; i < mc.memberlist.size(); i++) {
						if(mc.memberlist.get(i).getId().equals(id)) {
							idx_1 = i;
							if(mc.memberlist.get(idx_1).getTime() <= 0) {
								Logcheck = false;
							}
						}
					}
					
					if(id.equals("admin")) {
						System.out.println("좌석을 예약할 수 없습니다.");
						continue;
					}

					for(int i =0; i<MemberController.memberlist.size(); i++) {
						if(MemberController.memberlist.get(i).getId().equals(id)) {
							idx_1 = i;
						}
					}
				
				if(Logcheck) {
					System.out.println("------------------  좌석 선택  ------------------");
					System.out.println("\t원하는 좌석 번호를 고르세요.");
					System.out.print("----->");
					ch2 = Kiosk.sc.nextInt();

					for(int i = 1; i < seat.size(); i++) {
						if(ch2 == i) {
							if(seat.get(i - 1).equals(seatEnough)) { //번호는 123~ 자리 인덱스는 012~
								System.out.println("선택한 자리가 사용중입니다.");
							} else {
								seat.set(i - 1, seatEnough).replace(seatEmpty, seatEnough); //?
								System.out.println("선택한 자리에서 로그인 해주세요.");
							}
						}
					}
				} else {
					System.out.println("회원정보가 틀리거나 시간 충전이 필요합니다.");
				}
				
			} else if(ch == 3) {
				System.out.println("자리 확인을 위한 로그인 필요");
				System.out.print("ID : ");
				String id = Kiosk.sc.next();
				System.out.print("PW : ");
				String pw = Kiosk.sc.next();
				
				boolean Logcheck = MemberController.login(id, pw);
				
				for(int i = 0; i < seat.size(); i++) {
					if(id.equals("admin") || seat.get(i).equals(seatEnough)) {
						sw = false;
						break;
					} 
				}
				if(sw) {
					System.out.println("좌석 예약 후 이용 바랍니다.");
					sw = true;
					continue;
				}
				
				boolean run = true;
				if(Logcheck) {
					long firstTime = System.nanoTime();
					kkkkk1 = (int)(firstTime/1000000000);
					while(run) {
						if(id.equals("admin")) {
							System.out.println("[알림] 관리자 로그인 성공");
							System.out.println(" 1. 회원정보 2. 재고확인 3. 재고등록 4. 매출확인 5. 나가기");
							int adminCh = Kiosk.sc.nextInt();
								
							if(adminCh == 1){
								mc.info();
								continue;
							} else if(adminCh == 2) {
								ac.order_status();
								continue;
							} else if(adminCh == 3){
								ac.enroll();
								continue;
							} else if(adminCh == 4) {
								ac.sales();
								continue;
							} else if(adminCh == 5){
								break;
							} 
						continue;	
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
						if(!id.equals("admin")) {
							
						System.out.println("사용시간 : " + kkkkk  + "초"); // 시간, 분, 초로 찍기
						for(int i = 0; i < MemberController.memberlist.size(); i++) {
							if(MemberController.memberlist.get(i).getId().equals(id)) {
								idx_1 = i;
								MemberController.memberlist.get(idx_1).setTime(MemberController.memberlist.get(idx_1).getTime() - kkkkk);
								mc.timeControll();
								DB.upLoad(1);
								break;
							}
						}
					}
				}
			}
		}
	}
}
