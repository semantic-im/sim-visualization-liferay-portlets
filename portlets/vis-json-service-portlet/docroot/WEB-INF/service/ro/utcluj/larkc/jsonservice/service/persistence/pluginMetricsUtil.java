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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import ro.utcluj.larkc.jsonservice.model.pluginMetrics;

import java.util.List;

/**
 * The persistence utility for the plugin metrics service. This utility wraps {@link pluginMetricsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author UTCluj
 * @see pluginMetricsPersistence
 * @see pluginMetricsPersistenceImpl
 * @generated
 */
public class pluginMetricsUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(pluginMetrics pluginMetrics) {
		getPersistence().clearCache(pluginMetrics);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<pluginMetrics> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<pluginMetrics> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<pluginMetrics> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static pluginMetrics remove(pluginMetrics pluginMetrics)
		throws SystemException {
		return getPersistence().remove(pluginMetrics);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static pluginMetrics update(pluginMetrics pluginMetrics,
		boolean merge) throws SystemException {
		return getPersistence().update(pluginMetrics, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static pluginMetrics update(pluginMetrics pluginMetrics,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(pluginMetrics, merge, serviceContext);
	}

	/**
	* Caches the plugin metrics in the entity cache if it is enabled.
	*
	* @param pluginMetrics the plugin metrics to cache
	*/
	public static void cacheResult(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics) {
		getPersistence().cacheResult(pluginMetrics);
	}

	/**
	* Caches the plugin metricses in the entity cache if it is enabled.
	*
	* @param pluginMetricses the plugin metricses to cache
	*/
	public static void cacheResult(
		java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> pluginMetricses) {
		getPersistence().cacheResult(pluginMetricses);
	}

	/**
	* Creates a new plugin metrics with the primary key. Does not add the plugin metrics to the database.
	*
	* @param fooId the primary key for the new plugin metrics
	* @return the new plugin metrics
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics create(
		long fooId) {
		return getPersistence().create(fooId);
	}

	/**
	* Removes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the plugin metrics to remove
	* @return the plugin metrics that was removed
	* @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics remove(
		long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException {
		return getPersistence().remove(fooId);
	}

	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics updateImpl(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(pluginMetrics, merge);
	}

	/**
	* Finds the plugin metrics with the primary key or throws a {@link ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException} if it could not be found.
	*
	* @param fooId the primary key of the plugin metrics to find
	* @return the plugin metrics
	* @throws ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException {
		return getPersistence().findByPrimaryKey(fooId);
	}

	/**
	* Finds the plugin metrics with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fooId the primary key of the plugin metrics to find
	* @return the plugin metrics, or <code>null</code> if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(fooId);
	}

	/**
	* Finds all the plugin metricses where field2 = &#63;.
	*
	* @param field2 the field2 to search with
	* @return the matching plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findByField2(
		boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2);
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
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2, start, end);
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
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByField2(field2, start, end, orderByComparator);
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
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics findByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException {
		return getPersistence().findByField2_First(field2, orderByComparator);
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
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics findByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException {
		return getPersistence().findByField2_Last(field2, orderByComparator);
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
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			ro.utcluj.larkc.jsonservice.NoSuchpluginMetricsException {
		return getPersistence()
				   .findByField2_PrevAndNext(fooId, field2, orderByComparator);
	}

	/**
	* Finds all the plugin metricses.
	*
	* @return the plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
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
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the plugin metricses where field2 = &#63; from the database.
	*
	* @param field2 the field2 to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByField2(field2);
	}

	/**
	* Removes all the plugin metricses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the plugin metricses where field2 = &#63;.
	*
	* @param field2 the field2 to search with
	* @return the number of matching plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByField2(field2);
	}

	/**
	* Counts all the plugin metricses.
	*
	* @return the number of plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static pluginMetricsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (pluginMetricsPersistence)PortletBeanLocatorUtil.locate(ro.utcluj.larkc.jsonservice.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					pluginMetricsPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(pluginMetricsPersistence persistence) {
		_persistence = persistence;
	}

	private static pluginMetricsPersistence _persistence;
}