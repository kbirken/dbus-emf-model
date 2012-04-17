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

	// ********************************************************************************
	
	private DBusTypeList parseTypeList (boolean expectClosingBracket) {
		DBusTypeList res = new DBusTypeList();
		if (expectClosingBracket) {
			while (type.charAt(index) != ')') {
				DBusType sct = parseSingleCompleteType();
				res.add(sct);
			}
		} else {
			while (index < type.length()) {
				DBusType sct = parseSingleCompleteType();
				res.add(sct);
			}
		}
		return res;
	}
	
	private DBusType parseSingleCompleteType() {
		DBusType res = null;
		switch (type.charAt(index)) {
		case 'y': index++; res = DBusBasicType.DBUS_TYPE_INT8; break;
		case 'b': index++; res = DBusBasicType.DBUS_TYPE_BOOLEAN; break;
		case 'n': index++; res = DBusBasicType.DBUS_TYPE_INT16; break;
		case 'q': index++; res = DBusBasicType.DBUS_TYPE_UINT16; break;
		case 'i': index++; res = DBusBasicType.DBUS_TYPE_INT32; break;
		case 'u': index++; res = DBusBasicType.DBUS_TYPE_UINT32; break;
		case 'x': index++; res = DBusBasicType.DBUS_TYPE_INT64; break;
		case 't': index++; res = DBusBasicType.DBUS_TYPE_UINT64; break;
		case 'd': index++; res = DBusBasicType.DBUS_TYPE_DOUBLE; break;
		case 's': index++; res = DBusBasicType.DBUS_TYPE_STRING; break;
		case 'o': /* Object Path - not supported */ break;
		case 'g': /* Type signature - not supported */ break;
		case 'a': index++; res = parseArrayType(); break;
		case '(': index++; res = parseStructType(); break;
		case 'v': index++; res = DBusBasicType.DBUS_TYPE_VARIANT; break;
		case '{': index++; res = parseDictEntryType(); break;
		case 'h': /* UNIX FD - not supported */ break;
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
