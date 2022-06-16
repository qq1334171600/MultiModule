package com.tsign.aienterprise.camera.hk;

import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import io.reactivex.Observable;

public class XMLUtil {
        /**
         * DOM解析
         * 把文档中的所有元素，按照其出现的层次关系，解析成一个个Node对象(节点)。
         * 缺点是消耗大量的内存。
         * @param xmlFilePath 文件
         * @return Document
         */
        public static Document loadWithDom(String xmlFilePath) {
            try {
                File file = new File(xmlFilePath);
                if (!file.exists()) {
                    throw new RuntimeException("not find file:" + xmlFilePath);
                }
                else {
                    InputStream inputStream = new FileInputStream(file);
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(inputStream);
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return document;
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                return null;
            }
        }
        public static Observable<Document> loadWithDomRx(String xmlFilePath) {
            return Observable.just(loadWithDom(xmlFilePath));
        }
        /**
         * 保存修改后的Doc
         * http://blog.csdn.net/franksun1991/article/details/41869521
         * @param doc doc
         * @param saveXmlFilePath 路径
         * @return 是否成功
         */
        public static boolean saveXmlWithDom(Document doc,String saveXmlFilePath) {
            if (doc==null || saveXmlFilePath==null || saveXmlFilePath.isEmpty())
                return false;
            try {
                //将内存中的Dom保存到文件
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer();
                //设置输出的xml的格式，utf-8
                transformer.setOutputProperty("encoding", "utf-8");
                transformer.setOutputProperty("version",doc.getXmlVersion());
                DOMSource source = new DOMSource(doc);
                //打开输出流
                File file = new File(saveXmlFilePath);
                if (!file.exists())
                    Log.e("XmlUtils","saveXmlWithDom,createNewFile:"+file.createNewFile());
                OutputStream outputStream = new FileOutputStream(file);
                //xml的存放位置
                StreamResult src = new StreamResult(outputStream);
                transformer.transform(source, src);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public static Observable<Boolean> saveXmlWithDomRx(Document doc,String saveXmlFilePath) {
            return Observable.just(saveXmlWithDom(doc, saveXmlFilePath));
        }

}
