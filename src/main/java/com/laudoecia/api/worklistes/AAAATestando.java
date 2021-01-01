package com.laudoecia.api.worklistes;

import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;

public class AAAATestando implements Event<QueryContext>{

	@Override
	public void fire(QueryContext arg0) {
	}

	@Override
	public <U extends QueryContext> CompletionStage<U> fireAsync(U arg0) {
		return null;
	}

	@Override
	public <U extends QueryContext> CompletionStage<U> fireAsync(U arg0, NotificationOptions arg1) {
		return null;
	}

	@Override
	public Event<QueryContext> select(Annotation... arg0) {
		return null;
	}

	@Override
	public <U extends QueryContext> Event<U> select(Class<U> arg0, Annotation... arg1) {
		return null;
	}

	@Override
	public <U extends QueryContext> Event<U> select(TypeLiteral<U> arg0, Annotation... arg1) {
		return null;
	}

}
