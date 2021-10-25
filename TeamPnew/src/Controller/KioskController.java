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
		
		
		String seatEmpty = "��\t";
		String seatEnough = "��\t";
		
		
		
		while(true) {
			System.out.println("================   PC�� Ű����ũ   ===============");
			System.out.println("------------------  �¼� ��Ȳ  ------------------");
	
			for(int i = 0; i < 6; i++) {
				System.out.print((i + 1) + "\t");
			}
			System.out.println();
			for(int i = 0; i < 6; i ++) {
				seat.add("��\t");
				System.out.print(seat.get(i));
			}
			System.out.println("\n-----------------------------------------------");
			System.out.println("\t1. �¼�����\t2. ������ �ڸ��� �̵�");
			System.out.print("----->");
			
			int ch = Kiosk.sc.nextInt();
			
			if(ch == 1) {
				System.out.print("ID : ");
				String id = Kiosk.sc.next();
				System.out.print("PW : ");
				String pw = Kiosk.sc.next();
				
				
				boolean Logcheck = MemberController.login(id, pw);
				
				if(Logcheck) {
					System.out.println("------------------  �¼� ����  ------------------");
					System.out.println("\t���ϴ� �¼� ��ȣ�� ������.");
					System.out.print("----->");
					ch2 = Kiosk.sc.nextInt();

					for(int i = 1; i < seat.size(); i++) {
						if(ch2 == i) {
							if(seat.get(i - 1).equals(seatEnough)) {
								System.out.println("������ �ڸ��� ������Դϴ�.");
							} else {
								seat.set(i - 1, seatEnough).replace(seatEmpty, seatEnough);
								System.out.println("������ �ڸ����� �α��� ���ּ���.");
							}
						}
					}
				} else {
					System.out.println("ȸ�������� Ʋ���ϴ�.");
				}
				
			} else if(ch == 2) {
				System.out.println("�ڸ� Ȯ���� ���� �α��� �ʿ�");
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
							System.out.println("[�˸�] ������ �α��� ����");
							System.out.println("1. ȸ������ 2. ȸ������ 3. ���Ȯ�� 4. ����� 5. �ð��߰� 6. ����Ȯ��");
							int adminCh = Kiosk.sc.nextInt();
								
							if(adminCh == 1){
								System.out.println("[[[ȸ������ ������ �Դϴ�.]]]");
								System.out.println("����� ID�� �Է����ּ���"); String adminId = Kiosk.sc.next();
								System.out.println("����� ��й�ȣ�� �Է����ּ���"); String adminPw = Kiosk.sc.next();
								System.out.println("����� �̸��� �Է����ּ���"); String adminName = Kiosk.sc.next();
								System.out.println("����� �̸����� �Է����ּ���"); String adminMail = Kiosk.sc.next();
								Member guest = new Member(adminId, adminPw, adminName, adminMail);
								
								boolean resultAdmin = MemberController.signup(guest);
								DB.upLoad();
							
								if(resultAdmin) {
									System.out.println("[�˸�] ȸ������ ����");
								}else {
									System.err.println("[�˸�] ȸ������ ����");
								}//ȸ������ ����
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
								// �ð��߰�(����)
							} else if(adminCh == 6){
								ac.sales();
							} else {
								run = false;
							}
						}
				String[] gamement = {"�ν�Ʈ��ũ : ������ �Ƿ���", "�� : ��ȥ�� ��Ÿ��", "GTA5 : ���� �̼� ������", "��ƺ��2 : ���ǽ��� �޹���"
						, "����4 : ī��� �ϴ���", "��Ÿũ����Ʈ : �պ����� ������", "��Ʋ�׶��� : TOP10�̶� ������", "���̹����� : �ο��� ������"
						, "��Ʃ�� : ����ä�� ���鼭 �ǽĴ����", "�� ������ �� ì��� ��"};
				
						
						System.out.println("------------------  ���� �¼�  ------------------");
						System.out.println("�¼� ��ȣ : " + (seat.indexOf(seatEnough) + 1));
						System.out.println("EZEN PC�濡 ���Ű��� ȯ���մϴ�.");
						System.out.println("�ڷγ� Ȯ�� ������ ���� ����ũ�� �׽� ����ٶ��ϴ�.");
						System.out.println("1. ����\t2. �԰Ÿ�\t3. �α׾ƿ�");
						System.out.print("���ϴ� �޴� ���� : ");
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
							System.out.println("------------------  �԰� ����  ------------------");
							
							ac.client_order(); // *********
						} else {
							for(int i = 0; i < seat.size(); i++) {
								if(ch2 == i) {
									if(seat.get(i - 1).equals(seatEnough)) {
										seat.set((i - 1), seatEmpty).replace(seatEnough, seatEmpty);
										System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
									}
								}
							}
							break;
						}
					}
					long endTime = System.nanoTime();
					int kkkkk2 = (int)(endTime/1000000000);
					int kkkkk = (int)(kkkkk2 - kkkkk1);
					System.out.println("���ð� : " + kkkkk  + "��");
				}
			}
		}
	
	}
}
