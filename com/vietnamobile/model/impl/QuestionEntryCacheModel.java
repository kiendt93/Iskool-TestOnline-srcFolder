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

import com.vietnamobile.model.QuestionEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing QuestionEntry in entity cache.
 *
 * @author duymb
 * @see QuestionEntry
 * @generated
 */
public class QuestionEntryCacheModel implements CacheModel<QuestionEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{questionEntryId=");
		sb.append(questionEntryId);
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
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", levelQuestion=");
		sb.append(levelQuestion);
		sb.append(", answer=");
		sb.append(answer);
		sb.append(", score=");
		sb.append(score);
		sb.append(", questionContent=");
		sb.append(questionContent);
		sb.append(", a=");
		sb.append(a);
		sb.append(", b=");
		sb.append(b);
		sb.append(", c=");
		sb.append(c);
		sb.append(", d=");
		sb.append(d);
		sb.append(", title=");
		sb.append(title);
		sb.append(", selection1=");
		sb.append(selection1);
		sb.append(", selection2=");
		sb.append(selection2);
		sb.append(", selection3=");
		sb.append(selection3);
		sb.append(", selection4=");
		sb.append(selection4);
		sb.append(", typeExam=");
		sb.append(typeExam);
		sb.append(", inExam=");
		sb.append(inExam);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public QuestionEntry toEntityModel() {
		QuestionEntryImpl questionEntryImpl = new QuestionEntryImpl();

		questionEntryImpl.setQuestionEntryId(questionEntryId);
		questionEntryImpl.setGroupId(groupId);
		questionEntryImpl.setCompanyId(companyId);
		questionEntryImpl.setUserId(userId);

		if (userName == null) {
			questionEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			questionEntryImpl.setCreateDate(null);
		}
		else {
			questionEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			questionEntryImpl.setModifiedDate(null);
		}
		else {
			questionEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (subject == null) {
			questionEntryImpl.setSubject(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setSubject(subject);
		}

		questionEntryImpl.setLevelQuestion(levelQuestion);

		if (answer == null) {
			questionEntryImpl.setAnswer(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setAnswer(answer);
		}

		questionEntryImpl.setScore(score);

		if (questionContent == null) {
			questionEntryImpl.setQuestionContent(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setQuestionContent(questionContent);
		}

		if (a == null) {
			questionEntryImpl.setA(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setA(a);
		}

		if (b == null) {
			questionEntryImpl.setB(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setB(b);
		}

		if (c == null) {
			questionEntryImpl.setC(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setC(c);
		}

		if (d == null) {
			questionEntryImpl.setD(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setD(d);
		}

		if (title == null) {
			questionEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setTitle(title);
		}

		if (selection1 == null) {
			questionEntryImpl.setSelection1(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setSelection1(selection1);
		}

		if (selection2 == null) {
			questionEntryImpl.setSelection2(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setSelection2(selection2);
		}

		if (selection3 == null) {
			questionEntryImpl.setSelection3(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setSelection3(selection3);
		}

		if (selection4 == null) {
			questionEntryImpl.setSelection4(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setSelection4(selection4);
		}

		if (typeExam == null) {
			questionEntryImpl.setTypeExam(StringPool.BLANK);
		}
		else {
			questionEntryImpl.setTypeExam(typeExam);
		}

		questionEntryImpl.setInExam(inExam);
		questionEntryImpl.setStatus(status);

		questionEntryImpl.resetOriginalValues();

		return questionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		questionEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		subject = objectInput.readUTF();
		levelQuestion = objectInput.readInt();
		answer = objectInput.readUTF();
		score = objectInput.readDouble();
		questionContent = objectInput.readUTF();
		a = objectInput.readUTF();
		b = objectInput.readUTF();
		c = objectInput.readUTF();
		d = objectInput.readUTF();
		title = objectInput.readUTF();
		selection1 = objectInput.readUTF();
		selection2 = objectInput.readUTF();
		selection3 = objectInput.readUTF();
		selection4 = objectInput.readUTF();
		typeExam = objectInput.readUTF();
		inExam = objectInput.readInt();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(questionEntryId);
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

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		objectOutput.writeInt(levelQuestion);

		if (answer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(answer);
		}

		objectOutput.writeDouble(score);

		if (questionContent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(questionContent);
		}

		if (a == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(a);
		}

		if (b == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(b);
		}

		if (c == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(c);
		}

		if (d == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(d);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (selection1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selection1);
		}

		if (selection2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selection2);
		}

		if (selection3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selection3);
		}

		if (selection4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selection4);
		}

		if (typeExam == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(typeExam);
		}

		objectOutput.writeInt(inExam);
		objectOutput.writeInt(status);
	}

	public long questionEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String subject;
	public int levelQuestion;
	public String answer;
	public double score;
	public String questionContent;
	public String a;
	public String b;
	public String c;
	public String d;
	public String title;
	public String selection1;
	public String selection2;
	public String selection3;
	public String selection4;
	public String typeExam;
	public int inExam;
	public int status;
}