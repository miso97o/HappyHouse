# HappyHouse
공공데이터와 카카오 맵 API를 사용한 아파트 실거래가, 주변 상권, 뉴스 정보 확인 사이트
# 프로젝트 설명
### 배경
한 사이트에서 아파트에 대한 정보를 한번에 쉽게 얻을 수 있도록 구성
### 목적
- 회원 가입과 관심 지역 등록을 토대로 한 재검색
- 내가 등록한 관심지역의 주변 상권정보와 환경정보
- 최신 부동산 뉴스
### 기능
- 로그인, 회원가입, 정보 수정을 포함한 회원관리 및 로그인 여부에 따른 권한 부여 (JWT 사용)
- Rest API를 이용한 공지사항 작성, 조회, 수정, 삭제, 검색, 페이지네이션
- 공공데이터 API를 이용한 지역별 미세먼지 조회
- 카카오 맵 API를 통한 지도와 공공데이터, Rest API를 이용한 아파트 실거래가 조회 및 상권 조회
- 네이버 뉴스 크롤링을 통해 데이터를 DB에 저장하고 조회하는 최신 부동산 뉴스 조회 및 검색
# 프로젝트 일정
![프로젝트 일정](https://user-images.githubusercontent.com/61226384/175567220-b4e6351f-b925-4e1c-8823-94ac7a27b9a9.png)
# 개발 환경
### BackEnd
- Java 1.8
- SpringBoot 2.6.7
- Mysql 8.0.28
- Mybatis 2.2.2
### FrontEnd
- Node 16.14
- npm 8.3.1
- Vue 2.6.14
# 결과 화면
## 메인화면
![image](https://user-images.githubusercontent.com/61226384/175560113-7e59e700-351d-4a19-a634-c742e982b3cd.png)
### 로그인
![image](https://user-images.githubusercontent.com/61226384/175560339-14fbc432-cab5-475a-8f6a-1c26e74fbba8.png)
### 회원가입
![image](https://user-images.githubusercontent.com/61226384/175560438-1d6dabbc-b1c7-4755-8644-6384089607d0.png)
### 로그인 후
![image](https://user-images.githubusercontent.com/61226384/175562903-12329ba3-4af5-44c6-87eb-ab526d55e0ff.png)
## 공지사항 - 로그인 후에 이용 가능
![image](https://user-images.githubusercontent.com/61226384/175563070-38e6b099-e968-460c-89a6-63bd7eb56e4e.png)
### 작성화면
![image](https://user-images.githubusercontent.com/61226384/175563216-8369e72d-1921-4bdb-924e-49280dcb86aa.png)
### 세부화면
![image](https://user-images.githubusercontent.com/61226384/175563330-ad62ac34-10eb-4c1a-9efd-936dd7d5379d.png)
## 미세먼지
최근 5시간의 미세먼지 정보 제공
![image](https://user-images.githubusercontent.com/61226384/175563476-9cbddd34-608e-49a4-8c63-56113ce3cf1e.png)
## 아파트 검색
![image](https://user-images.githubusercontent.com/61226384/175563938-99ae3182-8cfa-4877-883a-8ab039dfbd0e.png)
### 상세보기 Modal
![image](https://user-images.githubusercontent.com/61226384/175564078-26970c2e-ca2d-43a7-8479-eee1c9a41abc.png)
### 관심지역 등록 및 아파트 이름 검색
![image](https://user-images.githubusercontent.com/61226384/175564455-571e1146-f8a2-4d67-9c8c-41961f3be50c.png)
## 주변 상권 검색
![image](https://user-images.githubusercontent.com/61226384/175565590-f0360085-ff62-4f4e-aff8-7e4112a35596.png)
## 부동산 뉴스
![image](https://user-images.githubusercontent.com/61226384/175566405-bcc443da-8a1c-42f5-a57a-85c75060eb68.png)
### 제목 검색
![image](https://user-images.githubusercontent.com/61226384/175566683-9c4216ed-d352-4fb5-92a5-519c20653d38.png)

