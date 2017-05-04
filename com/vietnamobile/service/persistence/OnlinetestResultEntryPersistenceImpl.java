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

import com.vietnamobile.NoSuchOnlinetestResultEntryException;

import com.vietnamobile.model.OnlinetestResultEntry;
import com.vietnamobile.model.impl.OnlinetestResultEntryImpl;
import com.vietnamobile.model.impl.OnlinetestResultEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the onlinetest result entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see OnlinetestResultEntryPersistence
 * @see OnlinetestResultEntryUtil
 * @generated
 */
public class OnlinetestResultEntryPersistenceImpl extends BasePersistenceImpl<OnlinetestResultEntry>
	implements OnlinetestResultEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OnlinetestResultEntryUtil} to access the onlinetest result entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OnlinetestResultEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryModelImpl.FINDER_CACHE_ENABLED,
			OnlinetestResultEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryModelImpl.FINDER_CACHE_ENABLED,
			OnlinetestResultEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OnlinetestResultEntryPersistenceImpl() {
		setModelClass(OnlinetestResultEntry.class);
	}

	/**
	 * Caches the onlinetest result entry in the entity cache if it is enabled.
	 *
	 * @param onlinetestResultEntry the onlinetest result entry
	 */
	@Override
	public void cacheResult(OnlinetestResultEntry onlinetestResultEntry) {
		EntityCacheUtil.putResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryImpl.class,
			onlinetestResultEntry.getPrimaryKey(), onlinetestResultEntry);

		onlinetestResultEntry.resetOriginalValues();
	}

	/**
	 * Caches the onlinetest result entries in the entity cache if it is enabled.
	 *
	 * @param onlinetestResultEntries the onlinetest result entries
	 */
	@Override
	public void cacheResult(List<OnlinetestResultEntry> onlinetestResultEntries) {
		for (OnlinetestResultEntry onlinetestResultEntry : onlinetestResultEntries) {
			if (EntityCacheUtil.getResult(
						OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
						OnlinetestResultEntryImpl.class,
						onlinetestResultEntry.getPrimaryKey()) == null) {
				cacheResult(onlinetestResultEntry);
			}
			else {
				onlinetestResultEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all onlinetest result entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OnlinetestResultEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OnlinetestResultEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the onlinetest result entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OnlinetestResultEntry onlinetestResultEntry) {
		EntityCacheUtil.removeResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryImpl.class,
			onlinetestResultEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OnlinetestResultEntry> onlinetestResultEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OnlinetestResultEntry onlinetestResultEntry : onlinetestResultEntries) {
			EntityCacheUtil.removeResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
				OnlinetestResultEntryImpl.class,
				onlinetestResultEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new onlinetest result entry with the primary key. Does not add the onlinetest result entry to the database.
	 *
	 * @param onlinetestResultEntryId the primary key for the new onlinetest result entry
	 * @return the new onlinetest result entry
	 */
	@Override
	public OnlinetestResultEntry create(long onlinetestResultEntryId) {
		OnlinetestResultEntry onlinetestResultEntry = new OnlinetestResultEntryImpl();

		onlinetestResultEntry.setNew(true);
		onlinetestResultEntry.setPrimaryKey(onlinetestResultEntryId);

		return onlinetestResultEntry;
	}

	/**
	 * Removes the onlinetest result entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param onlinetestResultEntryId the primary key of the onlinetest result entry
	 * @return the onlinetest result entry that was removed
	 * @throws com.vietnamobile.NoSuchOnlinetestResultEntryException if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry remove(long onlinetestResultEntryId)
		throws NoSuchOnlinetestResultEntryException, SystemException {
		return remove((Serializable)onlinetestResultEntryId);
	}

	/**
	 * Removes the onlinetest result entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the onlinetest result entry
	 * @return the onlinetest result entry that was removed
	 * @throws com.vietnamobile.NoSuchOnlinetestResultEntryException if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry remove(Serializable primaryKey)
		throws NoSuchOnlinetestResultEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OnlinetestResultEntry onlinetestResultEntry = (OnlinetestResultEntry)session.get(OnlinetestResultEntryImpl.class,
					primaryKey);

			if (onlinetestResultEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOnlinetestResultEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(onlinetestResultEntry);
		}
		catch (NoSuchOnlinetestResultEntryException nsee) {
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
	protected OnlinetestResultEntry removeImpl(
		OnlinetestResultEntry onlinetestResultEntry) throws SystemException {
		onlinetestResultEntry = toUnwrappedModel(onlinetestResultEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(onlinetestResultEntry)) {
				onlinetestResultEntry = (OnlinetestResultEntry)session.get(OnlinetestResultEntryImpl.class,
						onlinetestResultEntry.getPrimaryKeyObj());
			}

			if (onlinetestResultEntry != null) {
				session.delete(onlinetestResultEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (onlinetestResultEntry != null) {
			clearCache(onlinetestResultEntry);
		}

		return onlinetestResultEntry;
	}

	@Override
	public OnlinetestResultEntry updateImpl(
		com.vietnamobile.model.OnlinetestResultEntry onlinetestResultEntry)
		throws SystemException {
		onlinetestResultEntry = toUnwrappedModel(onlinetestResultEntry);

		boolean isNew = onlinetestResultEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (onlinetestResultEntry.isNew()) {
				session.save(onlinetestResultEntry);

				onlinetestResultEntry.setNew(false);
			}
			else {
				session.merge(onlinetestResultEntry);
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

		EntityCacheUtil.putResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestResultEntryImpl.class,
			onlinetestResultEntry.getPrimaryKey(), onlinetestResultEntry);

		return onlinetestResultEntry;
	}

	protected OnlinetestResultEntry toUnwrappedModel(
		OnlinetestResultEntry onlinetestResultEntry) {
		if (onlinetestResultEntry instanceof OnlinetestResultEntryImpl) {
			return onlinetestResultEntry;
		}

		OnlinetestResultEntryImpl onlinetestResultEntryImpl = new OnlinetestResultEntryImpl();

		onlinetestResultEntryImpl.setNew(onlinetestResultEntry.isNew());
		onlinetestResultEntryImpl.setPrimaryKey(onlinetestResultEntry.getPrimaryKey());

		onlinetestResultEntryImpl.setOnlinetestResultEntryId(onlinetestResultEntry.getOnlinetestResultEntryId());
		onlinetestResultEntryImpl.setGroupId(onlinetestResultEntry.getGroupId());
		onlinetestResultEntryImpl.setCompanyId(onlinetestResultEntry.getCompanyId());
		onlinetestResultEntryImpl.setUserId(onlinetestResultEntry.getUserId());
		onlinetestResultEntryImpl.setUserName(onlinetestResultEntry.getUserName());
		onlinetestResultEntryImpl.setCreateDate(onlinetestResultEntry.getCreateDate());
		onlinetestResultEntryImpl.setModifiedDate(onlinetestResultEntry.getModifiedDate());
		onlinetestResultEntryImpl.setOnlineTestEntryId(onlinetestResultEntry.getOnlineTestEntryId());
		onlinetestResultEntryImpl.setScore(onlinetestResultEntry.getScore());
		onlinetestResultEntryImpl.setAnswerSelected(onlinetestResultEntry.getAnswerSelected());
		onlinetestResultEntryImpl.setNumberOfWrongAnswer(onlinetestResultEntry.getNumberOfWrongAnswer());
		onlinetestResultEntryImpl.setTimeRemaining(onlinetestResultEntry.getTimeRemaining());
		onlinetestResultEntryImpl.setExamType(onlinetestResultEntry.getExamType());
		onlinetestResultEntryImpl.setQuestionIds(onlinetestResultEntry.getQuestionIds());

		return onlinetestResultEntryImpl;
	}

	/**
	 * Returns the onlinetest result entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the onlinetest result entry
	 * @return the onlinetest result entry
	 * @throws com.vietnamobile.NoSuchOnlinetestResultEntryException if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOnlinetestResultEntryException, SystemException {
		OnlinetestResultEntry onlinetestResultEntry = fetchByPrimaryKey(primaryKey);

		if (onlinetestResultEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOnlinetestResultEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return onlinetestResultEntry;
	}

	/**
	 * Returns the onlinetest result entry with the primary key or throws a {@link com.vietnamobile.NoSuchOnlinetestResultEntryException} if it could not be found.
	 *
	 * @param onlinetestResultEntryId the primary key of the onlinetest result entry
	 * @return the onlinetest result entry
	 * @throws com.vietnamobile.NoSuchOnlinetestResultEntryException if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry findByPrimaryKey(long onlinetestResultEntryId)
		throws NoSuchOnlinetestResultEntryException, SystemException {
		return findByPrimaryKey((Serializable)onlinetestResultEntryId);
	}

	/**
	 * Returns the onlinetest result entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the onlinetest result entry
	 * @return the onlinetest result entry, or <code>null</code> if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		OnlinetestResultEntry onlinetestResultEntry = (OnlinetestResultEntry)EntityCacheUtil.getResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
				OnlinetestResultEntryImpl.class, primaryKey);

		if (onlinetestResultEntry == _nullOnlinetestResultEntry) {
			return null;
		}

		if (onlinetestResultEntry == null) {
			Session session = null;

			try {
				session = openSession();

				onlinetestResultEntry = (OnlinetestResultEntry)session.get(OnlinetestResultEntryImpl.class,
						primaryKey);

				if (onlinetestResultEntry != null) {
					cacheResult(onlinetestResultEntry);
				}
				else {
					EntityCacheUtil.putResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
						OnlinetestResultEntryImpl.class, primaryKey,
						_nullOnlinetestResultEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OnlinetestResultEntryModelImpl.ENTITY_CACHE_ENABLED,
					OnlinetestResultEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return onlinetestResultEntry;
	}

	/**
	 * Returns the onlinetest result entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param onlinetestResultEntryId the primary key of the onlinetest result entry
	 * @return the onlinetest result entry, or <code>null</code> if a onlinetest result entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestResultEntry fetchByPrimaryKey(long onlinetestResultEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)onlinetestResultEntryId);
	}

	/**
	 * Returns all the onlinetest result entries.
	 *
	 * @return the onlinetest result entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestResultEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the onlinetest result entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.OnlinetestResultEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of onlinetest result entries
	 * @param end the upper bound of the range of onlinetest result entries (not inclusive)
	 * @return the range of onlinetest result entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestResultEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the onlinetest result entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.OnlinetestResultEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of onlinetest result entries
	 * @param end the upper bound of the range of onlinetest result entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of onlinetest result entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestResultEntry> findAll(int start, int end,
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

		List<OnlinetestResultEntry> list = (List<OnlinetestResultEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ONLINETESTRESULTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ONLINETESTRESULTENTRY;

				if (pagination) {
					sql = sql.concat(OnlinetestResultEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OnlinetestResultEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OnlinetestResultEntry>(list);
				}
				else {
					list = (List<OnlinetestResultEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the onlinetest result entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (OnlinetestResultEntry onlinetestResultEntry : findAll()) {
			remove(onlinetestResultEntry);
		}
	}

	/**
	 * Returns the number of onlinetest result entries.
	 *
	 * @return the number of onlinetest result entries
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

				Query q = session.createQuery(_SQL_COUNT_ONLINETESTRESULTENTRY);

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
	 * Initializes the onlinetest result entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.OnlinetestResultEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OnlinetestResultEntry>> listenersList = new ArrayList<ModelListener<OnlinetestResultEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OnlinetestResultEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OnlinetestResultEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ONLINETESTRESULTENTRY = "SELECT onlinetestResultEntry FROM OnlinetestResultEntry onlinetestResultEntry";
	private static final String _SQL_COUNT_ONLINETESTRESULTENTRY = "SELECT COUNT(onlinetestResultEntry) FROM OnlinetestResultEntry onlinetestResultEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "onlinetestResultEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OnlinetestResultEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OnlinetestResultEntryPersistenceImpl.class);
	private static OnlinetestResultEntry _nullOnlinetestResultEntry = new OnlinetestResultEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OnlinetestResultEntry> toCacheModel() {
				return _nullOnlinetestResultEntryCacheModel;
			}
		};

	private static CacheModel<OnlinetestResultEntry> _nullOnlinetestResultEntryCacheModel =
		new CacheModel<OnlinetestResultEntry>() {
			@Override
			public OnlinetestResultEntry toEntityModel() {
				return _nullOnlinetestResultEntry;
			}
		};
}