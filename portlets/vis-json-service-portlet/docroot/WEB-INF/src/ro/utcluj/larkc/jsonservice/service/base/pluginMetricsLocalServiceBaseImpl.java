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

package ro.utcluj.larkc.jsonservice.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import ro.utcluj.larkc.jsonservice.model.pluginMetrics;
import ro.utcluj.larkc.jsonservice.service.persistence.pluginMetricsPersistence;
import ro.utcluj.larkc.jsonservice.service.pluginMetricsLocalService;
import ro.utcluj.larkc.jsonservice.service.pluginMetricsService;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the plugin metrics local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link ro.utcluj.larkc.jsonservice.service.impl.pluginMetricsLocalServiceImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link ro.utcluj.larkc.jsonservice.service.pluginMetricsLocalServiceUtil} to access the plugin metrics local service.
 * </p>
 *
 * @author UTCluj
 * @see ro.utcluj.larkc.jsonservice.service.impl.pluginMetricsLocalServiceImpl
 * @see ro.utcluj.larkc.jsonservice.service.pluginMetricsLocalServiceUtil
 * @generated
 */
public abstract class pluginMetricsLocalServiceBaseImpl
	implements pluginMetricsLocalService {
	/**
	 * Adds the plugin metrics to the database. Also notifies the appropriate model listeners.
	 *
	 * @param pluginMetrics the plugin metrics to add
	 * @return the plugin metrics that was added
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics addpluginMetrics(pluginMetrics pluginMetrics)
		throws SystemException {
		pluginMetrics.setNew(true);

		return pluginMetricsPersistence.update(pluginMetrics, false);
	}

	/**
	 * Creates a new plugin metrics with the primary key. Does not add the plugin metrics to the database.
	 *
	 * @param fooId the primary key for the new plugin metrics
	 * @return the new plugin metrics
	 */
	public pluginMetrics createpluginMetrics(long fooId) {
		return pluginMetricsPersistence.create(fooId);
	}

	/**
	 * Deletes the plugin metrics with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fooId the primary key of the plugin metrics to delete
	 * @throws PortalException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deletepluginMetrics(long fooId)
		throws PortalException, SystemException {
		pluginMetricsPersistence.remove(fooId);
	}

	/**
	 * Deletes the plugin metrics from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pluginMetrics the plugin metrics to delete
	 * @throws SystemException if a system exception occurred
	 */
	public void deletepluginMetrics(pluginMetrics pluginMetrics)
		throws SystemException {
		pluginMetricsPersistence.remove(pluginMetrics);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return pluginMetricsPersistence.findWithDynamicQuery(dynamicQuery);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return pluginMetricsPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return pluginMetricsPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Counts the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return pluginMetricsPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Gets the plugin metrics with the primary key.
	 *
	 * @param fooId the primary key of the plugin metrics to get
	 * @return the plugin metrics
	 * @throws PortalException if a plugin metrics with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics getpluginMetrics(long fooId)
		throws PortalException, SystemException {
		return pluginMetricsPersistence.findByPrimaryKey(fooId);
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
	public List<pluginMetrics> getpluginMetricses(int start, int end)
		throws SystemException {
		return pluginMetricsPersistence.findAll(start, end);
	}

	/**
	 * Gets the number of plugin metricses.
	 *
	 * @return the number of plugin metricses
	 * @throws SystemException if a system exception occurred
	 */
	public int getpluginMetricsesCount() throws SystemException {
		return pluginMetricsPersistence.countAll();
	}

	/**
	 * Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	 *
	 * @param pluginMetrics the plugin metrics to update
	 * @return the plugin metrics that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics updatepluginMetrics(pluginMetrics pluginMetrics)
		throws SystemException {
		pluginMetrics.setNew(false);

		return pluginMetricsPersistence.update(pluginMetrics, true);
	}

	/**
	 * Updates the plugin metrics in the database. Also notifies the appropriate model listeners.
	 *
	 * @param pluginMetrics the plugin metrics to update
	 * @param merge whether to merge the plugin metrics with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the plugin metrics that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public pluginMetrics updatepluginMetrics(pluginMetrics pluginMetrics,
		boolean merge) throws SystemException {
		pluginMetrics.setNew(false);

		return pluginMetricsPersistence.update(pluginMetrics, merge);
	}

	/**
	 * Gets the plugin metrics local service.
	 *
	 * @return the plugin metrics local service
	 */
	public pluginMetricsLocalService getpluginMetricsLocalService() {
		return pluginMetricsLocalService;
	}

	/**
	 * Sets the plugin metrics local service.
	 *
	 * @param pluginMetricsLocalService the plugin metrics local service
	 */
	public void setpluginMetricsLocalService(
		pluginMetricsLocalService pluginMetricsLocalService) {
		this.pluginMetricsLocalService = pluginMetricsLocalService;
	}

	/**
	 * Gets the plugin metrics remote service.
	 *
	 * @return the plugin metrics remote service
	 */
	public pluginMetricsService getpluginMetricsService() {
		return pluginMetricsService;
	}

	/**
	 * Sets the plugin metrics remote service.
	 *
	 * @param pluginMetricsService the plugin metrics remote service
	 */
	public void setpluginMetricsService(
		pluginMetricsService pluginMetricsService) {
		this.pluginMetricsService = pluginMetricsService;
	}

	/**
	 * Gets the plugin metrics persistence.
	 *
	 * @return the plugin metrics persistence
	 */
	public pluginMetricsPersistence getpluginMetricsPersistence() {
		return pluginMetricsPersistence;
	}

	/**
	 * Sets the plugin metrics persistence.
	 *
	 * @param pluginMetricsPersistence the plugin metrics persistence
	 */
	public void setpluginMetricsPersistence(
		pluginMetricsPersistence pluginMetricsPersistence) {
		this.pluginMetricsPersistence = pluginMetricsPersistence;
	}

	/**
	 * Gets the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Gets the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Gets the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Gets the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Gets the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Gets the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = pluginMetricsPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = pluginMetricsLocalService.class)
	protected pluginMetricsLocalService pluginMetricsLocalService;
	@BeanReference(type = pluginMetricsService.class)
	protected pluginMetricsService pluginMetricsService;
	@BeanReference(type = pluginMetricsPersistence.class)
	protected pluginMetricsPersistence pluginMetricsPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}