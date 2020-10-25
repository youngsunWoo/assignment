
## CSV Parser 라이브러리 비교

### 라이브러리 선정기준 
- 안전성 
  - 사용량이 가장 많은 라이브러리 Top 10중에 선정
  - 2020이전 릴리즈 되지 않은 라이브러리 제외
- 성능
  - JDK8 기준 가장 처리속도가 빠른 라이브러리 Top 10 선정

### 사용량 Top 10 
- 최신 릴리즈 버전이 2020 이전인 경우 제외

|순위|라이브러리 명|사용량|최신 릴리즈|
|--|-----|---|---|
|1 |Apache Commons CSV            | 763 usages |2020.02|
|2 | ~~OpenCSV~~                  | 608 usages |2011.07|
|3 |OpenCSV                       | 316 usages |2020.05|
|4 |~~JDBI~~                      | 200 usages |2017.01|
|5 |Data CSV                      | 159 usages |2020.02|
|6 |Jackson Dataformat CSV        | 134 usages |2020.10|
|7 |~~Super CSV Core~~            | 132 usages |2015.10|
|8 |**`UniVocity Parsers`**       | 125 usages |2020.08|
|9 |~~Scala CSV~~                 | 85 usages  |2019.10|
|10|~~Java CVS Reader and Writer~~| 70 usages  |2008.02|
(참고 자료) https://mvnrepository.com/search?q=csv&sort=popular

### 수행 속도 Top 10 
- 성능측정 조건 
  - JDK 8 기준
  - 3,173,958 rows 파싱
  - 모든 dafa에 "가 존재하는 경우  

|순위|라이브러리|평균속도|	성능대비 % |최소수행시간 |최대수행시간|
|--|------|-----|---|---|---|
|1|**`uniVocity CSV parser`**|	855 ms|	Best time!|	839 ms|	870 ms
|2|SimpleFlatMapper CSV parser|	964 ms|	12%	|959 ms|	971 ms
|3|Jackson CSV parser|	1023 ms|	19%	|1009 ms|	1058 ms
|4|Diergo Easy CSV Streamable|	1385 ms|	61%	|1378 ms|	1394 ms
|5|Product Collections parser|	1388 ms|	62%	|1386 ms|	1391 ms
|6|Java CSV Parser|	1642 ms|	92%	|1635 ms|	1650 ms
|7|JCSV Parser|	1756 ms|	105%	|1739 ms|	1765 ms
|8|Simple CSV parser|	1813 ms	|112%	| 1804 ms |	1841 ms
|9|Gen-Java CSV|	1954 ms|	128%	|1950 ms|	1961 ms
|10|SuperCSV|	1989 ms|	132%	|1975 ms|	2001 ms

### 선정 결과
**`UniVocity Parsers**`**