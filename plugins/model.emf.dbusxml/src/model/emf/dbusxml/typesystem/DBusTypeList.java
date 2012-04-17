/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

import java.util.ArrayList;

public class DBusTypeList extends ArrayList<DBusType> {
	private static final long serialVersionUID = -4732555355231937691L;

	public String asString() {
		String res = "";
		String sep = "";
		for(DBusType t : this) {
			res += sep + t.getName();
			sep = ",";
		}
		
		return res;
	}
}
