package com.mtm.springboot.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.mtm.springboot.config.SampleConfiguration;
import com.mtm.springboot.domain.FuturesAndOptions;
import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.service.SampleService;
import com.mtm.springboot.util.FuturesAndOptionsUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

//Primary
@Service("sampleService2")
@Slf4j
public class SampleServiceImpl2 implements SampleService {

	@Autowired
	private SampleConfiguration config;

	@Autowired
	@Qualifier("sampleWebClient")
	private WebClient webClient;

	@Override
	public Mono<SampleData> saveData(SampleData request) {
		log.info("SampleServiceImpl2.saveData() -->");

		// TODO Auto-generated method stub
		return Mono.just(SampleData.builder().description("Hello").build());
	}

	@Override
	public Mono<Object> getData() {
		log.info("SampleServiceImpl2.getData() -->");
		log.info("Base URL:" + config.getBaseUrl());

		return webClient.get().uri(uriBuilder -> uriBuilder.path("/mtm/examples/123").build()).exchange()
				.flatMap(result -> {
					log.info("<-- SampleServiceImpl2.getData()");
					return result.bodyToMono(Object.class);
				});
	}

	@Override
	public Mono<ResponseEntity<Object>> updateData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> deleteData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> calculateFOCharges(MultipartFile file) {
		try {
			List<FuturesAndOptions> futuresAndOptionsData = readFileData(file, "Type", "Qty.", "Avg. price");
			for (FuturesAndOptions futuresAndOption : futuresAndOptionsData) {
				log.info("Order Avg Price: {}", futuresAndOption.getOrderAveragePrice());
			}
		} catch (IOException e) {
			log.error("Exception Occurred {}", e);
		}
		return null;
	}

	public static List<FuturesAndOptions> readFileData(MultipartFile file, String type, String quantity,
			String avgPrice) throws IOException {
		List<FuturesAndOptions> futuresAndOptionsData = new ArrayList<>();

		try (Workbook workBook = csvToXLSX(file.getInputStream())) {
			Sheet sheetAt = workBook.getSheetAt(0);
			for (Row row : sheetAt) {
				if (row.getRowNum() == 0) {
					continue;
				}
				futuresAndOptionsData.add(FuturesAndOptions.builder()
						.orderAveragePrice(FuturesAndOptionsUtil.getAveragePrice(row, avgPrice))
						.orderType(FuturesAndOptionsUtil.getOrderType(row, type))
						.orderQuantity(FuturesAndOptionsUtil.getOrderQuantity(row, quantity)).build());
			}
		}
		return futuresAndOptionsData;
	}

	public static XSSFWorkbook csvToXLSX(InputStream inputStream) throws IOException {
		XSSFWorkbook workBook = new XSSFWorkbook();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			Sheet sheet = workBook.createSheet("sheet1");
			String currentLine = null;
			int rowNum = 0;

			while ((currentLine = br.readLine()) != null) {
				String[] str = currentLine.split(",");
				rowNum++;
				Row currentRow = sheet.createRow(rowNum);
				for (int i = 0; i < str.length; i++) {
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}

			log.info("CSV file converted to the workbook");
			return workBook;
		} catch (Exception ex) {
			log.error("Exception while converting csv to xls {}", ex);
		} finally {
			if (Objects.nonNull(workBook)) {
				workBook.close();
			}
		}
		return workBook;
	}

}