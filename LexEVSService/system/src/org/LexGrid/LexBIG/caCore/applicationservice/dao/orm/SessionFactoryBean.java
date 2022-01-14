package org.LexGrid.LexBIG.caCore.applicationservice.dao.orm;


import org.LexGrid.LexBIG.caCore.applicationservice.helper.SecurityInitializationHelper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class SessionFactoryBean extends LocalSessionFactoryBean {
	
	private HibernateConfigurationHolder configHolder;
	private SecurityInitializationHelper securityHelper;

	@Override
	protected SessionFactory newSessionFactory(Configuration config) throws HibernateException {
		logger.debug("In SessionFactorBean.newSession");
		configHolder.setConfiguration(config);
//		securityHelper.addFilters(config);
		return super.newSessionFactory(config);
	}

	public HibernateConfigurationHolder getConfigHolder() {
		return configHolder;
	}
	public void setConfigHolder(HibernateConfigurationHolder configHolder) {
		this.configHolder = configHolder;
	}

	public SecurityInitializationHelper getSecurityHelper() {
		return securityHelper;
	}

	public void setSecurityHelper(SecurityInitializationHelper securityHelper) {
		this.securityHelper = securityHelper;
	}
}
