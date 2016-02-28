package com.cheng.zenofdesignpatterns.extension.mvcframework.cheng.helper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 */
public class XmlHelper {

    // 单例对象
    private static Document doc;
    // 默认的Action的XPATH路径
    private final static String DEFAULT_ACTION_PATH = "/mvc/action";

    public XmlHelper() {
        this("c:/ActionConfig.xml");
    }

    // 初始化一个XML DOCUMENT对象
    public XmlHelper(String xmlFilePath) {
        try {
            InputStream xmlStream = new FileInputStream(xmlFilePath);
            doc = (new SAXReader()).read(xmlStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 根据Action名称查找出节点
    @SuppressWarnings("unchecked")
    public Element getNodeByActionName(String actionName) {
        // 取得所有节点
        List<Node> nodeList = doc.selectNodes(DEFAULT_ACTION_PATH);
        // 遍历所有的Node节点
        System.out.println(nodeList.size());
        for (Node node : nodeList) {
            Element e = (Element) node;
            Attribute a = e.attribute("name");
            // 找到名字匹配的action
            if (a.getText().equals(actionName)) {
                return e;
            }
        }
        return null;
    }

    // 定义个访问者
    public static void main(String[] args) {
        XmlHelper xmlhelper = new XmlHelper();
        Element e = xmlhelper.getNodeByActionName("loginAction");
        ActionNode node = new XmlActionNode(e);
        System.out.println(node.getView("success"));
    }
}
