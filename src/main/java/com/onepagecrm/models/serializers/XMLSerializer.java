package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 04/10/2017.
 */
@SuppressWarnings("WeakerAccess")
public class XMLSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(XMLSerializer.class.getSimpleName());

    public static Document documentFromString(String xml) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);

        } catch (Exception e) {
            LOG.severe("Problems parsing XML to Document.");
            LOG.severe(e.toString());
            e.printStackTrace();
            throw e;
        }
    }

    public static String stringFromElement(Element element, String tagName) {
        if (!Utilities.notNullOrEmpty(tagName) || element == null) {
            return "";
        }
        final NodeList elements = element.getElementsByTagName(tagName);
        final Node node = elements.item(0);
        return node != null ? node.getTextContent() : "";
    }
}
