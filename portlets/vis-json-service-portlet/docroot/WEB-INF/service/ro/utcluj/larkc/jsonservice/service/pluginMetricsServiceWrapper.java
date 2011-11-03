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
 * This class is a wrapper for {@link pluginMetricsService}.
 * </p>
 *
 * @author    UTCluj
 * @see       pluginMetricsService
 * @generated
 */
public class pluginMetricsServiceWrapper implements pluginMetricsService {
	public pluginMetricsServiceWrapper(
		pluginMetricsService pluginMetricsService) {
		_pluginMetricsService = pluginMetricsService;
	}

	public pluginMetricsService getWrappedpluginMetricsService() {
		return _pluginMetricsService;
	}

	private pluginMetricsService _pluginMetricsService;
}