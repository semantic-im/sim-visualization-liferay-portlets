/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package ro.utcluj.larkc.jsonservice.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException;
import ro.utcluj.larkc.jsonservice.model.impl.pluginMetricsImpl;
import ro.utcluj.larkc.jsonservice.model.impl.pluginMetricsModelImpl;
import ro.utcluj.larkc.jsonservice.model.pluginMetrics;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the plugin metrics service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link pluginMetricsUtil} to access the plugin metrics persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author UTCluj
 * @see pluginMetricsPersistence
 * @see pluginMetricsUtil
 * @generated
 */
public class pluginMetricsPersistenceImpl extends BasePersistenceImpl<pluginMetrics>
	implements pluginMetricsPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = pluginMetricsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_FIELD2 = new FinderPath(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByField2",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FIELD2 = new FinderPath(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByField2",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the plugin metrics in the entity cache if it is enabled.
	 *
	 * @param pluginMetrics the plugin metrics to cache
	 */
	public void cacheResult(pluginMetrics pluginMetrics) {
		EntityCacheUtil.putResult(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsImpl.class, pluginMetrics.getPrimaryKey(),
			pluginMetrics);
	}

	/**
	 * Caches the plugin metricses in the entity cache if it is enabled.
	 *
	 * @param pluginMetricses the plugin metricses to cache
	 */
	public void cacheResult(List<pluginMetrics> pluginMetricses) {
		for (pluginMetrics pluginMetrics : pluginMetricses) {
			if (EntityCacheUtil.getResult(
						pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
						pluginMetricsImpl.class, pluginMetrics.getPrimaryKey(),
						this) == null) {
				cacheResult(pluginMetrics);
			}
		}
	}

	/**
	 * Clears the cache for all plugin metricses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(pluginMetricsImpl.class.getName());
		EntityCacheUtil.clearCache(pluginMetricsImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the plugin metrics.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(pluginMetrics pluginMetrics) {
		EntityCacheUtil.removeResult(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsImpl.class, pluginMetrics.getPrimaryKey());
	}

	/**
	 * Creates a new plugin metrics with the primary key. Does not add the plugin metrics to the database.
	 *
	 * @param fooId the primary key for the new plugin metrics
	 * @return the new plugin metrics
	 */
	public pluginMetrics create(long fooId) {
		pluginMetrics pluginMetrics = new pluginMetricsImpl();

		pluginMetrics.setNew(true);
		pluginMetrics.setPrimaryKey(fooId);

		return pluginMetrics;
	}

	/**
	 * Removes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the plugin metrics to remove
	 * @return the plugin metrics that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fooId the primary key of the plugin metrics to remove
	 * @return the plugin metrics that was removed
	 * @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics remove(long fooId)
		throws NoSuchpluginMetricsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			pluginMetrics pluginMetrics = (pluginMetrics)session.get(pluginMetricsImpl.class,
					new Long(fooId));

			if (pluginMetrics == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fooId);
				}

				throw new NoSuchpluginMetricsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					fooId);
			}

			return remove(pluginMetrics);
		}
		catch (NoSuchpluginMetricsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected pluginMetrics removeImpl(pluginMetrics pluginMetrics)
		throws SystemException {
		pluginMetrics = toUnwrappedModel(pluginMetrics);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, pluginMetrics);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsImpl.class, pluginMetrics.getPrimaryKey());

		return pluginMetrics;
	}

	public pluginMetrics updateImpl(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics,
		boolean merge) throws SystemException {
		pluginMetrics = toUnwrappedModel(pluginMetrics);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, pluginMetrics, merge);

			pluginMetrics.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
			pluginMetricsImpl.class, pluginMetrics.getPrimaryKey(),
			pluginMetrics);

		return pluginMetrics;
	}

	protected pluginMetrics toUnwrappedModel(pluginMetrics pluginMetrics) {
		if (pluginMetrics instanceof pluginMetricsImpl) {
			return pluginMetrics;
		}

		pluginMetricsImpl pluginMetricsImpl = new pluginMetricsImpl();

		pluginMetricsImpl.setNew(pluginMetrics.isNew());
		pluginMetricsImpl.setPrimaryKey(pluginMetrics.getPrimaryKey());

		pluginMetricsImpl.setFooId(pluginMetrics.getFooId());
		pluginMetricsImpl.setCompanyId(pluginMetrics.getCompanyId());
		pluginMetricsImpl.setUserId(pluginMetrics.getUserId());
		pluginMetricsImpl.setUserName(pluginMetrics.getUserName());
		pluginMetricsImpl.setCreateDate(pluginMetrics.getCreateDate());
		pluginMetricsImpl.setModifiedDate(pluginMetrics.getModifiedDate());
		pluginMetricsImpl.setField1(pluginMetrics.getField1());
		pluginMetricsImpl.setField2(pluginMetrics.isField2());
		pluginMetricsImpl.setField3(pluginMetrics.getField3());
		pluginMetricsImpl.setField4(pluginMetrics.getField4());
		pluginMetricsImpl.setField5(pluginMetrics.getField5());

		return pluginMetricsImpl;
	}

	/**
	 * Finds the plugin metrics with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the plugin metrics to find
	 * @return the plugin metrics
	 * @throws com.liferay.portal.NoSuchModelException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the plugin metrics with the primary key or throws a {@link ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException} if it could not be found.
	 *
	 * @param fooId the primary key of the plugin metrics to find
	 * @return the plugin metrics
	 * @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics findByPrimaryKey(long fooId)
		throws NoSuchpluginMetricsException, SystemException {
		pluginMetrics pluginMetrics = fetchByPrimaryKey(fooId);

		if (pluginMetrics == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fooId);
			}

			throw new NoSuchpluginMetricsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				fooId);
		}

		return pluginMetrics;
	}

	/**
	 * Finds the plugin metrics with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the plugin metrics to find
	 * @return the plugin metrics, or <code>null</code> if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the plugin metrics with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fooId the primary key of the plugin metrics to find
	 * @return the plugin metrics, or <code>null</code> if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics fetchByPrimaryKey(long fooId)
		throws SystemException {
		pluginMetrics pluginMetrics = (pluginMetrics)EntityCacheUtil.getResult(pluginMetricsModelImpl.ENTITY_CACHE_ENABLED,
				pluginMetricsImpl.class, fooId, this);

		if (pluginMetrics == null) {
			Session session = null;

			try {
				session = openSession();

				pluginMetrics = (pluginMetrics)session.get(pluginMetricsImpl.class,
						new Long(fooId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (pluginMetrics != null) {
					cacheResult(pluginMetrics);
				}

				closeSession(session);
			}
		}

		return pluginMetrics;
	}

	/**
	 * Finds all the plugin metricses where field2 = &#63;.
	 *
	 * @param field2 the field2 to search with
	 * @return the matching plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findByField2(boolean field2)
		throws SystemException {
		return findByField2(field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the plugin metricses where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2 to search with
	 * @param start the lower bound of the range of plugin metricses to return
	 * @param end the upper bound of the range of plugin metricses to return (not inclusive)
	 * @return the range of matching plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findByField2(boolean field2, int start, int end)
		throws SystemException {
		return findByField2(field2, start, end, null);
	}

	/**
	 * Finds an ordered range of all the plugin metricses where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2 to search with
	 * @param start the lower bound of the range of plugin metricses to return
	 * @param end the upper bound of the range of plugin metricses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findByField2(boolean field2, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				field2,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<pluginMetrics> list = (List<pluginMetrics>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FIELD2,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PLUGINMETRICS_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(pluginMetricsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				list = (List<pluginMetrics>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_FIELD2,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FIELD2,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first plugin metrics in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2 to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching plugin metrics
	 * @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a matching plugin metrics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics findByField2_First(boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchpluginMetricsException, SystemException {
		List<pluginMetrics> list = findByField2(field2, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("field2=");
			msg.append(field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchpluginMetricsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last plugin metrics in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2 to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching plugin metrics
	 * @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a matching plugin metrics could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics findByField2_Last(boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchpluginMetricsException, SystemException {
		int count = countByField2(field2);

		List<pluginMetrics> list = findByField2(field2, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("field2=");
			msg.append(field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchpluginMetricsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the plugin metricses before and after the current plugin metrics in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fooId the primary key of the current plugin metrics
	 * @param field2 the field2 to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next plugin metrics
	 * @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics[] findByField2_PrevAndNext(long fooId, boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchpluginMetricsException, SystemException {
		pluginMetrics pluginMetrics = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			pluginMetrics[] array = new pluginMetricsImpl[3];

			array[0] = getByField2_PrevAndNext(session, pluginMetrics, field2,
					orderByComparator, true);

			array[1] = pluginMetrics;

			array[2] = getByField2_PrevAndNext(session, pluginMetrics, field2,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected pluginMetrics getByField2_PrevAndNext(Session session,
		pluginMetrics pluginMetrics, boolean field2,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PLUGINMETRICS_WHERE);

		query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(pluginMetricsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(field2);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(pluginMetrics);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<pluginMetrics> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the plugin metricses.
	 *
	 * @return the plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the plugin metricses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of plugin metricses to return
	 * @param end the upper bound of the range of plugin metricses to return (not inclusive)
	 * @return the range of plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the plugin metricses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of plugin metricses to return
	 * @param end the upper bound of the range of plugin metricses to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public List<pluginMetrics> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<pluginMetrics> list = (List<pluginMetrics>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PLUGINMETRICS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PLUGINMETRICS.concat(pluginMetricsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<pluginMetrics>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<pluginMetrics>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the plugin metricses where field2 = &#63; from the database.
	 *
	 * @param field2 the field2 to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByField2(boolean field2) throws SystemException {
		for (pluginMetrics pluginMetrics : findByField2(field2)) {
			remove(pluginMetrics);
		}
	}

	/**
	 * Removes all the plugin metricses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (pluginMetrics pluginMetrics : findAll()) {
			remove(pluginMetrics);
		}
	}

	/**
	 * Counts all the plugin metricses where field2 = &#63;.
	 *
	 * @param field2 the field2 to search with
	 * @return the number of matching plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByField2(boolean field2) throws SystemException {
		Object[] finderArgs = new Object[] { field2 };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FIELD2,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PLUGINMETRICS_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FIELD2,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the plugin metricses.
	 *
	 * @return the number of plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PLUGINMETRICS);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the plugin metrics persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.ro.utcluj.larkc.jsonservice.model.pluginMetrics")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<pluginMetrics>> listenersList = new ArrayList<ModelListener<pluginMetrics>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<pluginMetrics>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(pluginMetricsImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = pluginMetricsPersistence.class)
	protected pluginMetricsPersistence pluginMetricsPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PLUGINMETRICS = "SELECT pluginMetrics FROM pluginMetrics pluginMetrics";
	private static final String _SQL_SELECT_PLUGINMETRICS_WHERE = "SELECT pluginMetrics FROM pluginMetrics pluginMetrics WHERE ";
	private static final String _SQL_COUNT_PLUGINMETRICS = "SELECT COUNT(pluginMetrics) FROM pluginMetrics pluginMetrics";
	private static final String _SQL_COUNT_PLUGINMETRICS_WHERE = "SELECT COUNT(pluginMetrics) FROM pluginMetrics pluginMetrics WHERE ";
	private static final String _FINDER_COLUMN_FIELD2_FIELD2_2 = "pluginMetrics.field2 = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pluginMetrics.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No pluginMetrics exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No pluginMetrics exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(pluginMetricsPersistenceImpl.class);
}