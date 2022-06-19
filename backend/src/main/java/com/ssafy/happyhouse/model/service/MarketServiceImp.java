package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ssafy.happyhouse.model.dao.MarketMapper;
import com.ssafy.happyhouse.model.dto.Market;
import com.ssafy.happyhouse.model.dto.PageBean;

@Service
public class MarketServiceImp implements MarketService {

	@Autowired
	private MarketMapper marketMapper;
	
	@Override
	public List<Market> getMarket(String dong) {
		// TODO Auto-generated method stub
		return marketMapper.getMarket(dong);
	}

	@Override
	public boolean insertMarket() throws Exception {
		// TODO Auto-generated method stub
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=BpuYJ71ctvL1e5v7gM%2BNcw7nABuuPBrZCYMizcRcrHTKJMD2IRrVsJZQwFaGFH1YqBkyRNgz2SvL%2FcJ5h57abg%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("divId", "UTF-8") + "="
				+ URLEncoder.encode("ctprvnCd", "UTF-8")); /* 시도는 ctprvnCd, 시군구는 signguCd, 행정동은 adongCd를 사용 */
		//** 행정동코드와 법정동코드는 다름, 주의
		urlBuilder.append("&" + URLEncoder.encode("key", "UTF-8") + "="
				+ URLEncoder.encode("11", "UTF-8")); /* 시도는 시도코드값, 시군구는 시군구코드값, 행정동은 행정동코드값을 사용 */
//        urlBuilder.append("&" + URLEncoder.encode("indsLclsCd","UTF-8") + "=" + URLEncoder.encode("Q", "UTF-8")); /* 입력된 대분류 업종에 해당하는 것만 조회*/
//        urlBuilder.append("&" + URLEncoder.encode("indsMclsCd","UTF-8") + "=" + URLEncoder.encode("Q12", "UTF-8")); /* 입력된 중분류 업종에 해당하는 것만 조회*/
//        urlBuilder.append("&" + URLEncoder.encode("indsSclsCd","UTF-8") + "=" + URLEncoder.encode("Q12A01", "UTF-8")); /* 입력된 소분류 업종에 해당하는 것만 조회*/
		urlBuilder.append(
				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml ", "UTF-8")); /* xml / json */
		URL url = new URL(urlBuilder.toString());

		System.out.println("url: " + url);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;

		String result = sb.toString();

		InputSource is = new InputSource(new StringReader(result));
		builder = factory.newDocumentBuilder();
		doc = (Document) builder.parse(is);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		// XPathExpression expr = xpath.compile("/response/body/items/item");
		XPathExpression expr = xpath.compile("//items/item");
		NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			NodeList child = nodeList.item(i).getChildNodes();
			System.out.println(child.toString());
			Market market = new Market();
			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);

				if (node.getNodeName().equals("bizesNm")) {
					// System.out.println("상호명 : " + node.getTextContent());
					market.setMarketName(node.getTextContent());
				}

				if (node.getNodeName().equals("indsLclsNm")) {
					// System.out.println("상권업종대분류명 : " + node.getTextContent());
					market.setMarketKinds(node.getTextContent());
					//음식 , 이/미용/건강, 소매, 생활서비스 크게 4분류
				}

				if (node.getNodeName().equals("ldongCd")) {
					// System.out.println("법정동코드 : " + node.getTextContent());
					market.setDongCode(node.getTextContent());
				}

				if (node.getNodeName().equals("ldongNm")) {
					// System.out.println("법정동명 : " + node.getTextContent());
					market.setDongName(node.getTextContent());
				}

				if (node.getNodeName().equals("lat")) {
					// System.out.println("위도 : " + node.getTextContent());
					market.setLat(node.getTextContent());
				}
				
				if (node.getNodeName().equals("lon")) {
					// System.out.println("경도 : " + node.getTextContent());
					market.setLng(node.getTextContent());
				}

			}
			System.out.println(market.toString());
			marketMapper.insertMarket(market);
		}

		rd.close();
		conn.disconnect();
		
		
		return true;
	}

}
