/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

public class DBusDictEntryType extends DBusType {
	private DBusType keyType;
	private DBusType valueType;
	
	public DBusDictEntryType (DBusType keyType, DBusType valueType) {
		super("{" + keyType.getName() + "=>" + valueType.getName() + "}");
		this.keyType = keyType;
		this.valueType = valueType;
	}

	public DBusType getKeyType() {
		return keyType;
	}

	public DBusType getValueType() {
		return valueType;
	}

	@Override
	public boolean validate() {
		if (keyType==null || ! keyType.validate())
			return false;
		
		// DBus only allows basic types as key type
		if (! (keyType instanceof DBusBasicType)) {
			return false;
		}

		if (valueType==null || ! valueType.validate())
			return false;
		
		return true;
	}
}
