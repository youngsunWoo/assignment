# 서울교통공사 승하차 순위 데이터 적재 및 조회용API 개발

## 목차
- [개발환경 및 라이브러리](#개발환경-및-라이브러리)
- [빌드 및 실행](#빌드-및-실행)
- [과제 요구사항](#과제-요구사항)
- [과제 요구사항 및 결과](#과제-요구사항-및-결과)
- [과제 해결](#과제-해결)
- [시연](#시연)


## 빌드 및 실행
### 터미널 환경
Java, Docker는 설치되어 있다고 가정한다.

**Run the Application** 
```
$ ./gradlew bootRun
```
**Run the Application With Docker**
```
$ ./gradlew bootBuildImage 
$ docker run -p 8080:8080 -t assignment:0.0.1-SNAPSHOT
```

## 개발환경 및 라이브러리

#### Spring 2.3  
- 도커 컨테이너 환경으로 실행 할 수 있도록 이미지 빌드를 지원하는 2.3.X 버전을 사용

#### Spring Security
- REST API 인증(Authentication) 구현  
- 별도의 저장소가 필요 없는 JWT를 이용해 Access Token 관리 
- Access Token의 유효시간조정과 토큰 갱신 구현으로 Access Token 탈취 대응 

#### JPA 
- DB 입출력 구현
- DBMS/구조변경 등의 유지보수성을 고려 
- 비슷한 조건으로 빈번한 조회가 될 상황을 고려해 캐싱기능을 제공하는 JPA를 사용해 성능향상

#### log4j
- 로깅처리

#### Springdoc
- API 문서 작성 및 시연화면 구현

#### UniVocity Parsers
- CSV 파일 파싱 구현
- 2020이후 릴리즈 버전이 존재하는 라이브러리중 가장 처리 속도가 빠른 UniVocity 사용
- 라이브러리 선정기준   
    - 안정성   
        - 사용량이 가장 많은 라이브러리 Top 10중에 선정  
        - 2020이전 릴리즈 되지 않은 라이브러리 제외  
    - 성능  
        - JDK8 기준 가장 처리속도가 빠른 라이브러리 Top 10 선정
        - 성능측정 조건 : JDK 8 / 3,173,958 rows / 모든 data에 "(quotes)가 존재 경우  



## 과제 요구사항 및 결과

### 필수사항
#### 1. API 명세서 작성

Response
#### 2. 데이터파일(.csv)의 각 레코드를 데이터베이스에 저장하는 API 개발
- Request
  - url
  ```
  http://localhost:8080/v1/seoul-metro/data/passengers/daily/average
  ```
  - header
  ```
  "X-AUTH-TOKEN: {token}"
  ``` 
  - parameter
  ```
  {
    "year": 2019,
    "filename": "2019_data.csv"
  }
  ```  
- Response
  ```
    {
      "code": 200,
      "msg": "OK"
    }
  ```
#### 3. 일평균 인원이 가장 많은 상위 10개의 역 명과 인원 수를 출력하는 API 개발
- Request
  - url
  ```
  http://localhost:8080/v1/seoul-metro/data/passengers/daily/average
  ```
  - parameter
  ```
  {
    "year": 2019,        //조회 대상 연도
    "ascending": false,  //정렬기준(true:오름차순, false:내림차순 )
    "rankNum": 10        //출력갯수
  }
  ```  
- Response
   ```
    {
      "code": 200,
      "msg": "OK",
      "data": [
        {
          "stationName": "강남",
          "passengerCount": 202174
        },
        {
          "stationName": "잠실(2)",
          "passengerCount": 170721
        },
        {
          "stationName": "홍대입구",
          "passengerCount": 167872
        },
        {
          "stationName": "신림",
          "passengerCount": 139189
        },
        {
          "stationName": "구로디지털단지",
          "passengerCount": 126034
        },
        {
          "stationName": "고속터미널(3)",
          "passengerCount": 121245
        },
        {
          "stationName": "삼성",
          "passengerCount": 121183
        },
        {
          "stationName": "신도림",
          "passengerCount": 118081
        },
        {
          "stationName": "서울역(1)",
          "passengerCount": 111771
        },
        {
          "stationName": "서울대입구",
          "passengerCount": 105143
        }
      ]
    }
  ```

#### 4. 월 인원수 평균이 가장 낮은 역 명과 인원수를 출력하는 API 개발
- Request
  - url
  ```
  http://localhost:8080/v1/seoul-metro/data/passengers/monthly/average
  ```
  - parameter
  ```
  {
    "year": 2019,        //조회 대상 연도
    "ascending": true,   //정렬기준(true:오름차순, false:내림차순 )
    "rankNum": 1        //출력갯수
  }
  ```  
- Response
  ```
    {
      "code": 200,
      "msg": "OK",
      "data": [
        {
          "stationName": "둔촌오륜",
          "passengerCount": 38808
        }
      ]
    }
  ```

#### 5. 월 인원수 최대 최소의 차이가 가장 큰 역 명을 출력하는 API 개발
- Request
  - url
  ```
  http://localhost:8080/v1/seoul-metro/data/passengers/monthly/difference
  ```
  - parameter
- Response

  ``` ```
    {
      "code": 200,
      "msg": "OK",
      "data": [
          {
            "stationName": "강남",
            "passengerCount": 1350567
          }
      ]
    }
  ```  
### 선택사항 - API 인증(Authentication)
#### 1. 회원가입 
- Request
  - url
  ```
  http://localhost:8080/v1/sign-up
  ```
  - parameter
  ```
  {
    "id": "suuny",
    "password": "test",
    "name": "0sun"
  }
  ```

- Request
  ```
  {
    "code": 200,
    "msg": "OK"
  }
  ```
#### 2. 로그인(토큰발급)
- Request
  - url
  ```
  http://localhost:8080/v1/sign-in
  ```
  - parameter
  ```
  {
    "id": "suuny",
    "password": "test",
  }
  ```

- Request
  ```
  {
    "code": 200,
    "msg": "OK",
    "data": {token}
  }
  ```

#### 3. 토큰갱신
- Request
  - url
  ```
  http://localhost:8080/v1/token-refresh
  ```
  - header
  ```
  "X-AUTH-TOKEN: {token}"
  ```
- Request
  ```
  {
    "code": 200,
    "msg": "OK",
    "data": {token}
  }
  ```

## 과제 해결
#### API설계
- RESTFUL API

#### API 구현

#### API 인증

#### DB 인덱싱
- DB INDEX
  - 파일은 연도별 파일로 해당 자료의 `입력(insert)`은 1회/연 발생으로 가정 
  - 자료의 `수정(update)` 및 `삭제(delete)` 는 거의 발생하지 않을 것으로 가정
  - 이후 주 요청은 `조회(read)`로 예상
  - 따라서 index를 추가해도 입력/수정/삭제의 성능 저하보다 조회의 성능 향상을 우선하여 인덱스를 생성

#### API 인증


## 시연
[http://localhost:8080](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config/)