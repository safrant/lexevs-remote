package gov.nih.nci.system.applicationservice.dao.orm;


import org.LexGrid.LexBIG.caCore.applicationservice.helper.SecurityInitializationHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class FilterableHibernateTemplate extends HibernateTemplate {

	private SecurityInitializationHelper securityHelper;

	public FilterableHibernateTemplate(SessionFactory sessionFactory, SecurityInitializationHelper securityHelper) {
		super(sessionFactory);
		this.securityHelper = securityHelper;
	}

	protected void enableFilters(Session session) {
		if (securityHelper == null) {
			super.enableFilters(session);
			return;
		}
		
		securityHelper.enableAttributeLevelSecurity(getSessionFactory());
		securityHelper.initializeFilters(session);
	}
	
}
