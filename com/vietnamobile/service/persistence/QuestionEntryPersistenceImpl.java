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

import com.vietnamobile.NoSuchQuestionEntryException;

import com.vietnamobile.model.QuestionEntry;
import com.vietnamobile.model.impl.QuestionEntryImpl;
import com.vietnamobile.model.impl.QuestionEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the question entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duymb
 * @see QuestionEntryPersistence
 * @see QuestionEntryUtil
 * @generated
 */
public class QuestionEntryPersistenceImpl extends BasePersistenceImpl<QuestionEntry>
	implements QuestionEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link QuestionEntryUtil} to access the question entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = QuestionEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryModelImpl.FINDER_CACHE_ENABLED,
			QuestionEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryModelImpl.FINDER_CACHE_ENABLED,
			QuestionEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public QuestionEntryPersistenceImpl() {
		setModelClass(QuestionEntry.class);
	}

	/**
	 * Caches the question entry in the entity cache if it is enabled.
	 *
	 * @param questionEntry the question entry
	 */
	@Override
	public void cacheResult(QuestionEntry questionEntry) {
		EntityCacheUtil.putResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryImpl.class, questionEntry.getPrimaryKey(),
			questionEntry);

		questionEntry.resetOriginalValues();
	}

	/**
	 * Caches the question entries in the entity cache if it is enabled.
	 *
	 * @param questionEntries the question entries
	 */
	@Override
	public void cacheResult(List<QuestionEntry> questionEntries) {
		for (QuestionEntry questionEntry : questionEntries) {
			if (EntityCacheUtil.getResult(
						QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
						QuestionEntryImpl.class, questionEntry.getPrimaryKey()) == null) {
				cacheResult(questionEntry);
			}
			else {
				questionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all question entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(QuestionEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(QuestionEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the question entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(QuestionEntry questionEntry) {
		EntityCacheUtil.removeResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryImpl.class, questionEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<QuestionEntry> questionEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (QuestionEntry questionEntry : questionEntries) {
			EntityCacheUtil.removeResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
				QuestionEntryImpl.class, questionEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new question entry with the primary key. Does not add the question entry to the database.
	 *
	 * @param questionEntryId the primary key for the new question entry
	 * @return the new question entry
	 */
	@Override
	public QuestionEntry create(long questionEntryId) {
		QuestionEntry questionEntry = new QuestionEntryImpl();

		questionEntry.setNew(true);
		questionEntry.setPrimaryKey(questionEntryId);

		return questionEntry;
	}

	/**
	 * Removes the question entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionEntryId the primary key of the question entry
	 * @return the question entry that was removed
	 * @throws com.vietnamobile.NoSuchQuestionEntryException if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry remove(long questionEntryId)
		throws NoSuchQuestionEntryException, SystemException {
		return remove((Serializable)questionEntryId);
	}

	/**
	 * Removes the question entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the question entry
	 * @return the question entry that was removed
	 * @throws com.vietnamobile.NoSuchQuestionEntryException if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry remove(Serializable primaryKey)
		throws NoSuchQuestionEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			QuestionEntry questionEntry = (QuestionEntry)session.get(QuestionEntryImpl.class,
					primaryKey);

			if (questionEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuestionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(questionEntry);
		}
		catch (NoSuchQuestionEntryException nsee) {
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
	protected QuestionEntry removeImpl(QuestionEntry questionEntry)
		throws SystemException {
		questionEntry = toUnwrappedModel(questionEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(questionEntry)) {
				questionEntry = (QuestionEntry)session.get(QuestionEntryImpl.class,
						questionEntry.getPrimaryKeyObj());
			}

			if (questionEntry != null) {
				session.delete(questionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (questionEntry != null) {
			clearCache(questionEntry);
		}

		return questionEntry;
	}

	@Override
	public QuestionEntry updateImpl(
		com.vietnamobile.model.QuestionEntry questionEntry)
		throws SystemException {
		questionEntry = toUnwrappedModel(questionEntry);

		boolean isNew = questionEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (questionEntry.isNew()) {
				session.save(questionEntry);

				questionEntry.setNew(false);
			}
			else {
				session.merge(questionEntry);
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

		EntityCacheUtil.putResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
			QuestionEntryImpl.class, questionEntry.getPrimaryKey(),
			questionEntry);

		return questionEntry;
	}

	protected QuestionEntry toUnwrappedModel(QuestionEntry questionEntry) {
		if (questionEntry instanceof QuestionEntryImpl) {
			return questionEntry;
		}

		QuestionEntryImpl questionEntryImpl = new QuestionEntryImpl();

		questionEntryImpl.setNew(questionEntry.isNew());
		questionEntryImpl.setPrimaryKey(questionEntry.getPrimaryKey());

		questionEntryImpl.setQuestionEntryId(questionEntry.getQuestionEntryId());
		questionEntryImpl.setGroupId(questionEntry.getGroupId());
		questionEntryImpl.setCompanyId(questionEntry.getCompanyId());
		questionEntryImpl.setUserId(questionEntry.getUserId());
		questionEntryImpl.setUserName(questionEntry.getUserName());
		questionEntryImpl.setCreateDate(questionEntry.getCreateDate());
		questionEntryImpl.setModifiedDate(questionEntry.getModifiedDate());
		questionEntryImpl.setSubject(questionEntry.getSubject());
		questionEntryImpl.setLevelQuestion(questionEntry.getLevelQuestion());
		questionEntryImpl.setAnswer(questionEntry.getAnswer());
		questionEntryImpl.setScore(questionEntry.getScore());
		questionEntryImpl.setQuestionContent(questionEntry.getQuestionContent());
		questionEntryImpl.setA(questionEntry.getA());
		questionEntryImpl.setB(questionEntry.getB());
		questionEntryImpl.setC(questionEntry.getC());
		questionEntryImpl.setD(questionEntry.getD());
		questionEntryImpl.setTitle(questionEntry.getTitle());
		questionEntryImpl.setSelection1(questionEntry.getSelection1());
		questionEntryImpl.setSelection2(questionEntry.getSelection2());
		questionEntryImpl.setSelection3(questionEntry.getSelection3());
		questionEntryImpl.setSelection4(questionEntry.getSelection4());
		questionEntryImpl.setTypeExam(questionEntry.getTypeExam());
		questionEntryImpl.setInExam(questionEntry.getInExam());
		questionEntryImpl.setStatus(questionEntry.getStatus());

		return questionEntryImpl;
	}

	/**
	 * Returns the question entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the question entry
	 * @return the question entry
	 * @throws com.vietnamobile.NoSuchQuestionEntryException if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQuestionEntryException, SystemException {
		QuestionEntry questionEntry = fetchByPrimaryKey(primaryKey);

		if (questionEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQuestionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return questionEntry;
	}

	/**
	 * Returns the question entry with the primary key or throws a {@link com.vietnamobile.NoSuchQuestionEntryException} if it could not be found.
	 *
	 * @param questionEntryId the primary key of the question entry
	 * @return the question entry
	 * @throws com.vietnamobile.NoSuchQuestionEntryException if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry findByPrimaryKey(long questionEntryId)
		throws NoSuchQuestionEntryException, SystemException {
		return findByPrimaryKey((Serializable)questionEntryId);
	}

	/**
	 * Returns the question entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the question entry
	 * @return the question entry, or <code>null</code> if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		QuestionEntry questionEntry = (QuestionEntry)EntityCacheUtil.getResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
				QuestionEntryImpl.class, primaryKey);

		if (questionEntry == _nullQuestionEntry) {
			return null;
		}

		if (questionEntry == null) {
			Session session = null;

			try {
				session = openSession();

				questionEntry = (QuestionEntry)session.get(QuestionEntryImpl.class,
						primaryKey);

				if (questionEntry != null) {
					cacheResult(questionEntry);
				}
				else {
					EntityCacheUtil.putResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
						QuestionEntryImpl.class, primaryKey, _nullQuestionEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(QuestionEntryModelImpl.ENTITY_CACHE_ENABLED,
					QuestionEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return questionEntry;
	}

	/**
	 * Returns the question entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param questionEntryId the primary key of the question entry
	 * @return the question entry, or <code>null</code> if a question entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuestionEntry fetchByPrimaryKey(long questionEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)questionEntryId);
	}

	/**
	 * Returns all the question entries.
	 *
	 * @return the question entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<QuestionEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the question entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.QuestionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of question entries
	 * @param end the upper bound of the range of question entries (not inclusive)
	 * @return the range of question entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<QuestionEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the question entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.vietnamobile.model.impl.QuestionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of question entries
	 * @param end the upper bound of the range of question entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of question entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<QuestionEntry> findAll(int start, int end,
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

		List<QuestionEntry> list = (List<QuestionEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_QUESTIONENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_QUESTIONENTRY;

				if (pagination) {
					sql = sql.concat(QuestionEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<QuestionEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<QuestionEntry>(list);
				}
				else {
					list = (List<QuestionEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the question entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (QuestionEntry questionEntry : findAll()) {
			remove(questionEntry);
		}
	}

	/**
	 * Returns the number of question entries.
	 *
	 * @return the number of question entries
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

				Query q = session.createQuery(_SQL_COUNT_QUESTIONENTRY);

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
	 * Initializes the question entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.vietnamobile.model.QuestionEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<QuestionEntry>> listenersList = new ArrayList<ModelListener<QuestionEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<QuestionEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(QuestionEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_QUESTIONENTRY = "SELECT questionEntry FROM QuestionEntry questionEntry";
	private static final String _SQL_COUNT_QUESTIONENTRY = "SELECT COUNT(questionEntry) FROM QuestionEntry questionEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "questionEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No QuestionEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(QuestionEntryPersistenceImpl.class);
	private static QuestionEntry _nullQuestionEntry = new QuestionEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<QuestionEntry> toCacheModel() {
				return _nullQuestionEntryCacheModel;
			}
		};

	private static CacheModel<QuestionEntry> _nullQuestionEntryCacheModel = new CacheModel<QuestionEntry>() {
			@Override
			public QuestionEntry toEntityModel() {
				return _nullQuestionEntry;
			}
		};
}