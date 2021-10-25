package Controller;
import java.util.ArrayList;

import Database.DB;
import Model.Member;
import Model.Orderlist;
import View.Kiosk;

public class AdminController {
	
	public static ArrayList <Orderlist> orders = new ArrayList<>(); 
	public static ArrayList<Integer> tmp = new ArrayList<>();
	static int total_price; 
	static DB db = new DB();
	
	// 매출
	public static void sales() {
		System.out.println("총 매출 : " + total_price);

	}
	
	// 재고등록
	public static void enroll() {
		
		System.out.println("-----재고 등록  ------");
		System.out.println("제품명,가격,수량을 입력하세요.");
		//System.out.println("1. 재고등록  2. 재고빼기  3.재고확인");
		System.out.println("제품이름 : "); String food = Kiosk.sc.next();
		System.out.println("제품가격: "); int price = Kiosk.sc.nextInt();
		System.out.println("제품수량 : "); int food_num = Kiosk.sc.nextInt();
			
		Orderlist orderlist = new Orderlist(food, food_num, price);
		orders.add(orderlist);
		// 먹거리 파일 업로드
		db.upLoad(2); // (해결)
	}
	
	// 재고 정리 (인자값을 받아서 if문을 실행하여 각 재고등록 및 재고정리를 실행할지 생각해야됨 
	public static void remove() {
		System.out.println("------ 재고 정리 ------");
		System.out.println("------ 물품 리스트 ------");
		int i = 1;
		System.out.println("번호\t제품명");
		for(Orderlist temp : orders) {
			System.out.printf(i+"."+"%s\t\n",temp.getFood());
			i++;
		}
		System.out.println("재고정리할 물품을 선택해주세요"); int ch = Kiosk.sc.nextInt();
		orders.remove(ch-1);

	}
	
	// 재고현황 
	public static void order_status() {
		
		System.out.println("------재고현황 메뉴------");
		System.out.println("제품명\t제품가격\t제품수량\t");
		for(Orderlist temp : orders) {
			System.out.printf("%s\t%d\t%d\t\n",temp.getFood(),temp.getPrice(),
					temp.getFood_num());
		}	
	}

	//사용자 주문 화면 
	public static void client_order() {
		// 먹거리 파일 다운로드
		db.downLoad(2); // (해결)
		System.out.println("------먹거리------ ");	
		int i = 1;
		System.out.println("번호\t제품명\t제품가격");
		for(Orderlist temp : orders) {
			System.out.printf(i +"."+"\t%s\t%d원\t\n",temp.getFood(),temp.getPrice());
			i++;
		}
		// 사용자 주문 처리
		//System.out.println("구매하실 제품명을 입력해주세요");
		System.out.println("구매하실 제품번호를 선택해주세요.");
		int ch2 = Kiosk.sc.nextInt();
		
		for(int j = 0; j<orders.size(); j++) {
			// 주문리스트 인덱스와 입력한 제품번호가 일치하면
			int price_sum = 0;
			// 주문하려는 물품리스트와 사용자의 구매 물품이 일치하면 
			tmp.add(j);
			if(tmp.get(j).equals(ch2 - 1)) {
				// 주문 리스트 추가  // 구매하는 순간 다 더해버려야됨 
				price_sum = orders.get(j).getPrice() + price_sum; 
				System.out.println("장바구니에 담겼습니다. ");
				// ------------------------------------------------------------------------------------------------------장바구니 메소드 작동시키기 (장바구니 결제 이후제품수량 제거하기 결제 번호 인자값 전달)
				// order_basket(j);
				for (Integer tp : tmp) { // 구매하던 못하던 하나씩 먹거리 목록에 추가됨 // 하나만 선택해도 무조건 결제해야 함
					int f = 1;
					System.out.println("======장바구니======");
					f++;
					System.out.printf(f + "." + "\t%s\t%s", orders.get(tp).getFood(), orders.get(tp).getPrice() + "원\n");
					System.out.println("총 결제액 : " + price_sum+"원");
					
					System.out.println("결제 하실 금액을 입력해주세요 ");
					System.out.println("----->");
					 int client_price  = Kiosk.sc.nextInt();
					if(client_price > price_sum) {
						int 거스름돈 = (client_price - price_sum);
						System.out.println("결제 완료");
						System.out.println("거스름돈 : "+ 거스름돈 + "원");
						total_price += price_sum;
					}else if(client_price == price_sum) {
						System.out.println("결제 완료");
						total_price += price_sum;
					} else {
						System.out.println("결제 실패");
						System.out.println("금액 부족 : " + (price_sum - client_price) + "원");
					}
					break;
				}

			}
		}
	}
	
	
	public static void login(){
		DB.downLoad(1);
		while(true) {
			Member cc = new Member();
			try {
				System.out.println("\t    [[[ PC방 키오스크 ]]]");
				System.out.println("1. 로그인 2. 아이디 찾기 3. 비밀번호 찾기");
				System.out.println("[선택] > : "); int ch = Kiosk.sc.nextInt();
				
				if(ch == 1) {//키오스크 첫페이지 1번 선택
					System.out.println("[[로그인 페이지 입니다.]]");
					System.out.println("아이디 : ");		String id = Kiosk.sc.next();
					System.out.println("비밀번호 : ");		String pw = Kiosk.sc.next();
					boolean result1 = MemberController.login(id, pw);
					
					if(result1) {
						System.out.println("[알림] : 로그인 성공");
					}//로그인 성공
					else if(cc.getId().equals("admin")){
						System.out.println("[알림] 관리자 로그인 성공");
						System.out.println("1. 회원가입 2. 회원정보 3. 재고확인");
						int adminCh = Kiosk.sc.nextInt();
						
						if(adminCh == 1) {
							System.out.println("[[[회원가입 페이지 입니다.]]]");
							System.out.println("사용할 ID를 입력해주세요"); String adminId = Kiosk.sc.next();
							System.out.println("사용할 비밀번호를 입력해주세요"); String adminPw = Kiosk.sc.next();
							System.out.println("사용할 이름을 입력해주세요"); String adminName = Kiosk.sc.next();
							System.out.println("사용할 이메일을 입력해주세요"); String adminMail = Kiosk.sc.next();
							Member member = new Member(adminId, adminPw, adminName, adminMail);
							
							boolean resultAdmin = MemberController.signup(member);
							DB.upLoad(1);
							
							if(resultAdmin) {
								System.out.println("[알림] 회원가입 성공");
							}else {
								System.err.println("[알림] 회원가입 실패");
							}//회원가입 실패
						}//관리자 페이지
					}//관리자 로그인 성공
				}//키오스크 첫페이지 1번 선택 끝
				
				if (ch == 2) {//키오스크 첫페이지 2번 선택
					System.out.println("[[아이디 찾기 페이지 입니다.]]");
					System.out.println("이름을 입력해주세요. : ");	String name = Kiosk.sc.next();
					System.out.println("이메일을 입력해주세요. : ");	String email = Kiosk.sc.next();
					
					boolean result2 = MemberController.forgotId(name, email);
					if(result2) {
						System.out.println("[알림] 회원님의 아이디를 찾았습니다.");
					}
					else {
						System.err.println("[알림] 동일한 회원정보가 없어 아이디를 찾지 못했습니다.");
					}
				}//키오스크 첫페이지 2번 선택 끝
				
				if(ch == 3) {//키오스크 첫페이지 3번 선택
					System.out.println("[[비밀번호 찾기 페이지입니다.]]");
					System.out.println("아이디를 입력해 주세요 : ");		String id = Kiosk.sc.next();
					System.out.println("이메일을 입력해 주세요 : ");		String email = Kiosk.sc.next();
					
					boolean result3 = MemberController.forgotPd(id, email);
					if(result3) {
						System.out.println("[알림] 회원님의 비밀번호를 찾았습니다.");	
					}else {
						System.err.println("[알림] 동일한 회원정보가 없어 비밀번호를 찾지 못했습니다.");
					}
				}
			}catch (Exception e) {System.out.println("[오류 발생] 관리자에게 문의하세요." + e);}
		}//while end
	}//login end
	
	
}//class end
