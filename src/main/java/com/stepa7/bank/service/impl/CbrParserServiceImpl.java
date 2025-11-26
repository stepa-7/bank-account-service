package com.stepa7.bank.service.impl;

import com.stepa7.bank.service.CbrParserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CbrParserServiceImpl implements CbrParserService {
    @Override
    public Map<String, BigDecimal> parse(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            NodeList valutes = doc.getElementsByTagName("Valute");
            Map<String, BigDecimal> map = new HashMap<>();
            for (int i = 0; i < valutes.getLength(); i++) {
                Element val = (Element) valutes.item(i);
                String code = val.getElementsByTagName("CharCode").item(0).getTextContent();
                String value = val.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.');
                String nominal = val.getElementsByTagName("Nominal").item(0).getTextContent();
                BigDecimal valBD = new BigDecimal(value);
                BigDecimal nomBD = new BigDecimal(nominal);
                BigDecimal rate = valBD.divide(nomBD);
                map.put(code, rate);
            }
            return map;
        } catch (Exception e) {
            log.error("Failed to parse CBR xml", e);
            throw new RuntimeException("Failed to parse CBR xml", e);
        }
    }
}