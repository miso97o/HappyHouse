package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.ssafy.happyhouse.model.dao.AirMapper;
import com.ssafy.happyhouse.model.dto.Air;
import com.ssafy.happyhouse.model.dto.PageBean;

@Service
public class AirServiceImp implements AirService {

	@Autowired
	private AirMapper airMapper;
	private String station = "성동구";

//	@Override
//	public List<Air> getAir(String dong) {
//		// TODO Auto-generated method stub
//		return airMapper.getAir(dong);
//	}

	@Override
	public List<Air> getAir2(String gugun) throws Exception {
		List<Air> list = new ArrayList<Air>();

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=BpuYJ71ctvL1e5v7gM%2BNcw7nABuuPBrZCYMizcRcrHTKJMD2IRrVsJZQwFaGFH1YqBkyRNgz2SvL%2FcJ5h57abg%3D%3D"); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("xml", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("5", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("stationName", "UTF-8") + "="
				+ URLEncoder.encode(gugun, "UTF-8")); /* 측정소 이름 */
		urlBuilder.append("&" + URLEncoder.encode("dataTerm", "UTF-8") + "="
				+ URLEncoder.encode("3MONTH", "UTF-8")); /* 요청 데이터기간(1일: DAILY, 1개월: MONTH, 3개월: 3MONTH) */
		urlBuilder.append(
				"&" + URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /* 버전별 상세 결과 참고 */
		System.out.println(urlBuilder);
		URL url = new URL(urlBuilder.toString());
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
			Air air = new Air();
			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);

				air.setStation(gugun);

				if (node.getNodeName().equals("pm10Value")) {
					// System.out.println("미세먼지 농도 : " + node.getTextContent());
					if (!node.getTextContent().equals("-"))
						air.setPollution(Integer.parseInt(node.getTextContent()));
				}

				if (node.getNodeName().equals("dataTime")) {
					// System.out.println("측정날짜 : " + node.getTextContent());
					air.setDate(node.getTextContent());
				}

			}
			System.out.println(air.toString());
			list.add(air);
		}

		rd.close();
		conn.disconnect();

		return list;
	}
	
	@Override
	public boolean insertAir() throws Exception {

		List<Air> list = new ArrayList<Air>();

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=BpuYJ71ctvL1e5v7gM%2BNcw7nABuuPBrZCYMizcRcrHTKJMD2IRrVsJZQwFaGFH1YqBkyRNgz2SvL%2FcJ5h57abg%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("xml", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("stationName", "UTF-8") + "="
				+ URLEncoder.encode(station, "UTF-8")); /* 측정소 이름 */
		urlBuilder.append("&" + URLEncoder.encode("dataTerm", "UTF-8") + "="
				+ URLEncoder.encode("3MONTH", "UTF-8")); /* 요청 데이터기간(1일: DAILY, 1개월: MONTH, 3개월: 3MONTH) */
		urlBuilder.append(
				"&" + URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /* 버전별 상세 결과 참고 */

		URL url = new URL(urlBuilder.toString());
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
			Air air = new Air();
			for (int j = 0; j < child.getLength(); j++) {
				Node node = child.item(j);

				air.setStation(station);

				if (node.getNodeName().equals("pm10Value")) {
					// System.out.println("미세먼지 농도 : " + node.getTextContent());
					if (!node.getTextContent().equals("-"))
						air.setPollution(Integer.parseInt(node.getTextContent()));
				}

				if (node.getNodeName().equals("dataTime")) {
					// System.out.println("측정날짜 : " + node.getTextContent());
					air.setDate(node.getTextContent());
				}

			}
			System.out.println(air.toString());
			airMapper.insertAir(air);
		}

		rd.close();
		conn.disconnect();

		return true;

	}

}
