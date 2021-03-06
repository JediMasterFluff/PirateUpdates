package com.applications.fluffy.piratingupdates.Helpers;

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
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by fluffy on 10/02/17.
 */

public class XMLParser extends AsyncTask<Void, Void, ArrayList<Torrents>> {

    private ArrayList<Torrents> vectParse;

    @Override
    protected ArrayList<Torrents> doInBackground(Void... params) {
        try {

            vectParse = new ArrayList<>();
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

                    Torrents objTor = new Torrents();
                    vectParse.add(objTor);

                    objTor.setTitle(getTagValue("title", eElement));
                    objTor.setDescription(getTagValue("description", eElement));
                    objTor.setTorrentWebLink(getTagValue("link", eElement));
                    objTor.setTorrentDownloadLink(getTagAttributes(eElement));
                    objTor.setPubDate(getTagValue("pubDate", eElement));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vectParse;
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = nlList.item(0);

        return nValue.getNodeValue();

    }

    private String getTagAttributes(Element eElement) {

        XPath xpath =  XPathFactory.newInstance().newXPath();

        try {
            NodeList node = (NodeList) xpath.evaluate("//rss/channel/item/enclosure/@url", eElement, XPathConstants.NODESET);
            Node nValue = node.item(0);

            return nValue.getNodeValue();

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Torrents> torrentses) {
        super.onPostExecute(torrentses);
        returnList(torrentses);
    }

    private ArrayList<Torrents> returnList(ArrayList<Torrents> torrentses) {
        return torrentses;
    }
}
