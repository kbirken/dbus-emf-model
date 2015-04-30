package model.emf.dbusxml.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import model.emf.dbusxml.typesystem.DBusArrayType;
import model.emf.dbusxml.typesystem.DBusBasicType;
import model.emf.dbusxml.typesystem.DBusDictType;
import model.emf.dbusxml.typesystem.DBusStructType;
import model.emf.dbusxml.typesystem.DBusType;
import model.emf.dbusxml.typesystem.DBusTypeParser;

public class DBusTypeParserTest {

	DBusTypeParser parser;
	
	@Before
	public void setup() {
		parser = new DBusTypeParser();
	}
	
	// **** Test primitive Types **** //
	
	@Test
	public void testByte() {
		DBusType genType = parser.parseSingleType("y");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("Byte", genType.getName());
	}
	
	@Test
	public void testBoolean() {
		DBusType genType = parser.parseSingleType("b");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("Boolean", genType.getName());
	}
	
	@Test
	public void testInt16() {
		DBusType genType = parser.parseSingleType("n");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("Int16", genType.getName());
	}
	
	@Test
	public void testUInt16() {
		DBusType genType = parser.parseSingleType("q");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("UInt16", genType.getName());
	}
	
	@Test
	public void testInt32() {
		DBusType genType = parser.parseSingleType("i");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType);
		assertEquals("Int32", genType.getName());
	}
	
	@Test
	public void testUIn32() {
		DBusType genType = parser.parseSingleType("u");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("UInt32", genType.getName());
	}
	
	@Test
	public void testInt64() {
		DBusType genType = parser.parseSingleType("x");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("Int64", genType.getName());
	}
	
	@Test
	public void testUInt64() {
		DBusType genType = parser.parseSingleType("t");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("UInt64", genType.getName());
	}
	
	@Test
	public void testDouble() {
		DBusType genType = parser.parseSingleType("d");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType );
	    assertEquals("Double", genType.getName());
	}
	
	@Test
	public void testString() {
		DBusType genType = parser.parseSingleType("s");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType);
		assertEquals("String", genType.getName());
	}
	
	@Test
	public void testSignature() {
		DBusType genType = parser.parseSingleType("g");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType);
		assertEquals("Signature", genType.getName());
	}
	
	@Test
	public void testObjectPath() {
		DBusType genType = parser.parseSingleType("o");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType);
		assertEquals("Object_Path", genType.getName());
	}
	
	@Test
	public void testUnixFD() {
		DBusType genType = parser.parseSingleType("h");
		assertNotNull(genType);
		assertTrue(genType instanceof DBusBasicType);
		assertEquals("Unix_Fd", genType.getName());
	}
	
	// **** Test complex Types **** //
	
	@Test
	public void testDict() {
		String signature = "a{ii}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		assertTrue(((DBusDictType)genType).getKeyType() instanceof DBusBasicType);
		assertTrue(((DBusDictType)genType).getValueType() instanceof DBusBasicType);
	}
	
	@Test
	public void testStruct() {
		String signature = "(ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusStructType );
		assertTrue(genType != null);
	}
	
	@Test
	public void testArray() {
		String signature = "ai";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusBasicType);
	}
	
	@Test
	public void testArrayStruct() {
		String signature = "a(ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusStructType);
	}
	
	@Test
	public void testArrayDict() {
		String signature = "aa{ii}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType );
		assertTrue(genType != null);		
		assertTrue(((DBusArrayType)genType).getElementType() instanceof DBusDictType);
		DBusDictType dict = (DBusDictType)((DBusArrayType)genType).getElementType();
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusBasicType);
	}
	
	@Test
	public void testDictWithStruct() {
		String signature = "a{i(ii)}";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusDictType );
		assertTrue(genType != null);
		DBusDictType dict = (DBusDictType)genType;
		assertTrue(dict.getKeyType() instanceof DBusBasicType);
		assertTrue(dict.getValueType() instanceof DBusStructType);
	}
	
	@Test
	public void testNestedStruct() {
		String signature = "(i(ii(iii))iii(i)ii)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusStructType );
		assertTrue(genType != null);
		assertTrue(genType.getName()
				.equals("Struct[Int32,Struct[Int32,Int32,Struct[Int32,Int32,Int32]],Int32,Int32,Int32,Struct[Int32],Int32,Int32]"));
	}
	
	@Test
	public void testComplexType1() { //from Genivi Audio manager command plugin 
		String signature = "a(qs(nn)nnq)";
		DBusType genType = parser.parseSingleType(signature);
		assertTrue(genType instanceof DBusArrayType);		
		DBusArrayType array = (DBusArrayType)genType;
		DBusStructType struct = (DBusStructType)array.getElementType();	
		assertTrue(struct.getElementTypes().size() == 6);
		assertTrue(genType.getName()
				.equals("Array[Struct[UInt16,String,Struct[Int16,Int16],Int16,Int16,UInt16]]"));
	}
	
	@Test
	public void testMultiPrimitive() {
		String signature = "ii";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);	
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct1() {
		String signature = "i(ii)";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);
			
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
	
	@Test
	public void testMultiLinePrimitiveAndStruct2() {
		String signature = "i(ii)i";
		try {
			@SuppressWarnings("unused")
			DBusType genType = parser.parseSingleType(signature);
			
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("multitypes not supported"));
		}
	}
}
