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

import com.vietnamobile.NoSuchVnmOnlintestReportEntryException;

import com.vietnamobile.model.VnmOnlintestReportEntry;
import com.vietnamobile.model.impl.VnmOnlintestReportEntryImpl;
import com.vietnamobile.model.impl.VnmOnlintestReportEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the vnm onlintest report entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see VnmOnlintestReportEntryPersistence
 * @see VnmOnlintestReportEntryUtil
 * @generated
 */
public class VnmOnlintestReportEntryPersistenceImpl extends BasePersistenceImpl<VnmOnlintestReportEntry>
	implements VnmOnlintestReportEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VnmOnlintestReportEntryUtil} to access the vnm onlintest report entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VnmOnlintestReportEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryModelImpl.FINDER_CACHE_ENABLED,
			VnmOnlintestReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryModelImpl.FINDER_CACHE_ENABLED,
			VnmOnlintestReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VnmOnlintestReportEntryPersistenceImpl() {
		setModelClass(VnmOnlintestReportEntry.class);
	}

	/**
	 * Caches the vnm onlintest report entry in the entity cache if it is enabled.
	 *
	 * @param vnmOnlintestReportEntry the vnm onlintest report entry
	 */
	@Override
	public void cacheResult(VnmOnlintestReportEntry vnmOnlintestReportEntry) {
		EntityCacheUtil.putResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryImpl.class,
			vnmOnlintestReportEntry.getPrimaryKey(), vnmOnlintestReportEntry);

		vnmOnlintestReportEntry.resetOriginalValues();
	}

	/**
	 * Caches the vnm onlintest report entries in the entity cache if it is enabled.
	 *
	 * @param vnmOnlintestReportEntries the vnm onlintest report entries
	 */
	@Override
	public void cacheResult(
		List<VnmOnlintestReportEntry> vnmOnlintestReportEntries) {
		for (VnmOnlintestReportEntry vnmOnlintestReportEntry : vnmOnlintestReportEntries) {
			if (EntityCacheUtil.getResult(
						VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
						VnmOnlintestReportEntryImpl.class,
						vnmOnlintestReportEntry.getPrimaryKey()) == null) {
				cacheResult(vnmOnlintestReportEntry);
			}
			else {
				vnmOnlintestReportEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vnm onlintest report entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(VnmOnlintestReportEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(VnmOnlintestReportEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vnm onlintest report entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VnmOnlintestReportEntry vnmOnlintestReportEntry) {
		EntityCacheUtil.removeResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryImpl.class,
			vnmOnlintestReportEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VnmOnlintestReportEntry> vnmOnlintestReportEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VnmOnlintestReportEntry vnmOnlintestReportEntry : vnmOnlintestReportEntries) {
			EntityCacheUtil.removeResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
				VnmOnlintestReportEntryImpl.class,
				vnmOnlintestReportEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vnm onlintest report entry with the primary key. Does not add the vnm onlintest report entry to the database.
	 *
	 * @param vnmOnlintestReportEntryId the primary key for the new vnm onlintest report entry
	 * @return the new vnm onlintest report entry
	 */
	@Override
	public VnmOnlintestReportEntry create(long vnmOnlintestReportEntryId) {
		VnmOnlintestReportEntry vnmOnlintestReportEntry = new VnmOnlintestReportEntryImpl();

		vnmOnlintestReportEntry.setNew(true);
		vnmOnlintestReportEntry.setPrimaryKey(vnmOnlintestReportEntryId);

		return vnmOnlintestReportEntry;
	}

	/**
	 * Removes the vnm onlintest report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param vnmOnlintestReportEntryId the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry that was removed
	 * @throws com.vietnamobile.NoSuchVnmOnlintestReportEntryException if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry remove(long vnmOnlintestReportEntryId)
		throws NoSuchVnmOnlintestReportEntryException, SystemException {
		return remove((Serializable)vnmOnlintestReportEntryId);
	}

	/**
	 * Removes the vnm onlintest report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry that was removed
	 * @throws com.vietnamobile.NoSuchVnmOnlintestReportEntryException if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry remove(Serializable primaryKey)
		throws NoSuchVnmOnlintestReportEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			VnmOnlintestReportEntry vnmOnlintestReportEntry = (VnmOnlintestReportEntry)session.get(VnmOnlintestReportEntryImpl.class,
					primaryKey);

			if (vnmOnlintestReportEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVnmOnlintestReportEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vnmOnlintestReportEntry);
		}
		catch (NoSuchVnmOnlintestReportEntryException nsee) {
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
	protected VnmOnlintestReportEntry removeImpl(
		VnmOnlintestReportEntry vnmOnlintestReportEntry)
		throws SystemException {
		vnmOnlintestReportEntry = toUnwrappedModel(vnmOnlintestReportEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vnmOnlintestReportEntry)) {
				vnmOnlintestReportEntry = (VnmOnlintestReportEntry)session.get(VnmOnlintestReportEntryImpl.class,
						vnmOnlintestReportEntry.getPrimaryKeyObj());
			}

			if (vnmOnlintestReportEntry != null) {
				session.delete(vnmOnlintestReportEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vnmOnlintestReportEntry != null) {
			clearCache(vnmOnlintestReportEntry);
		}

		return vnmOnlintestReportEntry;
	}

	@Override
	public VnmOnlintestReportEntry updateImpl(
		com.vietnamobile.model.VnmOnlintestReportEntry vnmOnlintestReportEntry)
		throws SystemException {
		vnmOnlintestReportEntry = toUnwrappedModel(vnmOnlintestReportEntry);

		boolean isNew = vnmOnlintestReportEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vnmOnlintestReportEntry.isNew()) {
				session.save(vnmOnlintestReportEntry);

				vnmOnlintestReportEntry.setNew(false);
			}
			else {
				session.merge(vnmOnlintestReportEntry);
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

		EntityCacheUtil.putResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmOnlintestReportEntryImpl.class,
			vnmOnlintestReportEntry.getPrimaryKey(), vnmOnlintestReportEntry);

		return vnmOnlintestReportEntry;
	}

	protected VnmOnlintestReportEntry toUnwrappedModel(
		VnmOnlintestReportEntry vnmOnlintestReportEntry) {
		if (vnmOnlintestReportEntry instanceof VnmOnlintestReportEntryImpl) {
			return vnmOnlintestReportEntry;
		}

		VnmOnlintestReportEntryImpl vnmOnlintestReportEntryImpl = new VnmOnlintestReportEntryImpl();

		vnmOnlintestReportEntryImpl.setNew(vnmOnlintestReportEntry.isNew());
		vnmOnlintestReportEntryImpl.setPrimaryKey(vnmOnlintestReportEntry.getPrimaryKey());

		vnmOnlintestReportEntryImpl.setVnmOnlintestReportEntryId(vnmOnlintestReportEntry.getVnmOnlintestReportEntryId());
		vnmOnlintestReportEntryImpl.setGroupId(vnmOnlintestReportEntry.getGroupId());
		vnmOnlintestReportEntryImpl.setCompanyId(vnmOnlintestReportEntry.getCompanyId());
		vnmOnlintestReportEntryImpl.setCreateDate(vnmOnlintestReportEntry.getCreateDate());
		vnmOnlintestReportEntryImpl.setModifiedDate(vnmOnlintestReportEntry.getModifiedDate());
		vnmOnlintestReportEntryImpl.setOnlinetestEntryId(vnmOnlintestReportEntry.getOnlinetestEntryId());
		vnmOnlintestReportEntryImpl.setQuestionEntryId(vnmOnlintestReportEntry.getQuestionEntryId());
		vnmOnlintestReportEntryImpl.setCorrectTime(vnmOnlintestReportEntry.getCorrectTime());
		vnmOnlintestReportEntryImpl.setWrongTime(vnmOnlintestReportEntry.getWrongTime());

		return vnmOnlintestReportEntryImpl;
	}

	/**
	 * Returns the vnm onlintest report entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry
	 * @throws com.vietnamobile.NoSuchVnmOnlintestReportEntryException if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVnmOnlintestReportEntryException, SystemException {
		VnmOnlintestReportEntry vnmOnlintestReportEntry = fetchByPrimaryKey(primaryKey);

		if (vnmOnlintestReportEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVnmOnlintestReportEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vnmOnlintestReportEntry;
	}

	/**
	 * Returns the vnm onlintest report entry with the primary key or throws a {@link com.vietnamobile.NoSuchVnmOnlintestReportEntryException} if it could not be found.
	 *
	 * @param vnmOnlintestReportEntryId the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry
	 * @throws com.vietnamobile.NoSuchVnmOnlintestReportEntryException if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry findByPrimaryKey(
		long vnmOnlintestReportEntryId)
		throws NoSuchVnmOnlintestReportEntryException, SystemException {
		return findByPrimaryKey((Serializable)vnmOnlintestReportEntryId);
	}

	/**
	 * Returns the vnm onlintest report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry, or <code>null</code> if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		VnmOnlintestReportEntry vnmOnlintestReportEntry = (VnmOnlintestReportEntry)EntityCacheUtil.getResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
				VnmOnlintestReportEntryImpl.class, primaryKey);

		if (vnmOnlintestReportEntry == _nullVnmOnlintestReportEntry) {
			return null;
		}

		if (vnmOnlintestReportEntry == null) {
			Session session = null;

			try {
				session = openSession();

				vnmOnlintestReportEntry = (VnmOnlintestReportEntry)session.get(VnmOnlintestReportEntryImpl.class,
						primaryKey);

				if (vnmOnlintestReportEntry != null) {
					cacheResult(vnmOnlintestReportEntry);
				}
				else {
					EntityCacheUtil.putResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
						VnmOnlintestReportEntryImpl.class, primaryKey,
						_nullVnmOnlintestReportEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(VnmOnlintestReportEntryModelImpl.ENTITY_CACHE_ENABLED,
					VnmOnlintestReportEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vnmOnlintestReportEntry;
	}

	/**
	 * Returns the vnm onlintest report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param vnmOnlintestReportEntryId the primary key of the vnm onlintest report entry
	 * @return the vnm onlintest report entry, or <code>null</code> if a vnm onlintest report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmOnlintestReportEntry fetchByPrimaryKey(
		long vnmOnlintestReportEntryId) throws SystemException {
		return fetchByPrimaryKey((Serializable)vnmOnlintestReportEntryId);
	}

	/**
	 * Returns all the vnm onlintest report entries.
	 *
	 * @return the vnm onlintest report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmOnlintestReportEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vnm onlintest report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.VnmOnlintestReportEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vnm onlintest report entries
	 * @param end the upper bound of the range of vnm onlintest report entries (not inclusive)
	 * @return the range of vnm onlintest report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmOnlintestReportEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vnm onlintest report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.VnmOnlintestReportEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vnm onlintest report entries
	 * @param end the upper bound of the range of vnm onlintest report entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vnm onlintest report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmOnlintestReportEntry> findAll(int start, int end,
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

		List<VnmOnlintestReportEntry> list = (List<VnmOnlintestReportEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VNMONLINTESTREPORTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VNMONLINTESTREPORTENTRY;

				if (pagination) {
					sql = sql.concat(VnmOnlintestReportEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VnmOnlintestReportEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VnmOnlintestReportEntry>(list);
				}
				else {
					list = (List<VnmOnlintestReportEntry>)QueryUtil.list(q,
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
	 * Removes all the vnm onlintest report entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (VnmOnlintestReportEntry vnmOnlintestReportEntry : findAll()) {
			remove(vnmOnlintestReportEntry);
		}
	}

	/**
	 * Returns the number of vnm onlintest report entries.
	 *
	 * @return the number of vnm onlintest report entries
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

				Query q = session.createQuery(_SQL_COUNT_VNMONLINTESTREPORTENTRY);

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
	 * Initializes the vnm onlintest report entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.VnmOnlintestReportEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<VnmOnlintestReportEntry>> listenersList = new ArrayList<ModelListener<VnmOnlintestReportEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<VnmOnlintestReportEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(VnmOnlintestReportEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_VNMONLINTESTREPORTENTRY = "SELECT vnmOnlintestReportEntry FROM VnmOnlintestReportEntry vnmOnlintestReportEntry";
	private static final String _SQL_COUNT_VNMONLINTESTREPORTENTRY = "SELECT COUNT(vnmOnlintestReportEntry) FROM VnmOnlintestReportEntry vnmOnlintestReportEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vnmOnlintestReportEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VnmOnlintestReportEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(VnmOnlintestReportEntryPersistenceImpl.class);
	private static VnmOnlintestReportEntry _nullVnmOnlintestReportEntry = new VnmOnlintestReportEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<VnmOnlintestReportEntry> toCacheModel() {
				return _nullVnmOnlintestReportEntryCacheModel;
			}
		};

	private static CacheModel<VnmOnlintestReportEntry> _nullVnmOnlintestReportEntryCacheModel =
		new CacheModel<VnmOnlintestReportEntry>() {
			@Override
			public VnmOnlintestReportEntry toEntityModel() {
				return _nullVnmOnlintestReportEntry;
			}
		};
}