package com.laudoecia.api.worklistes;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;

public class OrderByTag {
	public final int tag;
	private final BiFunction<CriteriaBuilder, Expression<?>, Order> order;

	private OrderByTag(int tag, BiFunction<CriteriaBuilder, Expression<?>, Order> order) {
		this.tag = tag;
		this.order = order;
	}

	public static OrderByTag asc(int tag) {
		return new OrderByTag(tag, CriteriaBuilder::asc);
	}

	public static OrderByTag desc(int tag) {
		return new OrderByTag(tag, CriteriaBuilder::desc);
	}

	public static OrderByTag valueOf(Attributes attrs) {
		return "DECREASING".equals(attrs.getString(Tag.SortingDirection))
				? desc(attrs.getInt(Tag.SelectorAttribute, -1))
				: asc(attrs.getInt(Tag.SelectorAttribute, -1));
	}

	public Order order(CriteriaBuilder cb, Expression<?> x) {
		return order.apply(cb, x);
	}
}
