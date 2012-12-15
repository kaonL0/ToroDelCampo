package com.alnaiyr.loading;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.alnaiyr.general.EV;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public final class OptionLoader {

	/**
	 * @param ref
	 * @throws IOException
	 */
	public static void loadRessources(String ref) throws IOException {
		Log.info("Setting options...");
		OptionLoader.loadResources(ResourceLoader.getResourceAsStream(ref));
	}

	/**
	 * @param ref
	 * @throws IOException
	 */
	public static void loadResources(InputStream ref) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(ref);

			Element element = document.getDocumentElement();
			if (!element.getNodeName().equals("options")) {
				throw new IOException("Not a resource configuration file");
			}
			NodeList list = element.getElementsByTagName("sDim");
			for (int i = 0; i < list.getLength(); i++) {
				OptionLoader.loadSDim((Element) list.item(i));
			}
			list = element.getElementsByTagName("boolOpt");
			for (int i = 0; i < list.getLength(); i++) {
				OptionLoader.loadBoolOpt((Element) list.item(i));
			}
			Log.info("Options set!");
		} catch (IOException e) {
			Log.error(e);
			throw e;
		} catch (Exception e) {
			Log.error(e);
			throw new IOException("Unable to load resource configuration file");
		}
		ref.close();
	}

	/**
	 * LOads all info that are dimensions
	 * 
	 * @param sDim
	 */
	private static void loadSDim(Element sDim) {
		EV.setParsedInt(sDim.getAttribute("key"),
				Integer.valueOf(sDim.getAttribute("value")));
	}

	/**
	 * Loads all info that are boolean
	 * 
	 * @param bool
	 */
	private static void loadBoolOpt(Element bool) {
		EV.setParsedBool(bool.getAttribute("key"),
				Boolean.valueOf(bool.getAttribute("value")));
	}

	/**
	 * Writes values on an option document, following intern rules for names of
	 * Nodes
	 * 
	 * @param reference
	 */
	public static void writeNewValues(String reference) {

		try {
			InputStream ref = ResourceLoader.getResourceAsStream(reference);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(ref);
			OptionLoader.writeValuesOnDocument(document);
			Source source = new DOMSource(document);

			Result resultat = new StreamResult(reference);

			TransformerFactory fabrique = TransformerFactory.newInstance();
			Transformer transformer = fabrique.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.transform(source, resultat);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeValuesOnDocument(Document doc) {
		Element el = doc.getDocumentElement();
		NodeList list = el.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i) instanceof Element) {
				Element node = (Element) list.item(i);
				switch (node.getNodeName()) {
					case "sDim":
						OptionLoader.writeOnSDim(node);
						break;
					case "boolOpt":
						OptionLoader.writeOnBoolOpt(node);
						break;
				}
			}
		}
	}

	private static void writeOnSDim(Element node) {
		node.setAttribute("value",
				String.valueOf(EV.getParsedInt(node.getAttribute("key"))));
	}

	private static void writeOnBoolOpt(Element node) {
		node.setAttribute("value",
				String.valueOf(EV.getParsedBool(node.getAttribute("key"))));
	}
}
