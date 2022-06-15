///******************************************************************************
// * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
// * All Rights Reserved.
// * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
// *****************************************************************************/
//package com.example.demo.google.spreadsheet;
//
//import com.google.api.client.googleapis.json.GoogleJsonError;
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.services.sheets.v4.Sheets;
//import com.google.api.services.sheets.v4.SheetsScopes;
//import com.google.api.services.sheets.v4.model.ValueRange;
//import com.google.auth.http.HttpCredentialsAdapter;
//import com.google.auth.oauth2.GoogleCredentials;
//
//import java.io.IOException;
//import java.util.Collections;
//
///**
// * @author liangbo
// * @version V1.0
// * @Title: GetValues.java
// * @Package com.example.demo.google.spreadsheet
// * @Description
// * @date 2022 06-06 16:36.
// */
//public class GetValues {
//    /**
//     * Returns a range of values from a spreadsheet.
//     *
//     * @param spreadsheetId - Id of the spreadsheet.
//     * @param range - Range of cells of the spreadsheet.
//     * @return Values in the range
//     * @throws IOException - if credentials file not found.
//     */
//    public static ValueRange getValues(String spreadsheetId, String range) throws IOException {
//        /* Load pre-authorized user credentials from the environment.
//           TODO(developer) - See https://developers.google.com/identity for
//            guides on implementing OAuth2 for your application. */
//        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
//                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
//        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
//                credentials);
//
//        // Create the sheets API client
//        Sheets service = new Sheets.Builder(new NetHttpTransport(),
//                GsonFactory.getDefaultInstance(),
//                requestInitializer)
//                .setApplicationName("Sheets samples")
//                .build();
//
//        ValueRange result = null;
//        try {
//            // Gets the values of the cells in the specified range.
//            result = service.spreadsheets().values().get(spreadsheetId, range).execute();
//            int numRows = result.getValues() != null ? result.getValues().size() : 0;
//            System.out.printf("%d rows retrieved.", numRows);
//        } catch (GoogleJsonResponseException e) {
//            // TODO(developer) - handle error appropriately
//            GoogleJsonError error = e.getDetails();
//            if (error.getCode() == 404) {
//                System.out.printf("Spreadsheet not found with id '%s'.\n",spreadsheetId);
//            } else {
//                throw e;
//            }
//        }
//        return result;
//    }
//}
