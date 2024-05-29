package org.nsop.jusodemo.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nsop.jusodemo.service.JusoDataService;
import org.nsop.jusodemo.service.JusoDataVO;
import jakarta.annotation.Resource;
import org.nsop.jusodemo.service.MergeJusoDataVO;
import org.nsop.jusodemo.service.VwJusoDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


@Controller
public class JusoDataController {

    @Resource(name = "jusoDataService")
    private JusoDataService jusoDataService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/insertMergeJusoData")
    @ResponseBody
    public ResponseEntity<String> insertMergeJusoData() {
        try {
            List<MergeJusoDataVO> mergeJusoDataList = getMergeJusoDataList();
            jusoDataService.insertMergeVWJusoList(mergeJusoDataList);

            return ResponseEntity.ok("Data inserted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/jusoResult")
    @ResponseBody
    private List<VwJusoDataVO> juso() throws IOException {
        List<VwJusoDataVO> mergeJusoDataList = new ArrayList<>();
        List<JusoDataVO> jusoDataList = jusoDataService.selectJusoList();

        // application.properties에서 값을 읽기
        String vworldKey = env.getProperty("vworld.api.key");
        String query = null;
        String category = null;

        // vwJusoList에서 데이터를 MergeJusoDataVO에 추가
        for (JusoDataVO jusoData : jusoDataList) {
            String lnmadr = jusoData.getLnmadr();
            String rdnmadr = jusoData.getRdnmadr();

            if (rdnmadr != null && !rdnmadr.isEmpty()) {
                query = rdnmadr;
                category = "road";
            } else if (lnmadr != null && !lnmadr.isEmpty()) {
                query = lnmadr;
                category = "parcel";
            } else {
                // 둘 다 없으면 해당 연산을 스킵
                continue;
            }

            // lnmadr 필드로 원하는 연산 수행
            List<VwJusoDataVO> vwJusoList = vwDataList(query, category, vworldKey);
            mergeJusoDataList.addAll(vwJusoList);
        }

        return mergeJusoDataList;
    }

    private List<MergeJusoDataVO> getMergeJusoDataList() throws IOException {

        List<MergeJusoDataVO> mergeJusoDataList = new ArrayList<>();
        List<JusoDataVO> jusoDataList = jusoDataService.selectJusoList();

        // application.properties에서 값을 읽기
        String vworldKey = env.getProperty("vworld.api.key");
        String query = null;
        String category = null;

        // jusoDataList에 들어있는 JusoDataVO 객체들의 lnmadr 필드를 가져와 연산을 수행
        for (JusoDataVO jusoData : jusoDataList) {
            String lnmadr = jusoData.getLnmadr();
            String rdnmadr = jusoData.getRdnmadr();

            if (rdnmadr != null && !rdnmadr.isEmpty()) {
                query = rdnmadr;
                category = "road";
            } else if (lnmadr != null && !lnmadr.isEmpty()) {
                query = lnmadr;
                category = "parcel";
            } else {
                // 둘 다 없으면 해당 연산을 스킵
                continue;
            }

            // lnmadr 필드로 원하는 연산 수행
            List<VwJusoDataVO> vwJusoList = vwDataList(query, category, vworldKey);

            // MergeJusoDataVO 객체 생성 및 데이터 매핑
            MergeJusoDataVO mergeJusoData = new MergeJusoDataVO();
            mergeJusoData.setSeCd(jusoData.getSeCd());
            mergeJusoData.setGpCd(jusoData.getGpCd());
            mergeJusoData.setFcltyCd(jusoData.getFcltyCd());
            mergeJusoData.setFcltyNm(jusoData.getFcltyNm());
            mergeJusoData.setLnmadr(jusoData.getLnmadr());
            mergeJusoData.setRdnmadr(jusoData.getRdnmadr());
            mergeJusoData.setLa(jusoData.getLa());
            mergeJusoData.setLo(jusoData.getLo());

            // vwJusoList에서 데이터를 MergeJusoDataVO에 추가
            for (VwJusoDataVO vwJusoData : vwJusoList) {
                // vwJusoData에서 가져온 데이터를 MergeJusoDataVO에 추가하거나 업데이트
                // 예를 들어,
                 mergeJusoData.setVwId(vwJusoData.getVwId());
                 mergeJusoData.setVwLnmadr(vwJusoData.getVwLnmadr());
                 mergeJusoData.setVwRdnmadr(vwJusoData.getVwRdnmadr());
                 mergeJusoData.setVwLa(vwJusoData.getVwLa());
                 mergeJusoData.setVwLo(vwJusoData.getVwLo());
                 mergeJusoData.setVwFcltyNm(vwJusoData.getVwFcltyNm());
                 mergeJusoData.setVwYn(vwJusoData.getVwYn());
                // 와 같은 방식으로 추가하거나 업데이트해주세요.
            }

            // mergeJusoDataList에 MergeJusoDataVO 객체 추가
            mergeJusoDataList.add(mergeJusoData);

        }

        return mergeJusoDataList;
    }

    private List<VwJusoDataVO> vwDataList(String query, String category, String apiKey) throws IOException {
        // lnmadr이 null인 경우에는 메서드를 실행하지 않고 패스한다.
        if (query == null) {
            System.out.println("lnmadr is null. Skipping vwDataList method.");
            return Collections.emptyList(); // 빈 리스트 반환
        }else {
            // query가 null이 아닌 경우에는 메서드를 실행한다.
            String apiUrl = "https://api.vworld.kr/req/search?" +
                    "service=search&" +
                    "request=search&" +
                    "version=2.0&" +
                    "&size=10" +
                    "&page=1" +
                    "&query=" + URLEncoder.encode(query, "UTF-8") +
                    "&type=address" + // 장소 : place, 주소 : address, 행정구역 : district, 도로명 : road
                    "&category=" + URLEncoder.encode(category, "UTF-8") +
                    "&format=json" +
                    "&errorformat=json" +
                    "&key=" + URLEncoder.encode(apiKey, "UTF-8");

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 요청 메소드 설정
            conn.setRequestMethod("GET");

            // 응답 본문 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 연결 닫기
            conn.disconnect();

            System.out.println(query);
            System.out.println(category);

            // 여기서 응답 데이터를 처리하고 적절한 형식으로 변환하여 반환하면 됩니다.
            return processApiResponse(response.toString(), category);
        }
    }

    private List<VwJusoDataVO> processApiResponse(String jsonResponse, String category) {
        List<VwJusoDataVO> jusoDataList = new ArrayList<>();

        try {
            // JSON 문자열을 JSONObject로 변환
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // response 객체 가져오기
            JSONObject response = jsonObject.getJSONObject("response");

            // status 값 가져오기
            String status = response.getString("status");

            // status가 "OK"인 경우에만 처리
            if ("OK".equals(status)) {
                // result 객체 가져오기
                JSONObject result = response.getJSONObject("result");

                // items 배열 가져오기
                JSONArray items = result.getJSONArray("items");

                // items 배열 순회
                for (int i = 0; i < items.length(); i++) {
                    // i가 0일 때만 처리
                    if (i == 0) {
                        JSONObject item = items.getJSONObject(i);

                        // 필요한 데이터 추출
                        String id = item.getString("id");
                        String parcel = item.getJSONObject("address").getString("parcel");
                        String road = item.getJSONObject("address").getString("road");
                        String bldnm = item.getJSONObject("address").getString("bldnm");
                        String x = item.getJSONObject("point").getString("x");
                        String y = item.getJSONObject("point").getString("y");

                        // 주소 체계에 따라 주소 변경
                        if ("road".equals(category) && road != null && !road.isEmpty()) {
                            String prefix = extractPrefix(road);
                            System.out.println(prefix);
                            if (prefix != null) {
                                parcel = prefix + parcel;
                            }
                        } else if ("parcel".equals(category) && parcel != null && !parcel.isEmpty()) {
                            String prefix = extractPrefix(parcel);
                            if (prefix != null) {
                                road = prefix + road;
                            }
                        } else {
                            // road 또는 parcel이 없거나 빈 문자열이면 패스
                            continue;
                        }

                        // JusoDataVO 객체 생성하여 리스트에 추가
                        VwJusoDataVO jusoData = new VwJusoDataVO();

                        jusoData.setVwId(id);
                        jusoData.setVwLnmadr(parcel);
                        jusoData.setVwRdnmadr(road);
                        jusoData.setVwFcltyNm(bldnm);
                        jusoData.setVwLa(y);
                        jusoData.setVwLo(x);
                        jusoData.setVwYn("Y");

                        jusoDataList.add(jusoData);
                    } else {
                        // i가 0이 아닌 경우에는 스킵
                        continue;
                    }
                }
            }else {
                // JusoDataVO 객체 생성하여 리스트에 추가
                VwJusoDataVO jusoData = new VwJusoDataVO();

                jusoData.setVwYn("N");

                jusoDataList.add(jusoData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // 예외 처리 필요
        }

        return jusoDataList;
    }

    private String extractPrefix(String address) {
        if (address == null || address.isEmpty()) {
            return null;
        }

        String[] addressParts = address.split(" ");

        for (String part : addressParts) {
            if (part.length() > 1) {
                System.out.print(part);
            }
        }


        int prefixIndex = -1;

        // 우선적으로 읍이나 면을 찾기
        for (int i = addressParts.length - 1; i >= 0; i--) {
            if (addressParts[i].endsWith("읍") || addressParts[i].endsWith("면")) {
                prefixIndex = i;
                break;
            }
        }

        // 읍이나 면이 없는 경우, 구를 찾기
        if (prefixIndex == -1) {
            for (int i = addressParts.length - 1; i >= 0; i--) {
                if (addressParts[i].endsWith("구")) {
                    prefixIndex = i;
                    break;
                }
            }
        }

        // 구가 없는 경우, 시를 찾기
        if (prefixIndex == -1) {
            for (int i = addressParts.length - 1; i >= 0; i--) {
                if (addressParts[i].endsWith("시")) {
                    prefixIndex = i;
                    break;
                }
            }
        }

        // 접두사를 찾지 못한 경우 null 반환
        if (prefixIndex == -1) {
            return null;
        }

        // 접두사를 찾은 경우, 해당 위치까지의 문자열을 반환
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i <= prefixIndex; i++) {
            prefix.append(addressParts[i]).append(" ");
        }

        return prefix.toString();
    }
}
