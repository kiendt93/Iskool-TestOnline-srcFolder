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

import com.vietnamobile.NoSuchOnlinetestEntryException;

import com.vietnamobile.model.OnlinetestEntry;
import com.vietnamobile.model.impl.OnlinetestEntryImpl;
import com.vietnamobile.model.impl.OnlinetestEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the onlinetest entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see OnlinetestEntryPersistence
 * @see OnlinetestEntryUtil
 * @generated
 */
public class OnlinetestEntryPersistenceImpl extends BasePersistenceImpl<OnlinetestEntry>
	implements OnlinetestEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OnlinetestEntryUtil} to access the onlinetest entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OnlinetestEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryModelImpl.FINDER_CACHE_ENABLED,
			OnlinetestEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryModelImpl.FINDER_CACHE_ENABLED,
			OnlinetestEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OnlinetestEntryPersistenceImpl() {
		setModelClass(OnlinetestEntry.class);
	}

	/**
	 * Caches the onlinetest entry in the entity cache if it is enabled.
	 *
	 * @param onlinetestEntry the onlinetest entry
	 */
	@Override
	public void cacheResult(OnlinetestEntry onlinetestEntry) {
		EntityCacheUtil.putResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryImpl.class, onlinetestEntry.getPrimaryKey(),
			onlinetestEntry);

		onlinetestEntry.resetOriginalValues();
	}

	/**
	 * Caches the onlinetest entries in the entity cache if it is enabled.
	 *
	 * @param onlinetestEntries the onlinetest entries
	 */
	@Override
	public void cacheResult(List<OnlinetestEntry> onlinetestEntries) {
		for (OnlinetestEntry onlinetestEntry : onlinetestEntries) {
			if (EntityCacheUtil.getResult(
						OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
						OnlinetestEntryImpl.class,
						onlinetestEntry.getPrimaryKey()) == null) {
				cacheResult(onlinetestEntry);
			}
			else {
				onlinetestEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all onlinetest entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OnlinetestEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OnlinetestEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the onlinetest entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OnlinetestEntry onlinetestEntry) {
		EntityCacheUtil.removeResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryImpl.class, onlinetestEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OnlinetestEntry> onlinetestEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OnlinetestEntry onlinetestEntry : onlinetestEntries) {
			EntityCacheUtil.removeResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
				OnlinetestEntryImpl.class, onlinetestEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new onlinetest entry with the primary key. Does not add the onlinetest entry to the database.
	 *
	 * @param onlinetestEntryId the primary key for the new onlinetest entry
	 * @return the new onlinetest entry
	 */
	@Override
	public OnlinetestEntry create(long onlinetestEntryId) {
		OnlinetestEntry onlinetestEntry = new OnlinetestEntryImpl();

		onlinetestEntry.setNew(true);
		onlinetestEntry.setPrimaryKey(onlinetestEntryId);

		return onlinetestEntry;
	}

	/**
	 * Removes the onlinetest entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param onlinetestEntryId the primary key of the onlinetest entry
	 * @return the onlinetest entry that was removed
	 * @throws com.vietnamobile.NoSuchOnlinetestEntryException if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry remove(long onlinetestEntryId)
		throws NoSuchOnlinetestEntryException, SystemException {
		return remove((Serializable)onlinetestEntryId);
	}

	/**
	 * Removes the onlinetest entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the onlinetest entry
	 * @return the onlinetest entry that was removed
	 * @throws com.vietnamobile.NoSuchOnlinetestEntryException if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry remove(Serializable primaryKey)
		throws NoSuchOnlinetestEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OnlinetestEntry onlinetestEntry = (OnlinetestEntry)session.get(OnlinetestEntryImpl.class,
					primaryKey);

			if (onlinetestEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOnlinetestEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(onlinetestEntry);
		}
		catch (NoSuchOnlinetestEntryException nsee) {
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
	protected OnlinetestEntry removeImpl(OnlinetestEntry onlinetestEntry)
		throws SystemException {
		onlinetestEntry = toUnwrappedModel(onlinetestEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(onlinetestEntry)) {
				onlinetestEntry = (OnlinetestEntry)session.get(OnlinetestEntryImpl.class,
						onlinetestEntry.getPrimaryKeyObj());
			}

			if (onlinetestEntry != null) {
				session.delete(onlinetestEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (onlinetestEntry != null) {
			clearCache(onlinetestEntry);
		}

		return onlinetestEntry;
	}

	@Override
	public OnlinetestEntry updateImpl(
		com.vietnamobile.model.OnlinetestEntry onlinetestEntry)
		throws SystemException {
		onlinetestEntry = toUnwrappedModel(onlinetestEntry);

		boolean isNew = onlinetestEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (onlinetestEntry.isNew()) {
				session.save(onlinetestEntry);

				onlinetestEntry.setNew(false);
			}
			else {
				session.merge(onlinetestEntry);
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

		EntityCacheUtil.putResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
			OnlinetestEntryImpl.class, onlinetestEntry.getPrimaryKey(),
			onlinetestEntry);

		return onlinetestEntry;
	}

	protected OnlinetestEntry toUnwrappedModel(OnlinetestEntry onlinetestEntry) {
		if (onlinetestEntry instanceof OnlinetestEntryImpl) {
			return onlinetestEntry;
		}

		OnlinetestEntryImpl onlinetestEntryImpl = new OnlinetestEntryImpl();

		onlinetestEntryImpl.setNew(onlinetestEntry.isNew());
		onlinetestEntryImpl.setPrimaryKey(onlinetestEntry.getPrimaryKey());

		onlinetestEntryImpl.setOnlinetestEntryId(onlinetestEntry.getOnlinetestEntryId());
		onlinetestEntryImpl.setGroupId(onlinetestEntry.getGroupId());
		onlinetestEntryImpl.setCompanyId(onlinetestEntry.getCompanyId());
		onlinetestEntryImpl.setUserId(onlinetestEntry.getUserId());
		onlinetestEntryImpl.setUserName(onlinetestEntry.getUserName());
		onlinetestEntryImpl.setCreateDate(onlinetestEntry.getCreateDate());
		onlinetestEntryImpl.setModifiedDate(onlinetestEntry.getModifiedDate());
		onlinetestEntryImpl.setQuestionId(onlinetestEntry.getQuestionId());
		onlinetestEntryImpl.setTestStartTime(onlinetestEntry.getTestStartTime());
		onlinetestEntryImpl.setTestEndTime(onlinetestEntry.getTestEndTime());
		onlinetestEntryImpl.setDuration(onlinetestEntry.getDuration());
		onlinetestEntryImpl.setNumberOfQuestions(onlinetestEntry.getNumberOfQuestions());
		onlinetestEntryImpl.setTitle(onlinetestEntry.getTitle());
		onlinetestEntryImpl.setPoint(onlinetestEntry.getPoint());
		onlinetestEntryImpl.setStatus(onlinetestEntry.getStatus());

		return onlinetestEntryImpl;
	}

	/**
	 * Returns the onlinetest entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the onlinetest entry
	 * @return the onlinetest entry
	 * @throws com.vietnamobile.NoSuchOnlinetestEntryException if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOnlinetestEntryException, SystemException {
		OnlinetestEntry onlinetestEntry = fetchByPrimaryKey(primaryKey);

		if (onlinetestEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOnlinetestEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return onlinetestEntry;
	}

	/**
	 * Returns the onlinetest entry with the primary key or throws a {@link com.vietnamobile.NoSuchOnlinetestEntryException} if it could not be found.
	 *
	 * @param onlinetestEntryId the primary key of the onlinetest entry
	 * @return the onlinetest entry
	 * @throws com.vietnamobile.NoSuchOnlinetestEntryException if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry findByPrimaryKey(long onlinetestEntryId)
		throws NoSuchOnlinetestEntryException, SystemException {
		return findByPrimaryKey((Serializable)onlinetestEntryId);
	}

	/**
	 * Returns the onlinetest entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the onlinetest entry
	 * @return the onlinetest entry, or <code>null</code> if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		OnlinetestEntry onlinetestEntry = (OnlinetestEntry)EntityCacheUtil.getResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
				OnlinetestEntryImpl.class, primaryKey);

		if (onlinetestEntry == _nullOnlinetestEntry) {
			return null;
		}

		if (onlinetestEntry == null) {
			Session session = null;

			try {
				session = openSession();

				onlinetestEntry = (OnlinetestEntry)session.get(OnlinetestEntryImpl.class,
						primaryKey);

				if (onlinetestEntry != null) {
					cacheResult(onlinetestEntry);
				}
				else {
					EntityCacheUtil.putResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
						OnlinetestEntryImpl.class, primaryKey,
						_nullOnlinetestEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OnlinetestEntryModelImpl.ENTITY_CACHE_ENABLED,
					OnlinetestEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return onlinetestEntry;
	}

	/**
	 * Returns the onlinetest entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param onlinetestEntryId the primary key of the onlinetest entry
	 * @return the onlinetest entry, or <code>null</code> if a onlinetest entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OnlinetestEntry fetchByPrimaryKey(long onlinetestEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)onlinetestEntryId);
	}

	/**
	 * Returns all the onlinetest entries.
	 *
	 * @return the onlinetest entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the onlinetest entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.OnlinetestEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of onlinetest entries
	 * @param end the upper bound of the range of onlinetest entries (not inclusive)
	 * @return the range of onlinetest entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the onlinetest entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.OnlinetestEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of onlinetest entries
	 * @param end the upper bound of the range of onlinetest entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of onlinetest entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OnlinetestEntry> findAll(int start, int end,
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

		List<OnlinetestEntry> list = (List<OnlinetestEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ONLINETESTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ONLINETESTENTRY;

				if (pagination) {
					sql = sql.concat(OnlinetestEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OnlinetestEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OnlinetestEntry>(list);
				}
				else {
					list = (List<OnlinetestEntry>)QueryUtil.list(q,
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
	 * Removes all the onlinetest entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (OnlinetestEntry onlinetestEntry : findAll()) {
			remove(onlinetestEntry);
		}
	}

	/**
	 * Returns the number of onlinetest entries.
	 *
	 * @return the number of onlinetest entries
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

				Query q = session.createQuery(_SQL_COUNT_ONLINETESTENTRY);

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
	 * Initializes the onlinetest entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.OnlinetestEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OnlinetestEntry>> listenersList = new ArrayList<ModelListener<OnlinetestEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OnlinetestEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OnlinetestEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ONLINETESTENTRY = "SELECT onlinetestEntry FROM OnlinetestEntry onlinetestEntry";
	private static final String _SQL_COUNT_ONLINETESTENTRY = "SELECT COUNT(onlinetestEntry) FROM OnlinetestEntry onlinetestEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "onlinetestEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OnlinetestEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OnlinetestEntryPersistenceImpl.class);
	private static OnlinetestEntry _nullOnlinetestEntry = new OnlinetestEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OnlinetestEntry> toCacheModel() {
				return _nullOnlinetestEntryCacheModel;
			}
		};

	private static CacheModel<OnlinetestEntry> _nullOnlinetestEntryCacheModel = new CacheModel<OnlinetestEntry>() {
			@Override
			public OnlinetestEntry toEntityModel() {
				return _nullOnlinetestEntry;
			}
		};
}