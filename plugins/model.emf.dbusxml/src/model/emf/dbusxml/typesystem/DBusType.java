/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package model.emf.dbusxml.typesystem;

abstract public class DBusType {
	private String name = "";
	
	public DBusType (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	abstract public boolean validate();
}
