/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.util;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;

public class DbusxmlXMLHelper extends XMLHelperImpl {

	private final boolean isNameSpaceNeeded = false;
	
	
	public DbusxmlXMLHelper() {
		super();
	}

	public DbusxmlXMLHelper(XMLResource resource) {
		super(resource);
	}

	@Override
	protected String getQName(EPackage pkg, String name,
			boolean mustHavePrefix) 
	{
		if (isNameSpaceNeeded) {
			// let's send what ever prefix that's got as the argument
			return super.getQName(pkg, name, true);
		} else {
			// we don't want a namespace prefix 
			return name;
		}
	}
}


