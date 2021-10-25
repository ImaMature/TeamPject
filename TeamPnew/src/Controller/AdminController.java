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
	
	// ����
	public static void sales() {
		System.out.println("�� ���� : " + total_price);

	}
	
	// �����
	public static void enroll() {
		
		System.out.println("-----��� ���  ------");
		System.out.println("��ǰ��,����,������ �Է��ϼ���.");
		//System.out.println("1. �����  2. �����  3.���Ȯ��");
		System.out.println("��ǰ�̸� : "); String food = Kiosk.sc.next();
		System.out.println("��ǰ����: "); int price = Kiosk.sc.nextInt();
		System.out.println("��ǰ���� : "); int food_num = Kiosk.sc.nextInt();
			
		Orderlist orderlist = new Orderlist(food, food_num, price);
		orders.add(orderlist);
		// �԰Ÿ� ���� ���ε�
		db.upLoad(2); // (�ذ�)
	}
	
	// ��� ���� (���ڰ��� �޾Ƽ� if���� �����Ͽ� �� ����� �� ��������� �������� �����ؾߵ� 
	public static void remove() {
		System.out.println("------ ��� ���� ------");
		System.out.println("------ ��ǰ ����Ʈ ------");
		int i = 1;
		System.out.println("��ȣ\t��ǰ��");
		for(Orderlist temp : orders) {
			System.out.printf(i+"."+"%s\t\n",temp.getFood());
			i++;
		}
		System.out.println("��������� ��ǰ�� �������ּ���"); int ch = Kiosk.sc.nextInt();
		orders.remove(ch-1);

	}
	
	// �����Ȳ 
	public static void order_status() {
		
		System.out.println("------�����Ȳ �޴�------");
		System.out.println("��ǰ��\t��ǰ����\t��ǰ����\t");
		for(Orderlist temp : orders) {
			System.out.printf("%s\t%d\t%d\t\n",temp.getFood(),temp.getPrice(),
					temp.getFood_num());
		}	
	}

	//����� �ֹ� ȭ�� 
	public static void client_order() {
		// �԰Ÿ� ���� �ٿ�ε�
		db.downLoad(2); // (�ذ�)
		System.out.println("------�԰Ÿ�------ ");	
		int i = 1;
		System.out.println("��ȣ\t��ǰ��\t��ǰ����");
		for(Orderlist temp : orders) {
			System.out.printf(i +"."+"\t%s\t%d��\t\n",temp.getFood(),temp.getPrice());
			i++;
		}
		// ����� �ֹ� ó��
		//System.out.println("�����Ͻ� ��ǰ���� �Է����ּ���");
		System.out.println("�����Ͻ� ��ǰ��ȣ�� �������ּ���.");
		int ch2 = Kiosk.sc.nextInt();
		
		for(int j = 0; j<orders.size(); j++) {
			// �ֹ�����Ʈ �ε����� �Է��� ��ǰ��ȣ�� ��ġ�ϸ�
			int price_sum = 0;
			// �ֹ��Ϸ��� ��ǰ����Ʈ�� ������� ���� ��ǰ�� ��ġ�ϸ� 
			tmp.add(j);
			if(tmp.get(j).equals(ch2 - 1)) {
				// �ֹ� ����Ʈ �߰�  // �����ϴ� ���� �� ���ع����ߵ� 
				price_sum = orders.get(j).getPrice() + price_sum; 
				System.out.println("��ٱ��Ͽ� �����ϴ�. ");
				// ------------------------------------------------------------------------------------------------------��ٱ��� �޼ҵ� �۵���Ű�� (��ٱ��� ���� ������ǰ���� �����ϱ� ���� ��ȣ ���ڰ� ����)
				// order_basket(j);
				for (Integer tp : tmp) { // �����ϴ� ���ϴ� �ϳ��� �԰Ÿ� ��Ͽ� �߰��� // �ϳ��� �����ص� ������ �����ؾ� ��
					int f = 1;
					System.out.println("======��ٱ���======");
					f++;
					System.out.printf(f + "." + "\t%s\t%s", orders.get(tp).getFood(), orders.get(tp).getPrice() + "��\n");
					System.out.println("�� ������ : " + price_sum+"��");
					
					System.out.println("���� �Ͻ� �ݾ��� �Է����ּ��� ");
					System.out.println("----->");
					 int client_price  = Kiosk.sc.nextInt();
					if(client_price > price_sum) {
						int �Ž����� = (client_price - price_sum);
						System.out.println("���� �Ϸ�");
						System.out.println("�Ž����� : "+ �Ž����� + "��");
						total_price += price_sum;
					}else if(client_price == price_sum) {
						System.out.println("���� �Ϸ�");
						total_price += price_sum;
					} else {
						System.out.println("���� ����");
						System.out.println("�ݾ� ���� : " + (price_sum - client_price) + "��");
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
				System.out.println("\t    [[[ PC�� Ű����ũ ]]]");
				System.out.println("1. �α��� 2. ���̵� ã�� 3. ��й�ȣ ã��");
				System.out.println("[����] > : "); int ch = Kiosk.sc.nextInt();
				
				if(ch == 1) {//Ű����ũ ù������ 1�� ����
					System.out.println("[[�α��� ������ �Դϴ�.]]");
					System.out.println("���̵� : ");		String id = Kiosk.sc.next();
					System.out.println("��й�ȣ : ");		String pw = Kiosk.sc.next();
					boolean result1 = MemberController.login(id, pw);
					
					if(result1) {
						System.out.println("[�˸�] : �α��� ����");
					}//�α��� ����
					else if(cc.getId().equals("admin")){
						System.out.println("[�˸�] ������ �α��� ����");
						System.out.println("1. ȸ������ 2. ȸ������ 3. ���Ȯ��");
						int adminCh = Kiosk.sc.nextInt();
						
						if(adminCh == 1) {
							System.out.println("[[[ȸ������ ������ �Դϴ�.]]]");
							System.out.println("����� ID�� �Է����ּ���"); String adminId = Kiosk.sc.next();
							System.out.println("����� ��й�ȣ�� �Է����ּ���"); String adminPw = Kiosk.sc.next();
							System.out.println("����� �̸��� �Է����ּ���"); String adminName = Kiosk.sc.next();
							System.out.println("����� �̸����� �Է����ּ���"); String adminMail = Kiosk.sc.next();
							Member member = new Member(adminId, adminPw, adminName, adminMail);
							
							boolean resultAdmin = MemberController.signup(member);
							DB.upLoad(1);
							
							if(resultAdmin) {
								System.out.println("[�˸�] ȸ������ ����");
							}else {
								System.err.println("[�˸�] ȸ������ ����");
							}//ȸ������ ����
						}//������ ������
					}//������ �α��� ����
				}//Ű����ũ ù������ 1�� ���� ��
				
				if (ch == 2) {//Ű����ũ ù������ 2�� ����
					System.out.println("[[���̵� ã�� ������ �Դϴ�.]]");
					System.out.println("�̸��� �Է����ּ���. : ");	String name = Kiosk.sc.next();
					System.out.println("�̸����� �Է����ּ���. : ");	String email = Kiosk.sc.next();
					
					boolean result2 = MemberController.forgotId(name, email);
					if(result2) {
						System.out.println("[�˸�] ȸ������ ���̵� ã�ҽ��ϴ�.");
					}
					else {
						System.err.println("[�˸�] ������ ȸ�������� ���� ���̵� ã�� ���߽��ϴ�.");
					}
				}//Ű����ũ ù������ 2�� ���� ��
				
				if(ch == 3) {//Ű����ũ ù������ 3�� ����
					System.out.println("[[��й�ȣ ã�� �������Դϴ�.]]");
					System.out.println("���̵� �Է��� �ּ��� : ");		String id = Kiosk.sc.next();
					System.out.println("�̸����� �Է��� �ּ��� : ");		String email = Kiosk.sc.next();
					
					boolean result3 = MemberController.forgotPd(id, email);
					if(result3) {
						System.out.println("[�˸�] ȸ������ ��й�ȣ�� ã�ҽ��ϴ�.");	
					}else {
						System.err.println("[�˸�] ������ ȸ�������� ���� ��й�ȣ�� ã�� ���߽��ϴ�.");
					}
				}
			}catch (Exception e) {System.out.println("[���� �߻�] �����ڿ��� �����ϼ���." + e);}
		}//while end
	}//login end
	
	
}//class end
