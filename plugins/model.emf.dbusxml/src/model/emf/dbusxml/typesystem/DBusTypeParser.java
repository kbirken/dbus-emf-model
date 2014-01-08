/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

/**
 * Parser for DBus type signatures
 * 
 * DBus type signatures are strings with a well-defined format. They are used
 * to define signatures of interface functions (i.e., the types of the input
 * and output parameters). The syntax and semantics of DBus type signatures are
 * defined here:
 *    http://dbus.freedesktop.org/doc/dbus-specification.html
 * 
 * @author kbirken
 *
 */
public class DBusTypeParser {
	private int index;
	private String type;
	
	public DBusType parseSingleType (String type) {
		this.type = type;
		this.index = 0;

		// parse signature string
		DBusType res = parseSingleCompleteType();
		
		if (index != type.length()) {
			throw new IllegalArgumentException("multitypes not supported");
		}
				
		if (res==null) {
			// parse error
			return null;
		}
		
		// validate parsed syntax tree
		if (! res.validate()) {
			return null;
		}
		
		// parsing and validation successful
		return res;
	}


	public DBusTypeList parseTypeList (String type) {
		this.type = type;
		this.index = 0;

		DBusTypeList res = parseTypeList(false);
		for(DBusType t : res) {
			if (! t.validate()) {
				return null;
			}
		}
		
		// parsing and validation successful
		return res;
	}


	// ********************************************************************************
	
	private DBusTypeList parseTypeList (boolean expectClosingBracket) {
		DBusTypeList res = new DBusTypeList();
		if (expectClosingBracket) {
			while (type.charAt(index) != ')') {
				DBusType sct = parseSingleCompleteType();
				res.add(sct);
			}
			index++;
		} else {
			while (index < type.length()) {
				DBusType sct = parseSingleCompleteType();
				res.add(sct);
			}
		}
		return res;
	}
	
	private DBusType parseSingleCompleteType() {
		// progress to next character first
		char c = type.charAt(index);
		index++;

		DBusType res = null;
		switch (c) {
			case 'y': res = DBusBasicType.DBUS_TYPE_BYTE; break;
			case 'b': res = DBusBasicType.DBUS_TYPE_BOOLEAN; break;
			case 'n': res = DBusBasicType.DBUS_TYPE_INT16; break;
			case 'q': res = DBusBasicType.DBUS_TYPE_UINT16; break;
			case 'i': res = DBusBasicType.DBUS_TYPE_INT32; break;
			case 'u': res = DBusBasicType.DBUS_TYPE_UINT32; break;
			case 'x': res = DBusBasicType.DBUS_TYPE_INT64; break;
			case 't': res = DBusBasicType.DBUS_TYPE_UINT64; break;
			case 'd': res = DBusBasicType.DBUS_TYPE_DOUBLE; break;
			case 's': res = DBusBasicType.DBUS_TYPE_STRING; break;
			
			// object path - will be converted to String type without further checks
			case 'o': res = DBusBasicType.DBUS_TYPE_STRING; break;
	
			// type signature - will be converted to String type without further checks
			case 'g': res = DBusBasicType.DBUS_TYPE_STRING; break;
			
			// UNIX file descriptor - will be mapped to UINT32 type without further checks
			case 'h': res = DBusBasicType.DBUS_TYPE_UINT32; break;
	
			// complex types
			case 'a': res = parseArrayType(); break;
			case '(': res = parseStructType(); break;
			case 'v': res = DBusBasicType.DBUS_TYPE_VARIANT; break;
			case '{': res = parseDictEntryType(); break;
		}
		
		return res;
	}

	private DBusArrayType parseArrayType() {
		DBusType elementType = parseSingleCompleteType();
		if (elementType==null)
			return null;
		
		DBusArrayType res = null;
		if (elementType instanceof DBusDictEntryType)
			res = new DBusDictType((DBusDictEntryType) elementType);
		else
			res = new DBusArrayType(elementType);
		
		return res;
	}

	private DBusStructType parseStructType() {
		DBusTypeList typeList = parseTypeList(true);
		return new DBusStructType(typeList);
	}

	private DBusDictEntryType parseDictEntryType() {
		DBusType keyType = parseSingleCompleteType();
		if (keyType==null)
			return null;
		DBusType valueType = parseSingleCompleteType();
		if (valueType==null)
			return null;
		
		// expecting closing curly bracket
		if (type.charAt(index) != '}')
			return null;
		index++;
		
		return new DBusDictEntryType(keyType, valueType);
	}
	
}
