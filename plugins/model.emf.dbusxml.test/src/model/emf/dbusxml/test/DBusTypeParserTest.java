package model.emf.dbusxml.test;

import org.junit.Test;
import static org.junit.Assert.*;
import model.emf.dbusxml.typesystem.DBusArrayType;
import model.emf.dbusxml.typesystem.DBusBasicType;
import model.emf.dbusxml.typesystem.DBusDictType;
import model.emf.dbusxml.typesystem.DBusStructType;
import model.emf.dbusxml.typesystem.DBusType;
import model.emf.dbusxml.typesystem.DBusTypeParser;

public class DBusTypeParserTest {

	@Test
	public void testPrimitive() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "i";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusBasicType );
		assertTrue(genType != null);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testDict() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "a{ii}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		assertTrue(((DBusDictType)genType).getKeyType() instanceof DBusBasicType);
		assertTrue(((DBusDictType)genType).getValueType() instanceof DBusBasicType);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testStruct() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "(ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusStructType );
		assertTrue(genType != null);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testArray() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "ai";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusBasicType);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testArrayStrcut() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "a(ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusStructType);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testArrayDict() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "aa{ii}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);		
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusDictType);
		DBusDictType dict = (DBusDictType)((DBusArrayType)genType).getElementType();
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusBasicType);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testDictWithStruct() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "a{i(ii)}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		DBusDictType dict = (DBusDictType)genType;
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusStructType);
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testNestedStruct() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "(i(ii(iii))iii(i)ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusStructType );
		assertTrue(genType != null);
		assertTrue(genType.getName()
				.equals("Struct[Int32,Struct[Int32,Int32,Struct[Int32,Int32,Int32]],Int32,Int32,Int32,Struct[Int32],Int32,Int32]"));
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testComplexType1() { //from Genivi Audio manager command plugin 
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "a(qs(nn)nnq)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType);		
		DBusArrayType array = (DBusArrayType)genType;
		DBusStructType struct = (DBusStructType)array.getElementType();	
		assertTrue(struct.getElementTypes().size() == 6);
		assertTrue(genType.getName()
				.equals("Array[Struct[UInt16,String,Struct[Int16,Int16],Int16,Int16,UInt16]]"));
		System.out.println("parsing " + signature + " --> " + genType.getName());
	}
	
	@Test
	public void testMultiPrimitive() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "ii";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);	
		} catch (IllegalArgumentException e) {
			System.out.println("parsing " + signature + " --> " + "null");
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct1() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "i(ii)";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);
			
		} catch (IllegalArgumentException e) {
			System.out.println("parsing " + signature + " --> " + "null");
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct2() {
		DBusTypeParser  parser = new DBusTypeParser();
		String signature = "i(ii)i";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);
			
		} catch (IllegalArgumentException e) {
			System.out.println("parsing " + signature + " --> " + "null");
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
}
