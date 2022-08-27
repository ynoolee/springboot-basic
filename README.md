# SpringBoot Basic Weekly Mission
스프링부트 basic 위클리미션을 코드리뷰하는 Repository입니다.

주차별 과제는 노션에서 확인하세요!
[노션에서 미션 확인가기](https://www.notion.so/backend-devcourse/Part1-3-38f57acca0dd490db11393701417943a)


## 바우처 목록 출력 페이지
( 1차 버전 )



- 쿼리 파라미터 없이 보낸 경우 -> 기본적으로 5 개의 목록만 출력하도록 한다
<img width="1184" alt="Screen Shot 2022-08-27 at 1 00 06 PM" src="https://user-images.githubusercontent.com/53856184/187013569-be1fc434-d353-46b7-bcd9-a23e72736519.png">

- limit 과 offset 을 사용하여 특정 페이지, 특정 사이즈의 목록을 출력 할 수 있다 
  - http://localhost:8080/vouchers?limit=10 인 경우
    <img width="1119" alt="Screen Shot 2022-08-27 at 1 01 19 PM" src="https://user-images.githubusercontent.com/53856184/187013594-95ea859f-4822-4b21-802d-d3c2a8ea2ac3.png">
  - http://localhost:8080/vouchers?page=1&limit=3 인 경우
    <img width="1152" alt="Screen Shot 2022-08-27 at 1 02 00 PM" src="https://user-images.githubusercontent.com/53856184/187013607-5829e2ef-b440-4207-a963-9b760e70cb82.png">

## 바우처 등록 페이지
<img width="1186" alt="Screen Shot 2022-08-27 at 12 54 45 PM" src="https://user-images.githubusercontent.com/53856184/187013428-f83498a8-847e-49b6-838a-645424fcfc72.png">


### 바우처 등록 실패 시
( 1차 버전 - 다른 처리를 아직 하지 않아 에러로 출력되는 상황 )
<img width="1043" alt="image" src="https://user-images.githubusercontent.com/53856184/187013631-20e83f0b-8250-4738-996f-b4042cafa392.png">
