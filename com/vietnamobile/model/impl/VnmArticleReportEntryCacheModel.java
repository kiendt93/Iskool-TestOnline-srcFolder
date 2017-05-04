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

package com.vietnamobile.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.vietnamobile.model.VnmArticleReportEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VnmArticleReportEntry in entity cache.
 *
 * @author duymb
 * @see VnmArticleReportEntry
 * @generated
 */
public class VnmArticleReportEntryCacheModel implements CacheModel<VnmArticleReportEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{vnmArticleReportEntryId=");
		sb.append(vnmArticleReportEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", title=");
		sb.append(title);
		sb.append(", action=");
		sb.append(action);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VnmArticleReportEntry toEntityModel() {
		VnmArticleReportEntryImpl vnmArticleReportEntryImpl = new VnmArticleReportEntryImpl();

		vnmArticleReportEntryImpl.setVnmArticleReportEntryId(vnmArticleReportEntryId);
		vnmArticleReportEntryImpl.setGroupId(groupId);
		vnmArticleReportEntryImpl.setCompanyId(companyId);
		vnmArticleReportEntryImpl.setUserId(userId);

		if (userName == null) {
			vnmArticleReportEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			vnmArticleReportEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			vnmArticleReportEntryImpl.setCreateDate(null);
		}
		else {
			vnmArticleReportEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vnmArticleReportEntryImpl.setModifiedDate(null);
		}
		else {
			vnmArticleReportEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (articleId == null) {
			vnmArticleReportEntryImpl.setArticleId(StringPool.BLANK);
		}
		else {
			vnmArticleReportEntryImpl.setArticleId(articleId);
		}

		vnmArticleReportEntryImpl.setVersion(version);

		if (title == null) {
			vnmArticleReportEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			vnmArticleReportEntryImpl.setTitle(title);
		}

		if (action == null) {
			vnmArticleReportEntryImpl.setAction(StringPool.BLANK);
		}
		else {
			vnmArticleReportEntryImpl.setAction(action);
		}

		vnmArticleReportEntryImpl.setStatus(status);

		vnmArticleReportEntryImpl.resetOriginalValues();

		return vnmArticleReportEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		vnmArticleReportEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		articleId = objectInput.readUTF();
		version = objectInput.readDouble();
		title = objectInput.readUTF();
		action = objectInput.readUTF();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(vnmArticleReportEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (articleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(articleId);
		}

		objectOutput.writeDouble(version);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (action == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(action);
		}

		objectOutput.writeInt(status);
	}

	public long vnmArticleReportEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String articleId;
	public double version;
	public String title;
	public String action;
	public int status;
}