## 주제  
>PC방 키오스크  
##
>![image](https://user-images.githubusercontent.com/88884623/139022441-bc64242c-39d6-4a08-bc04-de812a0e0af9.png)


## 개요  
> PC방관리시스템을 관리자, 좌석 키오스크, 이용자 PC화면 관리시스템을 구축할 것입니다. 

## 환경
>jdk11, Eclipse, Window10, Github  

## 설계도
><초안> ![image](https://user-images.githubusercontent.com/61840067/138249904-565b62ce-7cdd-407a-a8d8-b777ba302d7b.png)

><수정안>![그림1](https://user-images.githubusercontent.com/88884623/139001465-fc86e267-befe-49a6-a77e-13219ec8530c.png)
## MVC
><MVC>![PC방 MVC](https://user-images.githubusercontent.com/88884623/139001433-d5266bf4-70fd-4e22-9fd7-00a13ca74825.png)
>![PC방MVC2](https://user-images.githubusercontent.com/88884623/139001457-3e0af032-d77f-43bd-ac05-7314493652c5.png)
  
## 프로젝트 기간  
> ~10/27 까지  

## 역할 분담
> Member : 장희동
> Kiosk : 최병호
> Admin : 최병호  

## 주요 코드
> static int kkkkk1 = 0;
 >long firstTime = System.nanoTime();
	kkkkk1 = (int)(firstTime/1000000000); 
  long endTime = System.nanoTime();
					int kkkkk2 = (int)(endTime/1000000000);
					int kkkkk = (int)(kkkkk2 - kkkkk1);
						if(!id.equals("admin")) {
							
						System.out.println("사용시간 : " + kkkkk  + "초");
## 보완할 점
> 1. 콘솔창으로 하는 프로젝트이다보니, 출력하는 데 한계가 있음을 발견. 
  나중에 DB연동해서 UI까지 사용하면 더 제대로 된 프로그램이 나올듯.   
> 2. 어드민으로 매출확인할 때 매출액이 계속늘어나는 버그를 고쳐야함.

