/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

public class DBusArrayType extends DBusType {

	private DBusType elementType;
	
	public DBusArrayType (DBusType elementType) {
		super("Array[" + elementType.getName() + "]");
		this.elementType = elementType;
	}
	
	public DBusType getElementType() {
		return elementType;
	}

	public boolean containsArray() {
		return elementType instanceof DBusArrayType;
	}
	
	@Override
	public boolean validate() {
		if (elementType==null)
			return false;
		
		return elementType.validate();
	}
}
