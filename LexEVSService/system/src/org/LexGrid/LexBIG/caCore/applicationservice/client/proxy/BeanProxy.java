package org.LexGrid.LexBIG.caCore.applicationservice.client.proxy;

import org.LexGrid.LexBIG.caCore.applicationservice.ApplicationService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class BeanProxy implements MethodInterceptor
{
	ApplicationService as;
	org.LexGrid.LexBIG.caCore.applicationservice.client.proxy.ProxyHelper proxyHelper;
	
	public BeanProxy(ApplicationService as, ProxyHelper proxyHelper)
	{
		this.as = as;
		this.proxyHelper = proxyHelper;
	}
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
	    if(!proxyHelper.isInitialized(invocation))
	    	return proxyHelper.lazyLoad(as,invocation);
	    else
	    	return proxyHelper.convertToProxy(as,invocation.proceed());
	}
}