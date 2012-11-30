package model.emf.dbusxml.test;
import static org.junit.Assert.*;

import java.util.Iterator;

import model.emf.dbusxml.typesystem.DBusArrayType;
import model.emf.dbusxml.typesystem.DBusBasicType;
import model.emf.dbusxml.typesystem.DBusDictType;
import model.emf.dbusxml.typesystem.DBusStructType;
import model.emf.dbusxml.typesystem.DBusType;
import model.emf.dbusxml.typesystem.DBusTypeList;
import model.emf.dbusxml.typesystem.DBusTypeParser;

import org.junit.Test;


public class DBusTypeParserTest {

	@Test
	public void testPrimitive() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("i");
		assertTrue(genType instanceof DBusBasicType );
		assertTrue(genType != null);
	}
	
	@Test
	public void testDict() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("a{ii}");
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		assertTrue(((DBusDictType)genType).getKeyType() instanceof DBusBasicType);
		assertTrue(((DBusDictType)genType).getValueType() instanceof DBusBasicType);
	}
	
	@Test
	public void testStruct() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("(ii)");
		assertTrue(genType instanceof DBusStructType );
		assertTrue(genType != null);
	}
	
	@Test
	public void testArray() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("ai");
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusBasicType);
	}
	
	@Test
	public void testArrayStrcut() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("a(ii)");
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusStructType);
	}
	
	@Test
	public void testArrayDict() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("aa{ii}");
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusDictType);
		DBusDictType dict = (DBusDictType)((DBusArrayType)genType).getElementType();
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusBasicType);
	}
	
	@Test
	public void testDictWithStruct() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusType genType = parser.parseSingleType("a{i(ii)}");
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		DBusDictType dict = (DBusDictType)genType;
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusStructType);
	}
	
	@Test
	public void testMultiLinePrimitive() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusTypeList genTypeList = parser.parseTypeList("iii");
		assertTrue(genTypeList.size() == 3);
		for (Iterator<DBusType> genTypeIterator = genTypeList.iterator(); genTypeIterator.hasNext();) {
			assertTrue(genTypeIterator.next() instanceof DBusBasicType);
		}	
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct1() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusTypeList genTypeList = parser.parseTypeList("i(ii)");
		assertTrue(genTypeList.size() == 2);
		assertTrue(genTypeList.get(0) instanceof DBusBasicType);
		assertTrue(genTypeList.get(1) instanceof DBusStructType);	
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct2() {
		DBusTypeParser  parser = new DBusTypeParser();
		DBusTypeList genTypeList = parser.parseTypeList("i(ii)i");
		assertTrue(genTypeList.size() == 3);
		assertTrue(genTypeList.get(0) instanceof DBusBasicType);
		assertTrue(genTypeList.get(1) instanceof DBusStructType);
		assertTrue(genTypeList.get(2) instanceof DBusBasicType);
	}

}
