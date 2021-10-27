package Controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Database.DB;
import Model.Map_order;
import Model.Member;
import Model.Orderlist;
import View.Kiosk;

public class AdminController {
   
   public static ArrayList <Orderlist> orders = new ArrayList<>(); 
   //public static ArrayList<Integer> idx_tmp = new ArrayList<>();
   public static ArrayList<Map_order> map_order = new ArrayList<Map_order>();
   
   static int total_price; 
   static DB db = new DB();
   
   // 매출
   public static void sales() {
	   for(Member temp : MemberController.memberlist) {
		   int money = temp.getMoney();
		   total_price += money;
	   }
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
      boolean flag = true;
      while(true) {
         int price_sum = 0;
         int i = 1;
         if(flag == false)break;
         System.out.println("------먹거리------ ");   
         System.out.println("번호\t제품명\t제품가격");
         for(Orderlist temp : orders) {
            System.out.printf( i +"."+"\t%s\t%d원\t\n",temp.getFood(),temp.getPrice());
            i++;
         }
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         // 구매할 제품번호를 선택하여  선택한 제품번호를 장바구니(map)에 저장 
         System.out.println("구매하실 제품번호를 선택해주세요.");int ch2 = Kiosk.sc.nextInt();
         for(int j = 0; j<orders.size(); j++) {
            // 주문리스트 인덱스와 입력한 제품번호가 일치하면
            // 주문하려는 물품리스트와 사용자의 구매 물품이 일치하면 
            
            // 선택한 제품번호를 저장한후 map과 비교해서 선택한 값과 일치한다면  장바구니에 완전히 담긴걸로 인식 
            if(j == ch2-1) {
               System.out.println("구매하실 제품의 수량을 입력해주세요"); int ch3 = Kiosk.sc.nextInt();
               
               Map_order map_order2 = new Map_order(orders.get(j).getFood(), ch3, orders.get(j).getPrice());
               map_order.add(map_order2);
               System.out.println("장바구니에 담겼습니다. \n\n");
               
               System.out.println("====== 장바구니 ======");
               System.out.println("물품\t수량\t결제금액");
               
                 
              
            }
         }
               // 장바구니 객체전체를 조회해서 출력
         	
               for(int k = 0; k<map_order.size(); k++) {
               //while(keyIterator.hasNext()) {//
                  
	              System.out.println(map_order.get(k).getProduct() + "\t" + map_order.get(k).getCount() 
	            		  +"\t"+ map_order.get(k).getTotal()*map_order.get(k).getCount()); // 해당값의 총 값이 들어가야되는데 count * 
	              price_sum = orders.get(k).getPrice()*map_order.get(k).getCount() + price_sum;
	              
	              // 수량만 체크해서 넘겨주는 느낌
	              
	              
                  
               }//
               
            
         
         
         
         while(true) {
            System.out.println("1.추가 결제  2. 최종결제 "); int braket = Kiosk.sc.nextInt();
            if(braket == 1) {
               break;
            }
            if(braket == 2) {
                for (int k2 = 0; k2<map_order.size(); k2++) {
              	  
              	  int orderminus = orders.get(k2).getFood_num() - map_order.get(k2).getCount();
  	              orders.get(k2).setFood_num(orderminus);
                 
  	              System.out.println("총 결제액 : " + price_sum+"원");   
                }
                     
               flag = false;
               System.out.println("결제 하실 금액을 입력해주세요 ");
               System.out.println("----->"); int client_price  = Kiosk.sc.nextInt();
                  
               if(client_price > price_sum) {
                  int 거스름돈 = (client_price - price_sum);
                  System.out.println("결제 완료");
                  System.out.println("거스름돈 : "+ 거스름돈 + "원");
                  total_price += price_sum;
                  
                  int save_money = MemberController.memberlist.get(KioskController.idx_1).getMoney() + price_sum;
                  MemberController.memberlist.get(KioskController.idx_1).setMoney(save_money);
                  
                  db.upLoad(2);
                  map_order.clear();
                  break;
                  
               }else if(client_price == price_sum) {
                  System.out.println("결제 완료");
                  int save_money = MemberController.memberlist.get(KioskController.idx_1).getMoney() + price_sum;
                  MemberController.memberlist.get(KioskController.idx_1).setMoney(save_money);
                 
                  db.upLoad(2);
                  map_order.clear();
                  break;
               } else {
                  System.out.println("결제 실패");
                  System.out.println("금액 부족 : " + (price_sum - client_price) + "원");
               }
               
            }
         } // w e
                   
         
         
      }// w e 
      

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
               System.out.println("아이디 : ");      String id = Kiosk.sc.next();
               System.out.println("비밀번호 : ");      String pw = Kiosk.sc.next();
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
               System.out.println("이름을 입력해주세요. : ");   String name = Kiosk.sc.next();
               System.out.println("이메일을 입력해주세요. : ");   String email = Kiosk.sc.next();
               
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
               System.out.println("아이디를 입력해 주세요 : ");      String id = Kiosk.sc.next();
               System.out.println("이메일을 입력해 주세요 : ");      String email = Kiosk.sc.next();
               
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