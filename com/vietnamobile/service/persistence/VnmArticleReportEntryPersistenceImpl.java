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

import com.vietnamobile.NoSuchVnmArticleReportEntryException;

import com.vietnamobile.model.VnmArticleReportEntry;
import com.vietnamobile.model.impl.VnmArticleReportEntryImpl;
import com.vietnamobile.model.impl.VnmArticleReportEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the vnm article report entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see VnmArticleReportEntryPersistence
 * @see VnmArticleReportEntryUtil
 * @generated
 */
public class VnmArticleReportEntryPersistenceImpl extends BasePersistenceImpl<VnmArticleReportEntry>
	implements VnmArticleReportEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VnmArticleReportEntryUtil} to access the vnm article report entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VnmArticleReportEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryModelImpl.FINDER_CACHE_ENABLED,
			VnmArticleReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryModelImpl.FINDER_CACHE_ENABLED,
			VnmArticleReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VnmArticleReportEntryPersistenceImpl() {
		setModelClass(VnmArticleReportEntry.class);
	}

	/**
	 * Caches the vnm article report entry in the entity cache if it is enabled.
	 *
	 * @param vnmArticleReportEntry the vnm article report entry
	 */
	@Override
	public void cacheResult(VnmArticleReportEntry vnmArticleReportEntry) {
		EntityCacheUtil.putResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryImpl.class,
			vnmArticleReportEntry.getPrimaryKey(), vnmArticleReportEntry);

		vnmArticleReportEntry.resetOriginalValues();
	}

	/**
	 * Caches the vnm article report entries in the entity cache if it is enabled.
	 *
	 * @param vnmArticleReportEntries the vnm article report entries
	 */
	@Override
	public void cacheResult(List<VnmArticleReportEntry> vnmArticleReportEntries) {
		for (VnmArticleReportEntry vnmArticleReportEntry : vnmArticleReportEntries) {
			if (EntityCacheUtil.getResult(
						VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
						VnmArticleReportEntryImpl.class,
						vnmArticleReportEntry.getPrimaryKey()) == null) {
				cacheResult(vnmArticleReportEntry);
			}
			else {
				vnmArticleReportEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vnm article report entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(VnmArticleReportEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(VnmArticleReportEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vnm article report entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VnmArticleReportEntry vnmArticleReportEntry) {
		EntityCacheUtil.removeResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryImpl.class,
			vnmArticleReportEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VnmArticleReportEntry> vnmArticleReportEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VnmArticleReportEntry vnmArticleReportEntry : vnmArticleReportEntries) {
			EntityCacheUtil.removeResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
				VnmArticleReportEntryImpl.class,
				vnmArticleReportEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vnm article report entry with the primary key. Does not add the vnm article report entry to the database.
	 *
	 * @param vnmArticleReportEntryId the primary key for the new vnm article report entry
	 * @return the new vnm article report entry
	 */
	@Override
	public VnmArticleReportEntry create(long vnmArticleReportEntryId) {
		VnmArticleReportEntry vnmArticleReportEntry = new VnmArticleReportEntryImpl();

		vnmArticleReportEntry.setNew(true);
		vnmArticleReportEntry.setPrimaryKey(vnmArticleReportEntryId);

		return vnmArticleReportEntry;
	}

	/**
	 * Removes the vnm article report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param vnmArticleReportEntryId the primary key of the vnm article report entry
	 * @return the vnm article report entry that was removed
	 * @throws com.vietnamobile.NoSuchVnmArticleReportEntryException if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry remove(long vnmArticleReportEntryId)
		throws NoSuchVnmArticleReportEntryException, SystemException {
		return remove((Serializable)vnmArticleReportEntryId);
	}

	/**
	 * Removes the vnm article report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vnm article report entry
	 * @return the vnm article report entry that was removed
	 * @throws com.vietnamobile.NoSuchVnmArticleReportEntryException if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry remove(Serializable primaryKey)
		throws NoSuchVnmArticleReportEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			VnmArticleReportEntry vnmArticleReportEntry = (VnmArticleReportEntry)session.get(VnmArticleReportEntryImpl.class,
					primaryKey);

			if (vnmArticleReportEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVnmArticleReportEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vnmArticleReportEntry);
		}
		catch (NoSuchVnmArticleReportEntryException nsee) {
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
	protected VnmArticleReportEntry removeImpl(
		VnmArticleReportEntry vnmArticleReportEntry) throws SystemException {
		vnmArticleReportEntry = toUnwrappedModel(vnmArticleReportEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vnmArticleReportEntry)) {
				vnmArticleReportEntry = (VnmArticleReportEntry)session.get(VnmArticleReportEntryImpl.class,
						vnmArticleReportEntry.getPrimaryKeyObj());
			}

			if (vnmArticleReportEntry != null) {
				session.delete(vnmArticleReportEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vnmArticleReportEntry != null) {
			clearCache(vnmArticleReportEntry);
		}

		return vnmArticleReportEntry;
	}

	@Override
	public VnmArticleReportEntry updateImpl(
		com.vietnamobile.model.VnmArticleReportEntry vnmArticleReportEntry)
		throws SystemException {
		vnmArticleReportEntry = toUnwrappedModel(vnmArticleReportEntry);

		boolean isNew = vnmArticleReportEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vnmArticleReportEntry.isNew()) {
				session.save(vnmArticleReportEntry);

				vnmArticleReportEntry.setNew(false);
			}
			else {
				session.merge(vnmArticleReportEntry);
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

		EntityCacheUtil.putResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
			VnmArticleReportEntryImpl.class,
			vnmArticleReportEntry.getPrimaryKey(), vnmArticleReportEntry);

		return vnmArticleReportEntry;
	}

	protected VnmArticleReportEntry toUnwrappedModel(
		VnmArticleReportEntry vnmArticleReportEntry) {
		if (vnmArticleReportEntry instanceof VnmArticleReportEntryImpl) {
			return vnmArticleReportEntry;
		}

		VnmArticleReportEntryImpl vnmArticleReportEntryImpl = new VnmArticleReportEntryImpl();

		vnmArticleReportEntryImpl.setNew(vnmArticleReportEntry.isNew());
		vnmArticleReportEntryImpl.setPrimaryKey(vnmArticleReportEntry.getPrimaryKey());

		vnmArticleReportEntryImpl.setVnmArticleReportEntryId(vnmArticleReportEntry.getVnmArticleReportEntryId());
		vnmArticleReportEntryImpl.setGroupId(vnmArticleReportEntry.getGroupId());
		vnmArticleReportEntryImpl.setCompanyId(vnmArticleReportEntry.getCompanyId());
		vnmArticleReportEntryImpl.setUserId(vnmArticleReportEntry.getUserId());
		vnmArticleReportEntryImpl.setUserName(vnmArticleReportEntry.getUserName());
		vnmArticleReportEntryImpl.setCreateDate(vnmArticleReportEntry.getCreateDate());
		vnmArticleReportEntryImpl.setModifiedDate(vnmArticleReportEntry.getModifiedDate());
		vnmArticleReportEntryImpl.setArticleId(vnmArticleReportEntry.getArticleId());
		vnmArticleReportEntryImpl.setVersion(vnmArticleReportEntry.getVersion());
		vnmArticleReportEntryImpl.setTitle(vnmArticleReportEntry.getTitle());
		vnmArticleReportEntryImpl.setAction(vnmArticleReportEntry.getAction());
		vnmArticleReportEntryImpl.setStatus(vnmArticleReportEntry.getStatus());

		return vnmArticleReportEntryImpl;
	}

	/**
	 * Returns the vnm article report entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vnm article report entry
	 * @return the vnm article report entry
	 * @throws com.vietnamobile.NoSuchVnmArticleReportEntryException if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVnmArticleReportEntryException, SystemException {
		VnmArticleReportEntry vnmArticleReportEntry = fetchByPrimaryKey(primaryKey);

		if (vnmArticleReportEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVnmArticleReportEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vnmArticleReportEntry;
	}

	/**
	 * Returns the vnm article report entry with the primary key or throws a {@link com.vietnamobile.NoSuchVnmArticleReportEntryException} if it could not be found.
	 *
	 * @param vnmArticleReportEntryId the primary key of the vnm article report entry
	 * @return the vnm article report entry
	 * @throws com.vietnamobile.NoSuchVnmArticleReportEntryException if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry findByPrimaryKey(long vnmArticleReportEntryId)
		throws NoSuchVnmArticleReportEntryException, SystemException {
		return findByPrimaryKey((Serializable)vnmArticleReportEntryId);
	}

	/**
	 * Returns the vnm article report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vnm article report entry
	 * @return the vnm article report entry, or <code>null</code> if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		VnmArticleReportEntry vnmArticleReportEntry = (VnmArticleReportEntry)EntityCacheUtil.getResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
				VnmArticleReportEntryImpl.class, primaryKey);

		if (vnmArticleReportEntry == _nullVnmArticleReportEntry) {
			return null;
		}

		if (vnmArticleReportEntry == null) {
			Session session = null;

			try {
				session = openSession();

				vnmArticleReportEntry = (VnmArticleReportEntry)session.get(VnmArticleReportEntryImpl.class,
						primaryKey);

				if (vnmArticleReportEntry != null) {
					cacheResult(vnmArticleReportEntry);
				}
				else {
					EntityCacheUtil.putResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
						VnmArticleReportEntryImpl.class, primaryKey,
						_nullVnmArticleReportEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(VnmArticleReportEntryModelImpl.ENTITY_CACHE_ENABLED,
					VnmArticleReportEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vnmArticleReportEntry;
	}

	/**
	 * Returns the vnm article report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param vnmArticleReportEntryId the primary key of the vnm article report entry
	 * @return the vnm article report entry, or <code>null</code> if a vnm article report entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public VnmArticleReportEntry fetchByPrimaryKey(long vnmArticleReportEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)vnmArticleReportEntryId);
	}

	/**
	 * Returns all the vnm article report entries.
	 *
	 * @return the vnm article report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmArticleReportEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vnm article report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.VnmArticleReportEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vnm article report entries
	 * @param end the upper bound of the range of vnm article report entries (not inclusive)
	 * @return the range of vnm article report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmArticleReportEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vnm article report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.VnmArticleReportEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vnm article report entries
	 * @param end the upper bound of the range of vnm article report entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vnm article report entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<VnmArticleReportEntry> findAll(int start, int end,
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

		List<VnmArticleReportEntry> list = (List<VnmArticleReportEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VNMARTICLEREPORTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VNMARTICLEREPORTENTRY;

				if (pagination) {
					sql = sql.concat(VnmArticleReportEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VnmArticleReportEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<VnmArticleReportEntry>(list);
				}
				else {
					list = (List<VnmArticleReportEntry>)QueryUtil.list(q,
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
	 * Removes all the vnm article report entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (VnmArticleReportEntry vnmArticleReportEntry : findAll()) {
			remove(vnmArticleReportEntry);
		}
	}

	/**
	 * Returns the number of vnm article report entries.
	 *
	 * @return the number of vnm article report entries
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

				Query q = session.createQuery(_SQL_COUNT_VNMARTICLEREPORTENTRY);

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
	 * Initializes the vnm article report entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.VnmArticleReportEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<VnmArticleReportEntry>> listenersList = new ArrayList<ModelListener<VnmArticleReportEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<VnmArticleReportEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(VnmArticleReportEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_VNMARTICLEREPORTENTRY = "SELECT vnmArticleReportEntry FROM VnmArticleReportEntry vnmArticleReportEntry";
	private static final String _SQL_COUNT_VNMARTICLEREPORTENTRY = "SELECT COUNT(vnmArticleReportEntry) FROM VnmArticleReportEntry vnmArticleReportEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vnmArticleReportEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VnmArticleReportEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(VnmArticleReportEntryPersistenceImpl.class);
	private static VnmArticleReportEntry _nullVnmArticleReportEntry = new VnmArticleReportEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<VnmArticleReportEntry> toCacheModel() {
				return _nullVnmArticleReportEntryCacheModel;
			}
		};

	private static CacheModel<VnmArticleReportEntry> _nullVnmArticleReportEntryCacheModel =
		new CacheModel<VnmArticleReportEntry>() {
			@Override
			public VnmArticleReportEntry toEntityModel() {
				return _nullVnmArticleReportEntry;
			}
		};
}