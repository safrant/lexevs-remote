package org.LexGrid.LexBIG.distributed.test.services;

import java.util.List;

import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Extensions.Generic.MappingExtension;
import org.LexGrid.LexBIG.Extensions.Generic.MappingExtension.Mapping;
import org.LexGrid.LexBIG.Extensions.Generic.MappingExtension.Mapping.SearchContext;
import org.LexGrid.LexBIG.Impl.Extensions.tree.model.LexEvsTree;
import org.LexGrid.LexBIG.Impl.Extensions.tree.model.LexEvsTreeNode;
import org.LexGrid.LexBIG.Impl.Extensions.tree.service.TreeService;
import org.LexGrid.LexBIG.Impl.Extensions.tree.service.TreeServiceFactory;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.SearchDesignationOption;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Iterators.ResolvedConceptReferencesIterator;
import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.junit.Before;
import org.junit.Test;

public class LexBIGExtensionTests extends ServiceTestCase {

	private LexBIGService lbs;
	private MappingExtension mappingExtension;
	private TreeService treeExtension;

	@Before
	public void setUp(){
		lbs = LexEVSServiceHolder.instance().getLexEVSAppService();
		try {
			mappingExtension = (MappingExtension)lbs.getGenericExtension("MappingExtension");
		     treeExtension = TreeServiceFactory.getInstance().getTreeService(lbs);
		} catch (LBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMappingExtension() throws Exception {
	ResolvedConceptReferencesIterator itr;
	CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
	csvt.setVersion(MAPPING_VERSION);
		Mapping mapping =
				mappingExtension.getMapping(MAPPING_SCHEME, csvt, "NCIt_to_ChEBI_Mappings");

			if (mapping != null) {
				mapping = mapping.restrictToMatchingDesignations(
							MAPPING_TEXT, SearchDesignationOption.ALL, "LuceneQuery", null, SearchContext.SOURCE_OR_TARGET_CODES);
				itr = mapping.resolveMapping();
		System.out.println(itr.next());
				assertTrue(itr.numberRemaining() > 0);

			}
	}
	
	
	@Test
	public void testTreeServiceDistributed() throws Exception{
		 LexEvsTree tree = null;
//	        CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
//	        csvt.setVersion("2011-12-08_Test");
//	         tree = treeExtension.getTree("Nanoparticle Ontology", csvt, "NPO_1607", "npo", "is_a");
//
//	            LexEvsTreeNode focusNode = tree.getCurrentFocus();
//	            focusNode.setNamespace("npo");
//	            List<LexEvsTreeNode> nodeList = treeExtension.getEvsTreeConverter().buildEvsTreePathFromRootTree(focusNode);
//	            assertTrue("1",nodeList.size() > 0);


		CodingSchemeVersionOrTag csvt2 = new CodingSchemeVersionOrTag();
		csvt2.setTag("PRODUCTION");
		tree = treeExtension.getTree("obib", csvt2, "IAO_0000578", "obo", "is_a");

		LexEvsTreeNode focusNode2 = tree.getCurrentFocus();
		focusNode2.setNamespace("obib");
		List<LexEvsTreeNode> nodeList2 = treeExtension.getEvsTreeConverter().buildEvsTreePathFromRootTree(focusNode2);
		assertTrue("2",nodeList2.size() > 0);
	}
}
	
