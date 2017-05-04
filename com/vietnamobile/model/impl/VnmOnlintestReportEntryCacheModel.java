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
import com.liferay.portal.model.CacheModel;

import com.vietnamobile.model.VnmOnlintestReportEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VnmOnlintestReportEntry in entity cache.
 *
 * @author duymb
 * @see VnmOnlintestReportEntry
 * @generated
 */
public class VnmOnlintestReportEntryCacheModel implements CacheModel<VnmOnlintestReportEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{vnmOnlintestReportEntryId=");
		sb.append(vnmOnlintestReportEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", onlinetestEntryId=");
		sb.append(onlinetestEntryId);
		sb.append(", questionEntryId=");
		sb.append(questionEntryId);
		sb.append(", correctTime=");
		sb.append(correctTime);
		sb.append(", wrongTime=");
		sb.append(wrongTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VnmOnlintestReportEntry toEntityModel() {
		VnmOnlintestReportEntryImpl vnmOnlintestReportEntryImpl = new VnmOnlintestReportEntryImpl();

		vnmOnlintestReportEntryImpl.setVnmOnlintestReportEntryId(vnmOnlintestReportEntryId);
		vnmOnlintestReportEntryImpl.setGroupId(groupId);
		vnmOnlintestReportEntryImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			vnmOnlintestReportEntryImpl.setCreateDate(null);
		}
		else {
			vnmOnlintestReportEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vnmOnlintestReportEntryImpl.setModifiedDate(null);
		}
		else {
			vnmOnlintestReportEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		vnmOnlintestReportEntryImpl.setOnlinetestEntryId(onlinetestEntryId);
		vnmOnlintestReportEntryImpl.setQuestionEntryId(questionEntryId);
		vnmOnlintestReportEntryImpl.setCorrectTime(correctTime);
		vnmOnlintestReportEntryImpl.setWrongTime(wrongTime);

		vnmOnlintestReportEntryImpl.resetOriginalValues();

		return vnmOnlintestReportEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		vnmOnlintestReportEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		onlinetestEntryId = objectInput.readLong();
		questionEntryId = objectInput.readLong();
		correctTime = objectInput.readInt();
		wrongTime = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(vnmOnlintestReportEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(onlinetestEntryId);
		objectOutput.writeLong(questionEntryId);
		objectOutput.writeInt(correctTime);
		objectOutput.writeInt(wrongTime);
	}

	public long vnmOnlintestReportEntryId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long onlinetestEntryId;
	public long questionEntryId;
	public int correctTime;
	public int wrongTime;
}