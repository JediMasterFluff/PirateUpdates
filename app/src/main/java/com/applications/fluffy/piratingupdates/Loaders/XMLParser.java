package com.applications.fluffy.piratingupdates.Loaders;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.applications.fluffy.piratingupdates.Objects.XMLParserBean;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fluffy on 10/02/17.
 */

public class XMLParser extends AsyncTask<Void, Void, Void> {

    XMLParserBean objBean;
    Vector<XMLParserBean> vectParse;

    int mediaThumbnailCount;
    boolean urlflag;
    int count = 0;

    public XMLParser() {
        try {

            vectParse = new Vector<XMLParserBean>();
            URL url = new URL("http://news.yahoo.com/rss/politics");
            URLConnection con = url.openConnection();

            System.out.println("Connection is : " + con);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            System.out.println("Reader :" + reader);

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            System.out.println();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    objBean = new XMLParserBean();
                    vectParse.add(objBean);

                    objBean.title = getTagValue("title", eElement);
                    objBean.description = getTagValue("description", eElement);
                    String noHTMLString = objBean.description.replaceAll("\\<.*?\\>", "");
                    objBean.description=noHTMLString;
                    objBean.link = getTagValue("link", eElement);
                    objBean.pubdate = getTagValue("pubDate", eElement);

                }
            }

            for (int index1 = 0; index1 < vectParse.size(); index1++) {
                XMLParserBean ObjNB = (XMLParserBean) vectParse
                        .get(index1);

                System.out.println("Item No : " + index1);
                System.out.println();

                System.out.println("Title is : " + ObjNB.title);
                System.out.println("Description is : " + ObjNB.description);
                System.out.println("Link is : " + ObjNB.link);
                System.out.println("Pubdate is : " + ObjNB.pubdate);

                System.out.println();
                System.out
                        .println("-------------------------------------------------------------------------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();

    }

    public void runParser() {
        new XMLParserBean();
    }

}
