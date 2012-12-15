/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.loading;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Is able to give locations of specific types, based on an XML file
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class TypeLoader {

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	public static String getPackage(String name, String fileRef) {

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(fileRef);

			Element element = document.getDocumentElement();
			if (element.getNodeName() != "types") {
				throw new IOException("Not a type reference file");
			}

			NodeList list = element.getElementsByTagName(name.toLowerCase());
			Element elem = (Element) list.item(0);

			return elem.getAttribute("package");

		} catch (ParserConfigurationException | SAXException | IOException
				| NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getClass(String name, String fileRef) {

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(fileRef);

			Element element = document.getDocumentElement();
			if (element.getNodeName() != "types") {
				throw new IOException("Not a type reference file");
			}

			NodeList list = element.getElementsByTagName(name.toLowerCase());
			Element elem = (Element) list.item(0);

			return elem.getAttribute("class");
		} catch (ParserConfigurationException | SAXException | IOException
				| NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}

}
