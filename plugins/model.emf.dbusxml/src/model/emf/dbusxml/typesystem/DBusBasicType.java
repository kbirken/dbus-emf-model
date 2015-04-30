/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;


public class DBusBasicType extends DBusType {

	public static final DBusBasicType DBUS_TYPE_BYTE    = new DBusBasicType("Byte");
	public static final DBusBasicType DBUS_TYPE_INT16   = new DBusBasicType("Int16");
	public static final DBusBasicType DBUS_TYPE_UINT16  = new DBusBasicType("UInt16");
	public static final DBusBasicType DBUS_TYPE_INT32   = new DBusBasicType("Int32");
	public static final DBusBasicType DBUS_TYPE_UINT32  = new DBusBasicType("UInt32");
	public static final DBusBasicType DBUS_TYPE_INT64   = new DBusBasicType("Int64");
	public static final DBusBasicType DBUS_TYPE_UINT64  = new DBusBasicType("UInt64");
	public static final DBusBasicType DBUS_TYPE_BOOLEAN = new DBusBasicType("Boolean");
	public static final DBusBasicType DBUS_TYPE_DOUBLE  = new DBusBasicType("Double");
	public static final DBusBasicType DBUS_TYPE_STRING  = new DBusBasicType("String");
	public static final DBusBasicType DBUS_TYPE_VARIANT = new DBusBasicType("Variant");
	public static final DBusBasicType DBUS_TYPE_SIGNATURE = new DBusBasicType("Signature");
	public static final DBusBasicType DBUS_TYPE_OBJECT_PATH = new DBusBasicType("Object_Path");
	public static final DBusBasicType DBUS_TYPE_UNIX_FD = new DBusBasicType("Unix_Fd");

	private DBusBasicType (String name) {
		super(name);
	}
	
	@Override
	public boolean validate() {
		// basic types are always valid
		return true;
	}
}
