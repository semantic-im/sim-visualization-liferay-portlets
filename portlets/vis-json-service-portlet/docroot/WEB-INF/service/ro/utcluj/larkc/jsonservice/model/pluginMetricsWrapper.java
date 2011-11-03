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

package ro.utcluj.larkc.jsonservice.model;

/**
 * <p>
 * This class is a wrapper for {@link pluginMetrics}.
 * </p>
 *
 * @author    UTCluj
 * @see       pluginMetrics
 * @generated
 */
public class pluginMetricsWrapper implements pluginMetrics {
	public pluginMetricsWrapper(pluginMetrics pluginMetrics) {
		_pluginMetrics = pluginMetrics;
	}

	public long getPrimaryKey() {
		return _pluginMetrics.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_pluginMetrics.setPrimaryKey(pk);
	}

	public long getFooId() {
		return _pluginMetrics.getFooId();
	}

	public void setFooId(long fooId) {
		_pluginMetrics.setFooId(fooId);
	}

	public long getCompanyId() {
		return _pluginMetrics.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_pluginMetrics.setCompanyId(companyId);
	}

	public long getUserId() {
		return _pluginMetrics.getUserId();
	}

	public void setUserId(long userId) {
		_pluginMetrics.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginMetrics.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_pluginMetrics.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _pluginMetrics.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_pluginMetrics.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _pluginMetrics.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_pluginMetrics.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _pluginMetrics.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_pluginMetrics.setModifiedDate(modifiedDate);
	}

	public java.lang.String getField1() {
		return _pluginMetrics.getField1();
	}

	public void setField1(java.lang.String field1) {
		_pluginMetrics.setField1(field1);
	}

	public boolean getField2() {
		return _pluginMetrics.getField2();
	}

	public boolean isField2() {
		return _pluginMetrics.isField2();
	}

	public void setField2(boolean field2) {
		_pluginMetrics.setField2(field2);
	}

	public int getField3() {
		return _pluginMetrics.getField3();
	}

	public void setField3(int field3) {
		_pluginMetrics.setField3(field3);
	}

	public java.util.Date getField4() {
		return _pluginMetrics.getField4();
	}

	public void setField4(java.util.Date field4) {
		_pluginMetrics.setField4(field4);
	}

	public java.lang.String getField5() {
		return _pluginMetrics.getField5();
	}

	public void setField5(java.lang.String field5) {
		_pluginMetrics.setField5(field5);
	}

	public ro.utcluj.larkc.jsonservice.model.pluginMetrics toEscapedModel() {
		return _pluginMetrics.toEscapedModel();
	}

	public boolean isNew() {
		return _pluginMetrics.isNew();
	}

	public void setNew(boolean n) {
		_pluginMetrics.setNew(n);
	}

	public boolean isCachedModel() {
		return _pluginMetrics.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_pluginMetrics.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _pluginMetrics.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_pluginMetrics.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _pluginMetrics.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _pluginMetrics.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_pluginMetrics.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _pluginMetrics.clone();
	}

	public int compareTo(
		ro.utcluj.larkc.jsonservice.model.pluginMetrics pluginMetrics) {
		return _pluginMetrics.compareTo(pluginMetrics);
	}

	public int hashCode() {
		return _pluginMetrics.hashCode();
	}

	public java.lang.String toString() {
		return _pluginMetrics.toString();
	}

	public java.lang.String toXmlString() {
		return _pluginMetrics.toXmlString();
	}

	public pluginMetrics getWrappedpluginMetrics() {
		return _pluginMetrics;
	}

	private pluginMetrics _pluginMetrics;
}