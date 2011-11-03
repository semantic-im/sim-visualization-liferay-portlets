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

package ro.utcluj.larkc.jsonservice.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the plugin metrics local service. This utility wraps {@link ro.utcluj.larkc.jsonservice.service.impl.pluginMetricsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link ro.utcluj.larkc.jsonservice.service.impl.pluginMetricsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author UTCluj
 * @see pluginMetricsLocalService
 * @see ro.utcluj.larkc.jsonservice.service.base.pluginMetricsLocalServiceBaseImpl
 * @see ro.utcluj.larkc.jsonservice.service.impl.pluginMetricsLocalServiceImpl
 * @generated
 */
public class pluginMetricsLocalServiceUtil {
	/**
	* Adds the plugin metrics to the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to add
	* @return the plugin metrics that was added
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics addpluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addpluginMetrics(pluginMetrics);
	}

	/**
	* Creates a new plugin metrics with the primary key. Does not add the plugin metrics to the database.
	*
	* @param fooId the primary key for the new plugin metrics
	* @return the new plugin metrics
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics createpluginMetrics(
		long fooId) {
		return getService().createpluginMetrics(fooId);
	}

	/**
	* Deletes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the plugin metrics to delete
	* @throws PortalException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deletepluginMetrics(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deletepluginMetrics(fooId);
	}

	/**
	* Deletes the plugin metrics from the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deletepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deletepluginMetrics(pluginMetrics);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the plugin metrics with the primary key.
	*
	* @param fooId the primary key of the plugin metrics to get
	* @return the plugin metrics
	* @throws PortalException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics getpluginMetrics(
		long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getpluginMetrics(fooId);
	}

	/**
	* Gets a range of all the plugin metricses.
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
	public static java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> getpluginMetricses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getpluginMetricses(start, end);
	}

	/**
	* Gets the number of plugin metricses.
	*
	* @return the number of plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public static int getpluginMetricsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getpluginMetricsesCount();
	}

	/**
	* Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to update
	* @return the plugin metrics that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics updatepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatepluginMetrics(pluginMetrics);
	}

	/**
	* Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to update
	* @param merge whether to merge the plugin metrics with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the plugin metrics that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static ro.utcluj.larkc.jsonservice.model.pluginMetrics updatepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatepluginMetrics(pluginMetrics, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static pluginMetricsLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					pluginMetricsLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new pluginMetricsLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(pluginMetricsLocalService service) {
		_service = service;
	}

	private static pluginMetricsLocalService _service;
}