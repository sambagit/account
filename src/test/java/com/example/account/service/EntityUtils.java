package com.example.account.service;

import com.example.account.acc.model.BaseEntity;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.Collection;

public abstract class EntityUtils {

	public static <T extends BaseEntity> T getById(Collection<T> entities, Class<T> entityClass, int entityId)
			throws ObjectRetrievalFailureException {
		for (T entity : entities) {
			if (entity.getId() == entityId && entityClass.isInstance(entity)) {
				return entity;
			}
		}
		throw new ObjectRetrievalFailureException(entityClass, entityId);
	}

}
