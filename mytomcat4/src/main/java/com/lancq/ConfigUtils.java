package com.lancq;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lancq
 * @Description 利用dom4j解析web.xml配置
 * @Date 2018/9/13
 **/
public class ConfigUtils {
    public static Map<String,String> getClassName(String path) throws Exception {
        Map<String,String> handlerMap = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        String rootPath = System.getProperty("user.dir");
        File file = new File(rootPath+"\\mytomcat4\\src\\main\\webapp\\"+path);
        Document document = reader.read(file);
        //得到根元素
        Element rootElement = document.getRootElement();
        List<Element> childElements = rootElement.elements();
        for(Element element : childElements){
            //key -> /login.action
            String key = element.element("url-pattern").getText();
            //value ->
            String value = element.element("servlet-class").getText();
            handlerMap.put(key,value);

        }
        return handlerMap;
    }
}
