package org.LexGrid.LexBIG.caCore.applicationservice.dao;

import java.util.List;

/**
 * @author Satish Patel, Dan Dumitru
 */
public interface DAO {

	/**
	 * Queries the datasource 
	 * 
	 * @param request 
	 *           
	 * @return
	 *
	 */
	public Response query(Request request) throws DAOException, Exception;
	
	public List<String> getAllClassNames();

}
