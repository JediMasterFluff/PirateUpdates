package com.applications.fluffy.piratingupdates.Loaders;

import android.os.AsyncTask;

import com.applications.fluffy.piratingupdates.Objects.Torrents;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by fluffy on 10/02/17.
 */

public class XMLParser extends AsyncTask<Void, Void, Void> {

    private Torrents objTor;
    private Vector<Torrents> vectParse;

    private int mediaThumbnailCount;
    private boolean urlflag;
    private int count = 0;

    @Override
    protected Void doInBackground(Void... params) {
        try {

            vectParse = new Vector<>();
            URL url = new URL("https://yts.ag/rss/0/1080p/all/0");
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

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            System.out.println();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    objTor = new Torrents();
                    vectParse.add(objTor);

                    objTor.setTitle(getTagValue("title", eElement));
                    objTor.setDescription(getTagValue("description", eElement));
                    objTor.setPosterImgLink(objTor.getDescription().replaceAll("\\<.*?\\>", "|"));
                    objTor.setTorrentWebLink(getTagValue("link", eElement));
                    objTor.setTorrentDownloadLink(getTagAttributes("enclosure", eElement, "//rss/channel/item/enclosure/@url"));
                    objTor.setPubDate(getTagValue("pubDate", eElement));

                }
            }

            for (int index1 = 0; index1 < vectParse.size(); index1++) {
                Torrents ObjNB = vectParse.get(index1);

                System.out.println("Item No : " + index1);
                System.out.println();

                System.out.println("Title is : " + ObjNB.getTitle());
                System.out.println("Description is : " + ObjNB.getDescription());
                System.out.println("Link is : " + ObjNB.getTorrentWebLink());
                System.out.println("Download Link is : " + ObjNB.getTorrentDownloadLink());
                System.out.println("Pubdate is : " + ObjNB.getPubDate());
                System.out.println("IMDB Rating is : " + ObjNB.getImdbRating());
                System.out.println("Genre is : " + ObjNB.getGenre());
                System.out.println("Runtime Link is : " + ObjNB.getRuntime());
                System.out.println("Size Link is : " + ObjNB.getSize());

                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = nlList.item(0);

        return nValue.getNodeValue();

    }

    private String getTagAttributes(String sTag, Element eElement, String path) {

        XPath xpath =  XPathFactory.newInstance().newXPath();

        try {
            NodeList node = (NodeList) xpath.evaluate(path, eElement, XPathConstants.NODESET);
            Node nValue = node.item(0);

            return nValue.getNodeValue();

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
