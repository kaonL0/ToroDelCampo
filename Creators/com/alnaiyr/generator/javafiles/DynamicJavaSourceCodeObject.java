/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.generator.javafiles;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DynamicJavaSourceCodeObject extends SimpleJavaFileObject {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private String qualifiedName;
	private String sourceCode;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	protected DynamicJavaSourceCodeObject(String name, String code) {
		super(URI.create("string:///" + name.replaceAll("\\.", "/")
				+ Kind.SOURCE.extension), Kind.SOURCE);

		this.qualifiedName = name;
		this.sourceCode = code;

	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.tools.SimpleJavaFileObject#getCharContent(boolean)
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		return sourceCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * 
	 * @param qualifiedName
	 */
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	/**
	 * 
	 * @return
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * 
	 * @param sourceCode
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}
