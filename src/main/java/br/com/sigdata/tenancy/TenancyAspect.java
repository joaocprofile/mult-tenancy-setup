package br.com.sigdata.tenancy;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Configuration
@Transactional(propagation = Propagation.REQUIRED)
public class TenancyAspect {
	
	private EntityManager manager;

	public TenancyAspect(EntityManager manager) {		
		this.manager = manager;
	}

	@Before("execution(* br.com.sigdata.repository.*.*(..))")
	public void definirTenant() {
		String tenantId = TenancyInterceptor.getTenantId();
		if (tenantId != null) {
			manager.unwrap(Session.class).enableFilter("tenant").setParameter("tenantid", tenantId);
		}
	}


}
