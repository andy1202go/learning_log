/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.google.spreadsheet;

import com.alibaba.fastjson.JSON;
import com.example.demo.general.NotControllerResponseAdvice;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.DeleteRangeRequest;
import com.google.api.services.sheets.v4.model.GridRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author liangbo
 * @version V1.0
 * @Title: SheetTestController.java
 * @Package com.example.demo.google.spreadsheet
 * @Description
 * @date 2022 06-07 11:06.
 */
@RestController
@Slf4j
@RequestMapping("sheet")
public class SheetTestController {
    private static final String APPLICATION_NAME = "My Project 9187";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String SPREADSHEET_ID = "1Qw0Tr_Zc-5F4WssScyklhgTXFzE2egMKh5vqDocpfLQ";
    private static final String RANGE = "Sheet1!A:B";


    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        return GoogleCredential.fromStream(SheetsQuickstart.class.getResourceAsStream("winter-monolith-352508-fb01b93f11a5.json"))
                .createScoped(SCOPES);

//
//        // Load client secrets.
//        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("andy1202go@163.com");
    }

    @GetMapping("")
    @NotControllerResponseAdvice
    public String modifyTest() {
// Build a new authorized API client service.
        NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder()
                .build();
        try {
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            cleanSheet(service);
            writeSheetWithData(service);
            readValue(service);
        } catch (Exception e) {
            log.error("e=", e);
        }
        return "success";
    }


    private void cleanSheet(Sheets service) throws IOException {
        List<Request> requests = new ArrayList<>();
        //第1张表的A:B列全部内容
        requests.add(new Request()
                .setDeleteRange(new DeleteRangeRequest()
                        .setRange(new GridRange()
                                .setSheetId(0)
                                .setStartColumnIndex(0)
                                .setEndColumnIndex(2))
                        .setShiftDimension("COLUMNS")));
        BatchUpdateSpreadsheetRequest body =
                new BatchUpdateSpreadsheetRequest().setRequests(requests);
        BatchUpdateSpreadsheetResponse response = service.spreadsheets().batchUpdate(SPREADSHEET_ID, body).execute();
        log.info("clean response={}.", JSON.toJSONString(response));
    }

    private void writeSheetWithData(Sheets service) throws IOException {
        Date now = new Date();
        List<List<Object>> values = new ArrayList<>();
        List<Object> row1 = new ArrayList<>();
        row1.add("shit" + now);
        row1.add("happens" + now);
        values.add(row1);
        List<Object> row2 = new ArrayList<>();
        row2.add("shit2" + now);
        row2.add("happens2" + now);
        values.add(row2);
        updateValues(service, SPREADSHEET_ID, RANGE, "RAW", values);
    }

    private void readValue(Sheets service) throws IOException {
        Sheets.Spreadsheets spreadsheets = service.spreadsheets();
        List<String> ranges = spreadsheets.get(SPREADSHEET_ID).getRanges();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();
        List<List<Object>> values = response.getValues();
        log.info("readValue:{}", JSON.toJSONString(values));
    }

    /**
     * Sets values in a range of a spreadsheet.
     *
     * @param spreadsheetId    - Id of the spreadsheet.
     * @param range            - Range of cells of the spreadsheet.
     * @param valueInputOption - Determines how input data should be interpreted.
     * @param values           - List of rows of values to input.
     * @return spreadsheet with updated values
     * @throws IOException - if credentials file not found.
     */
    private static UpdateValuesResponse updateValues(Sheets service,
                                                     String spreadsheetId,
                                                     String range,
                                                     String valueInputOption,
                                                     List<List<Object>> values)
            throws IOException {
        UpdateValuesResponse result = null;
        try {
            // Updates the values in the specified range.
            ValueRange body = new ValueRange()
                    .setValues(values);
            result = service.spreadsheets().values().update(spreadsheetId, range, body)
                    .setValueInputOption(valueInputOption)
                    .execute();
            log.info("{} cells updated.", result.getUpdatedCells());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 404) {
                System.out.printf("Spreadsheet not found with id '%s'.\n", spreadsheetId);
            } else {
                throw e;
            }
        }
        return result;
    }
}
