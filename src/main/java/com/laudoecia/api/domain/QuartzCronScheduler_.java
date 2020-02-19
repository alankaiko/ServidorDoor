package com.laudoecia.api.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuartzCronScheduler.class)
public abstract class QuartzCronScheduler_ {

	public static volatile SingularAttribute<QuartzCronScheduler, String> name;
	public static volatile SingularAttribute<QuartzCronScheduler, String> cronStatement;
	public static volatile SingularAttribute<QuartzCronScheduler, Integer> startDelay;
	public static volatile SingularAttribute<QuartzCronScheduler, Integer> id;
	public static volatile SingularAttribute<QuartzCronScheduler, Boolean> enabled;

	public static final String NAME = "name";
	public static final String CRON_STATEMENT = "cronStatement";
	public static final String START_DELAY = "startDelay";
	public static final String ID = "id";
	public static final String ENABLED = "enabled";

}

