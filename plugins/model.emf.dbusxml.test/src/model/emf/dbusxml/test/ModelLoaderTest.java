package model.emf.dbusxml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Collections;

import model.emf.dbusxml.AnnotationType;
import model.emf.dbusxml.ArgType;
import model.emf.dbusxml.DocumentRoot;
import model.emf.dbusxml.InterfaceType;
import model.emf.dbusxml.MethodType;
import model.emf.dbusxml.NodeType;
import model.emf.dbusxml.PropertyType;
import model.emf.dbusxml.SignalType;
import model.emf.dbusxml.util.DbusxmlResourceFactoryImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

public class ModelLoaderTest {

	private DbusxmlResourceFactoryImpl factory;
	
	@Before
	public void setUp() throws Exception {
		factory = new DbusxmlResourceFactoryImpl();
	}

	@Test
	public void loadModel20() {
		InterfaceType i = loadModel("models/20-AllElements.xml");
		assertNotNull(i);
		
		assertEquals(4, i.getProperty().size());
		assertEquals(4, i.getMethod().size());
		assertEquals(2, i.getSignal().size());
		// TODO: add more checks here (e.g., names of elements, details, ...)
	}

	@Test
	public void loadModel30() {
		InterfaceType i = loadModel("models/30-Annotations.xml");
		assertNotNull(i);

		// annotations on interface level
		assertEquals(1, i.getAnnotation().size());
		AnnotationType annoInterface = i.getAnnotation().get(0);
		assertEquals("OnInterface", annoInterface.getValue());
		
		// general structure of interface
		assertEquals(1, i.getProperty().size());
		assertEquals(1, i.getMethod().size());
		assertEquals(1, i.getSignal().size());
		
		// annotations on properties
		PropertyType p = i.getProperty().get(0);
		assertEquals(1, p.getAnnotation().size());
		AnnotationType annoProperty = p.getAnnotation().get(0);
		assertEquals("OnProperty", annoProperty.getValue());

		// annotations on methods
		MethodType m = i.getMethod().get(0);
		assertEquals(1, m.getAnnotation().size());
		AnnotationType annoMethod = m.getAnnotation().get(0);
		assertEquals("OnMethod", annoMethod.getValue());

		// annotations on method arguments
		assertEquals(2, m.getArg().size());
		ArgType arg = m.getArg().get(0);
		assertEquals(1, arg.getAnnotation().size());
		AnnotationType annoArg = arg.getAnnotation().get(0);
		assertEquals("OnArg", annoArg.getValue());
		
		// annotations on signals
		SignalType s = i.getSignal().get(0);
		assertEquals(1, s.getAnnotation().size());
		AnnotationType annoSignal = s.getAnnotation().get(0);
		assertEquals("OnSignal", annoSignal.getValue());

	}

	
	private InterfaceType loadModel(String filename) {
		Resource resource = factory.createResource(URI.createFileURI(filename));
		try {
			resource.load(Collections.EMPTY_MAP);
			assertNotNull(resource.getContents());
			
			EObject root = resource.getContents().get(0);
			assertNotNull(root);
			assertTrue(root instanceof DocumentRoot);
			
			DocumentRoot doc = (DocumentRoot)root;
			NodeType node = doc.getNode();
			assertNotNull(node);
			
			assertEquals("Expecting exactly one interface", 1, node.getInterface().size());
			return node.getInterface().get(0);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
			return null;
		}
	}

}
