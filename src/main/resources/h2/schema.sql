
DROP TABLE `MONTHLY_PASSENGER_INFO` IF EXISTS;

CREATE TABLE `MONTHLY_PASSENGER_INFO` (
  `seq`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `year`                 BIGINT NOT NULL COMMENT '연도',
  `month`                BIGINT NOT NULL COMMENT '월',
  `station_no`           BIGINT NOT NULL COMMENT '역번호',
  `line`                 VARCHAR(20)  NOT NULL AUTO_INCREMENT COMMENT '호선',
  `station_name`         VARCHAR(50) NOT NULL COMMENT '역명',
  `passengers_count`     BIGINT NOT NULL AUTO_INCREMENT COMMENT '승객수',
  PRIMARY KEY (`seq`),
  UNIQUE (`year`,`month`,`station_no`)
);

