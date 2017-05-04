/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vietnamobile.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.vietnamobile.NoSuchSubjectEntryException;

import com.vietnamobile.model.SubjectEntry;
import com.vietnamobile.model.impl.SubjectEntryImpl;
import com.vietnamobile.model.impl.SubjectEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the subject entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see SubjectEntryPersistence
 * @see SubjectEntryUtil
 * @generated
 */
public class SubjectEntryPersistenceImpl extends BasePersistenceImpl<SubjectEntry>
	implements SubjectEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SubjectEntryUtil} to access the subject entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SubjectEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryModelImpl.FINDER_CACHE_ENABLED, SubjectEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryModelImpl.FINDER_CACHE_ENABLED, SubjectEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SubjectEntryPersistenceImpl() {
		setModelClass(SubjectEntry.class);
	}

	/**
	 * Caches the subject entry in the entity cache if it is enabled.
	 *
	 * @param subjectEntry the subject entry
	 */
	@Override
	public void cacheResult(SubjectEntry subjectEntry) {
		EntityCacheUtil.putResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryImpl.class, subjectEntry.getPrimaryKey(), subjectEntry);

		subjectEntry.resetOriginalValues();
	}

	/**
	 * Caches the subject entries in the entity cache if it is enabled.
	 *
	 * @param subjectEntries the subject entries
	 */
	@Override
	public void cacheResult(List<SubjectEntry> subjectEntries) {
		for (SubjectEntry subjectEntry : subjectEntries) {
			if (EntityCacheUtil.getResult(
						SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
						SubjectEntryImpl.class, subjectEntry.getPrimaryKey()) == null) {
				cacheResult(subjectEntry);
			}
			else {
				subjectEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all subject entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SubjectEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SubjectEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the subject entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubjectEntry subjectEntry) {
		EntityCacheUtil.removeResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryImpl.class, subjectEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SubjectEntry> subjectEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SubjectEntry subjectEntry : subjectEntries) {
			EntityCacheUtil.removeResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
				SubjectEntryImpl.class, subjectEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new subject entry with the primary key. Does not add the subject entry to the database.
	 *
	 * @param subjectEntryId the primary key for the new subject entry
	 * @return the new subject entry
	 */
	@Override
	public SubjectEntry create(long subjectEntryId) {
		SubjectEntry subjectEntry = new SubjectEntryImpl();

		subjectEntry.setNew(true);
		subjectEntry.setPrimaryKey(subjectEntryId);

		return subjectEntry;
	}

	/**
	 * Removes the subject entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subjectEntryId the primary key of the subject entry
	 * @return the subject entry that was removed
	 * @throws com.vietnamobile.NoSuchSubjectEntryException if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry remove(long subjectEntryId)
		throws NoSuchSubjectEntryException, SystemException {
		return remove((Serializable)subjectEntryId);
	}

	/**
	 * Removes the subject entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the subject entry
	 * @return the subject entry that was removed
	 * @throws com.vietnamobile.NoSuchSubjectEntryException if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry remove(Serializable primaryKey)
		throws NoSuchSubjectEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SubjectEntry subjectEntry = (SubjectEntry)session.get(SubjectEntryImpl.class,
					primaryKey);

			if (subjectEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubjectEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(subjectEntry);
		}
		catch (NoSuchSubjectEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SubjectEntry removeImpl(SubjectEntry subjectEntry)
		throws SystemException {
		subjectEntry = toUnwrappedModel(subjectEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subjectEntry)) {
				subjectEntry = (SubjectEntry)session.get(SubjectEntryImpl.class,
						subjectEntry.getPrimaryKeyObj());
			}

			if (subjectEntry != null) {
				session.delete(subjectEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (subjectEntry != null) {
			clearCache(subjectEntry);
		}

		return subjectEntry;
	}

	@Override
	public SubjectEntry updateImpl(
		com.vietnamobile.model.SubjectEntry subjectEntry)
		throws SystemException {
		subjectEntry = toUnwrappedModel(subjectEntry);

		boolean isNew = subjectEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (subjectEntry.isNew()) {
				session.save(subjectEntry);

				subjectEntry.setNew(false);
			}
			else {
				session.merge(subjectEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
			SubjectEntryImpl.class, subjectEntry.getPrimaryKey(), subjectEntry);

		return subjectEntry;
	}

	protected SubjectEntry toUnwrappedModel(SubjectEntry subjectEntry) {
		if (subjectEntry instanceof SubjectEntryImpl) {
			return subjectEntry;
		}

		SubjectEntryImpl subjectEntryImpl = new SubjectEntryImpl();

		subjectEntryImpl.setNew(subjectEntry.isNew());
		subjectEntryImpl.setPrimaryKey(subjectEntry.getPrimaryKey());

		subjectEntryImpl.setSubjectEntryId(subjectEntry.getSubjectEntryId());
		subjectEntryImpl.setGroupId(subjectEntry.getGroupId());
		subjectEntryImpl.setCompanyId(subjectEntry.getCompanyId());
		subjectEntryImpl.setUserId(subjectEntry.getUserId());
		subjectEntryImpl.setUserName(subjectEntry.getUserName());
		subjectEntryImpl.setCreateDate(subjectEntry.getCreateDate());
		subjectEntryImpl.setModifiedDate(subjectEntry.getModifiedDate());
		subjectEntryImpl.setCourse(subjectEntry.getCourse());
		subjectEntryImpl.setSubject(subjectEntry.getSubject());
		subjectEntryImpl.setDescription(subjectEntry.getDescription());
		subjectEntryImpl.setExamType(subjectEntry.getExamType());
		subjectEntryImpl.setTotalQuestion(subjectEntry.getTotalQuestion());
		subjectEntryImpl.setStatus(subjectEntry.getStatus());

		return subjectEntryImpl;
	}

	/**
	 * Returns the subject entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the subject entry
	 * @return the subject entry
	 * @throws com.vietnamobile.NoSuchSubjectEntryException if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubjectEntryException, SystemException {
		SubjectEntry subjectEntry = fetchByPrimaryKey(primaryKey);

		if (subjectEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubjectEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return subjectEntry;
	}

	/**
	 * Returns the subject entry with the primary key or throws a {@link com.vietnamobile.NoSuchSubjectEntryException} if it could not be found.
	 *
	 * @param subjectEntryId the primary key of the subject entry
	 * @return the subject entry
	 * @throws com.vietnamobile.NoSuchSubjectEntryException if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry findByPrimaryKey(long subjectEntryId)
		throws NoSuchSubjectEntryException, SystemException {
		return findByPrimaryKey((Serializable)subjectEntryId);
	}

	/**
	 * Returns the subject entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the subject entry
	 * @return the subject entry, or <code>null</code> if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SubjectEntry subjectEntry = (SubjectEntry)EntityCacheUtil.getResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
				SubjectEntryImpl.class, primaryKey);

		if (subjectEntry == _nullSubjectEntry) {
			return null;
		}

		if (subjectEntry == null) {
			Session session = null;

			try {
				session = openSession();

				subjectEntry = (SubjectEntry)session.get(SubjectEntryImpl.class,
						primaryKey);

				if (subjectEntry != null) {
					cacheResult(subjectEntry);
				}
				else {
					EntityCacheUtil.putResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
						SubjectEntryImpl.class, primaryKey, _nullSubjectEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SubjectEntryModelImpl.ENTITY_CACHE_ENABLED,
					SubjectEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return subjectEntry;
	}

	/**
	 * Returns the subject entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subjectEntryId the primary key of the subject entry
	 * @return the subject entry, or <code>null</code> if a subject entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SubjectEntry fetchByPrimaryKey(long subjectEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)subjectEntryId);
	}

	/**
	 * Returns all the subject entries.
	 *
	 * @return the subject entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SubjectEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subject entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.SubjectEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject entries
	 * @param end the upper bound of the range of subject entries (not inclusive)
	 * @return the range of subject entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SubjectEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the subject entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.SubjectEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of subject entries
	 * @param end the upper bound of the range of subject entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subject entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SubjectEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SubjectEntry> list = (List<SubjectEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUBJECTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUBJECTENTRY;

				if (pagination) {
					sql = sql.concat(SubjectEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SubjectEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SubjectEntry>(list);
				}
				else {
					list = (List<SubjectEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the subject entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SubjectEntry subjectEntry : findAll()) {
			remove(subjectEntry);
		}
	}

	/**
	 * Returns the number of subject entries.
	 *
	 * @return the number of subject entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUBJECTENTRY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the subject entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.SubjectEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SubjectEntry>> listenersList = new ArrayList<ModelListener<SubjectEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SubjectEntry>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SubjectEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SUBJECTENTRY = "SELECT subjectEntry FROM SubjectEntry subjectEntry";
	private static final String _SQL_COUNT_SUBJECTENTRY = "SELECT COUNT(subjectEntry) FROM SubjectEntry subjectEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "subjectEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SubjectEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SubjectEntryPersistenceImpl.class);
	private static SubjectEntry _nullSubjectEntry = new SubjectEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SubjectEntry> toCacheModel() {
				return _nullSubjectEntryCacheModel;
			}
		};

	private static CacheModel<SubjectEntry> _nullSubjectEntryCacheModel = new CacheModel<SubjectEntry>() {
			@Override
			public SubjectEntry toEntityModel() {
				return _nullSubjectEntry;
			}
		};
}