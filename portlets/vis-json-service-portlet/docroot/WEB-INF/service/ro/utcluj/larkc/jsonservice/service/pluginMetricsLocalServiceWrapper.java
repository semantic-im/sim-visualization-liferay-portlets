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

/**
 * <p>
 * This class is a wrapper for {@link pluginMetricsLocalService}.
 * </p>
 *
 * @author    UTCluj
 * @see       pluginMetricsLocalService
 * @generated
 */
public class pluginMetricsLocalServiceWrapper
	implements pluginMetricsLocalService {
	public pluginMetricsLocalServiceWrapper(
		pluginMetricsLocalService pluginMetricsLocalService) {
		_pluginMetricsLocalService = pluginMetricsLocalService;
	}

	/**
	* Adds the plugin metrics to the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to add
	* @return the plugin metrics that was added
	* @throws SystemException if a system exception occurred
	*/
	public ro.utcluj.larkc.jsonservice.model.pluginMetrics addpluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.addpluginMetrics(pluginMetrics);
	}

	/**
	* Creates a new plugin metrics with the primary key. Does not add the plugin metrics to the database.
	*
	* @param fooId the primary key for the new plugin metrics
	* @return the new plugin metrics
	*/
	public ro.utcluj.larkc.jsonservice.model.pluginMetrics createpluginMetrics(
		long fooId) {
		return _pluginMetricsLocalService.createpluginMetrics(fooId);
	}

	/**
	* Deletes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the plugin metrics to delete
	* @throws PortalException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletepluginMetrics(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pluginMetricsLocalService.deletepluginMetrics(fooId);
	}

	/**
	* Deletes the plugin metrics from the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deletepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		_pluginMetricsLocalService.deletepluginMetrics(pluginMetrics);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the plugin metrics with the primary key.
	*
	* @param fooId the primary key of the plugin metrics to get
	* @return the plugin metrics
	* @throws PortalException if a plugin metrics with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public ro.utcluj.larkc.jsonservice.model.pluginMetrics getpluginMetrics(
		long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.getpluginMetrics(fooId);
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
	public java.util.List<ro.utcluj.larkc.jsonservice.model.pluginMetrics> getpluginMetricses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.getpluginMetricses(start, end);
	}

	/**
	* Gets the number of plugin metricses.
	*
	* @return the number of plugin metricses
	* @throws SystemException if a system exception occurred
	*/
	public int getpluginMetricsesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.getpluginMetricsesCount();
	}

	/**
	* Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to update
	* @return the plugin metrics that was updated
	* @throws SystemException if a system exception occurred
	*/
	public ro.utcluj.larkc.jsonservice.model.pluginMetrics updatepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.updatepluginMetrics(pluginMetrics);
	}

	/**
	* Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	*
	* @param pluginMetrics the plugin metrics to update
	* @param merge whether to merge the plugin metrics with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the plugin metrics that was updated
	* @throws SystemException if a system exception occurred
	*/
	public ro.utcluj.larkc.jsonservice.model.pluginMetrics updatepluginMetrics(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetricsLocalService.updatepluginMetrics(pluginMetrics,
			merge);
	}

	public pluginMetricsLocalService getWrappedpluginMetricsLocalService() {
		return _pluginMetricsLocalService;
	}

	private pluginMetricsLocalService _pluginMetricsLocalService;
}