package com.nice.assignment.metro.service;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

@Service
public class ParseCsvService {
    public List<Record> parseCsvWithHeader(String filePath){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        return new CsvParser(settings).parseAllRecords(new File(filePath), Charset.forName("EUC-KR"));
    }
}
