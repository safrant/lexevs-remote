package org.LexGrid.LexBIG.distributed.test.features;

import org.LexGrid.LexBIG.DataModel.Collections.NameAndValueList;
import org.LexGrid.LexBIG.DataModel.Core.AssociatedConcept;
import org.LexGrid.LexBIG.Impl.function.LexBIGServiceTestCase;
import org.LexGrid.LexBIG.Impl.load.umls.EntityAssnsToEntityQualsDataTestIT;
import org.LexGrid.LexBIG.Impl.testUtility.DataTestUtils;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.caCore.interfaces.LexEVSApplicationService;
import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.naming.Mappings;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestLEXEVS_3947 extends ServiceTestCase {

	LexEVSApplicationService svc;
	private AssociatedConcept[] associatedConcept;
	private CodedNodeGraph cng;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		svc = LexEVSServiceHolder.instance().getLexEVSAppService();
		cng =	svc.getNodeGraph(META_SCHEME, Constructors.createCodingSchemeVersionOrTagFromVersion(META_VERSION), null);
		
		associatedConcept = cng.resolveAsList(Constructors.createConceptReference("DIABT", "NCI Metathesaurus"), true, true, 1, 1, null, null, null, -1)
				.getResolvedConceptReference(0)
				.getSourceOf()
				.getAssociation()[0]
				.getAssociatedConcepts()
				.getAssociatedConcept();
	}
	
	
	@Test
	public void testSupportedQualifierLoad() throws Exception {
		CodingScheme scheme = svc.resolveCodingScheme(LexBIGServiceTestCase.AIR_URN, 
				Constructors.createCodingSchemeVersionOrTagFromVersion(LexBIGServiceTestCase.AIR_VERSION));
		Mappings mappings = scheme.getMappings();
		assertTrue(mappings.getSupportedAssociationQualifierAsReference().stream().
		anyMatch(x -> x.getContent().equals("MODIFIER_ID")));
		assertTrue(mappings.getSupportedAssociationQualifierAsReference().stream().
				anyMatch(x -> x.getContent().equals("CHARACTERISTIC_TYPE_ID")));
		
	}
	
	public static junit.framework.Test suite() {  
		return new JUnit4TestAdapter(TestLEXEVS_3947.class);  
	} 

}
