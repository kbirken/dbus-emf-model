/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

public class DBusDictType extends DBusArrayType {

	public DBusDictType (DBusDictEntryType elementType) {
		super(elementType);
	}
	
	public DBusType getKeyType() {
		DBusDictEntryType dictEntry = (DBusDictEntryType)getElementType();
		return dictEntry.getKeyType();
	}

	public DBusType getValueType() {
		DBusDictEntryType dictEntry = (DBusDictEntryType)getElementType();
		return dictEntry.getValueType();
	}
}
