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

import com.vietnamobile.model.SubjectEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SubjectEntry in entity cache.
 *
 * @author duymb
 * @see SubjectEntry
 * @generated
 */
public class SubjectEntryCacheModel implements CacheModel<SubjectEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{subjectEntryId=");
		sb.append(subjectEntryId);
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
		sb.append(", course=");
		sb.append(course);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", description=");
		sb.append(description);
		sb.append(", examType=");
		sb.append(examType);
		sb.append(", totalQuestion=");
		sb.append(totalQuestion);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SubjectEntry toEntityModel() {
		SubjectEntryImpl subjectEntryImpl = new SubjectEntryImpl();

		subjectEntryImpl.setSubjectEntryId(subjectEntryId);
		subjectEntryImpl.setGroupId(groupId);
		subjectEntryImpl.setCompanyId(companyId);
		subjectEntryImpl.setUserId(userId);

		if (userName == null) {
			subjectEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			subjectEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			subjectEntryImpl.setCreateDate(null);
		}
		else {
			subjectEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			subjectEntryImpl.setModifiedDate(null);
		}
		else {
			subjectEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (course == null) {
			subjectEntryImpl.setCourse(StringPool.BLANK);
		}
		else {
			subjectEntryImpl.setCourse(course);
		}

		if (subject == null) {
			subjectEntryImpl.setSubject(StringPool.BLANK);
		}
		else {
			subjectEntryImpl.setSubject(subject);
		}

		if (description == null) {
			subjectEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			subjectEntryImpl.setDescription(description);
		}

		if (examType == null) {
			subjectEntryImpl.setExamType(StringPool.BLANK);
		}
		else {
			subjectEntryImpl.setExamType(examType);
		}

		subjectEntryImpl.setTotalQuestion(totalQuestion);
		subjectEntryImpl.setStatus(status);

		subjectEntryImpl.resetOriginalValues();

		return subjectEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		subjectEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		course = objectInput.readUTF();
		subject = objectInput.readUTF();
		description = objectInput.readUTF();
		examType = objectInput.readUTF();
		totalQuestion = objectInput.readInt();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(subjectEntryId);
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

		if (course == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(course);
		}

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (examType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(examType);
		}

		objectOutput.writeInt(totalQuestion);
		objectOutput.writeInt(status);
	}

	public long subjectEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String course;
	public String subject;
	public String description;
	public String examType;
	public int totalQuestion;
	public int status;
}