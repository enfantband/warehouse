package com.samsbeauty.warehouse.menu;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.samsbeauty.warehouse.menu.model.Menu;

public class MenuSpecifications {
	public static Specification<Menu> activatedMenu(final Boolean activated) {
		return new Specification<Menu>() {
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean>get("activated"), activated);
			}
		};
	}
	public static Specification<Menu> searchTerm(final String searchTerm) {
		return new Specification<Menu>() {
			public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String containsLikePattern = getContainsLikePattern(searchTerm);
				return cb.or(
						cb.like(cb.lower(root.<String>get("name")), containsLikePattern),
						cb.like(cb.lower(root.<String>get("description")), containsLikePattern));
			}
		};
	}
	private static String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		}
		else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
}
