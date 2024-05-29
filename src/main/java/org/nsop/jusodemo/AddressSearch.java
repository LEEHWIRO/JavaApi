package org.nsop.jusodemo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

public class AddressSearch {

    public static void main(String[] args) {
        // Properties 객체 생성
        Properties properties = new Properties();


        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {

            // properties 파일 로드
            properties.load(input);

            //속성값 읽기
            String vworldKey = properties.getProperty("vworld.api.key");

//            // Scanner 객체 생성 (System.in을 사용하여 콘솔로부터 입력 받음)
//            Scanner scanner = new Scanner(System.in);
//
//            // 사용자에게 입력을 요청하는 메시지 출력
//            System.out.print("주소를 입력해주세요.>>> ");
//
//            // 문자열 입력 받기 (엔터키를 누를 때까지)
//            String inputString = scanner.nextLine();
//
//            // 입력 받은 문자열 출력
//            System.out.println("입력하신 주소 : " + inputString + "로 검색을 시작합니다.");
//
//            // Scanner 객체를 닫음 (권장 사항)
//            scanner.close();
            String keyword = "충청남도 아산시 염치읍 현대로 1077"; // 검색어 설정
            String currentPage = "1"; // 현재 페이지
            String countPerPage = "10"; // 페이지 당 결과 수
            String resultType = "json"; // 결과 형식
            String firstSort = "none"; // 첫 번째 정렬 기준

            // V-World API KEY : 137815AA-1D1C-3AE3-BB62-0B62464DD2F0

            // 지번 있으면 Y/ 없으면 N

            // select * from tb_p100m02;


            // URL 생성

            String urlStr = "http://192.168.10.101:8983/app/search/addrSearchApi.do" +
                    "?keyword=" + URLEncoder.encode(keyword, "UTF-8") +
                    "&currentPage=" + URLEncoder.encode(currentPage, "UTF-8") +
                    "&countPerPage=" + URLEncoder.encode(countPerPage, "UTF-8") +
                    "&resultType=" + URLEncoder.encode(resultType, "UTF-8") +
                    "&firstSort=" + URLEncoder.encode(firstSort, "UTF-8");

            // Vworld 지도 검색 API
            String vworldUrl = "https://api.vworld.kr/req/search?" +
                    "service=search&" +
                    "request=search&" +
                    "version=2.0&" +
                    "&size=10" +
                    "&page=1" +
                    "&query=" + URLEncoder.encode(keyword, "UTF-8") +
                    "&type=" + URLEncoder.encode("address", "UTF-8") + // 장소 : place, 주소 : address, 행정구역 : district, 도로명 : road
                    "&category=road" +
                    "&format=json" +
                    "&errorformat=json" +
                    "&key=" + URLEncoder.encode(vworldKey, "UTF-8");

            // Vworld Geocoder API
            String geocoderUrl = "https://api.vworld.kr/req/address?service=address&request=getcoord&format=json&" +
                "type=road" +
                "&address=" + URLEncoder.encode(keyword, "UTF-8") +
                "&key=" + URLEncoder.encode(vworldKey, "UTF-8");

            URL url = new URL(vworldUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 요청 메소드 설정
            conn.setRequestMethod("POST");

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();

            // 응답 본문 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 응답 출력
            System.out.println(response.toString());
//            JsonPerseVworld(response.toString());

            // 연결 닫기
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JsonParsed(String jsonResponse) {
        // JSON 파싱
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject results = jsonObject.getJSONObject("results");
        JSONObject common = results.getJSONObject("common");
        JSONArray jusoArray = results.getJSONArray("juso");
        JSONObject juso = jusoArray.getJSONObject(0);

        // Common Information 출력
        System.out.println("공통 정보:");
        System.out.println("  총 개수: " + common.getString("totalCount"));
        System.out.println("  현재 페이지: " + common.getString("currentPage"));
        System.out.println("  페이지당 개수: " + common.getString("countPerPage"));
        System.out.println("  오류 코드: " + common.getString("errorCode"));
        System.out.println("  오류 메시지: " + common.getString("errorMessage"));

        // Address Information 출력
        System.out.println("\n주소 정보:");
        System.out.println("  도로명 주소: " + juso.getString("roadAddr"));
        System.out.println("  도로명 주소 (부분 1): " + juso.getString("roadAddrPart1"));
        System.out.println("  도로명 주소 (부분 2): " + (juso.getString("roadAddrPart2").isEmpty() ? "(없음)" : juso.getString("roadAddrPart2")));
        System.out.println("  지번 주소: " + juso.getString("jibunAddr"));
        System.out.println("  영문 주소: " + juso.getString("engAddr"));
        System.out.println("  우편번호: " + juso.getString("zipNo"));
        System.out.println("  행정 코드: " + juso.getString("admCd"));
        System.out.println("  도로명 관리 번호: " + juso.getString("rdMgtSn"));
        System.out.println("  건물 관리 번호: " + juso.getString("bdMgtSn"));
        System.out.println("  상세 건물명 리스트: " + (juso.getString("detBdNmList").isEmpty() ? "(없음)" : juso.getString("detBdNmList")));
        System.out.println("  건물명: " + (juso.getString("bdNm").isEmpty() ? "(없음)" : juso.getString("bdNm")));
        System.out.println("  시도명: " + juso.getString("siNm"));
        System.out.println("  시군구명: " + juso.getString("sggNm"));
        System.out.println("  읍면동명: " + juso.getString("emdNm"));
        System.out.println("  법정리명: " + juso.getString("liNm"));
        System.out.println("  도로명: " + juso.getString("rn"));
        System.out.println("  건물 본번: " + juso.getString("buldMnnm"));
        System.out.println("  건물 부번: " + juso.getString("buldSlno"));
        System.out.println("  지번 본번: " + juso.getString("lnbrMnnm"));
        System.out.println("  지번 부번: " + juso.getString("lnbrSlno"));
        System.out.println("  건물 구분 코드: " + juso.getString("bdKdcd"));
        System.out.println("  지하 여부: " + juso.getString("udrtYn"));
        System.out.println("  산 여부: " + juso.getString("mtYn"));
        System.out.println("  역사 여부: " + juso.getString("hstryYn"));
        System.out.println("  관련 지번: " + (juso.getString("relJibun").isEmpty() ? "(없음)" : juso.getString("relJibun")));
        System.out.println("  행정동명: " + juso.getString("hemdNm"));
    }

    public static void JsonPerseVworld(String jsonString) {
        // JSON 문자열을 JSONObject로 변환
        JSONObject jsonObject = new JSONObject(jsonString);

        // response 객체 가져오기
        JSONObject response = jsonObject.getJSONObject("response");

        // result 객체 가져오기
        JSONObject result = response.getJSONObject("result");
        String crs = result.getString("crs");
        String type = result.getString("type");

        System.out.println("좌표계: " + crs);
        System.out.println("검색 대상: " + type);

        // items 배열 가져오기
        JSONArray items = result.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String id = item.getString("id");

            JSONObject address = item.getJSONObject("address");
            String zipcode = address.getString("zipcode");
            String category = address.getString("category");
            String road = address.getString("road");
            String parcel = address.getString("parcel");
            String bldnm = address.getString("bldnm");

            JSONObject point = item.getJSONObject("point");
            String x = point.getString("x");
            String y = point.getString("y");

            System.out.println("아이디: " + id);
            System.out.println("우편번호: " + zipcode);
            System.out.println("카테고리: " + category);
            System.out.println("도로명: " + road);
            System.out.println("지번: " + parcel);
            System.out.println("건물명: " + bldnm);
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
        }
    }
}