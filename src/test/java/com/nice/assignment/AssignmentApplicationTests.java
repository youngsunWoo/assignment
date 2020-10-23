//package com.nice.assignment;
//
//import com.nice.assignment.dto.PassengerTotalDto;
//import com.univocity.parsers.common.record.Record;
//import com.univocity.parsers.csv.CsvParser;
//import com.univocity.parsers.csv.CsvParserSettings;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//class AssignmentApplicationTests {
//
//    @Test
//    void contextLoads() throws FileNotFoundException {
//
//        String fileName = "src/main/resources/test.csv";
//
//        CsvParserSettings settings = new CsvParserSettings();
//        settings.setHeaderExtractionEnabled(true);
//
//        CsvParser parser = new CsvParser(settings);
//        List<Record> allRecords = parser.parseAllRecords(new File(fileName), Charset.forName("EUC-KR"));
//
//        List<PassengerTotalDto> list = new ArrayList<>();
//        for (Record record : allRecords) {
//            for (int i=1;i<13;i++){
//                list.add(PassengerTotalDto.builder()
//                        .year(2019L)
//                        .line(record.getString("호선"))
//                        .stationName(record.getString("역명"))
//                        .stationNo(record.getLong("역번호"))
//                        .month((long)i)
//                        .passengerCount(
//                                Long.parseLong(
//                                record.getString(i+"월").replace(",",""))
//                        )
//                        .build()
//                );
//            }
//        }
//
//        for (PassengerTotalDto passengerTotalDto : list) {
//            System.out.println(passengerTotalDto.toString());
//        }
//    }
//
//
//}
