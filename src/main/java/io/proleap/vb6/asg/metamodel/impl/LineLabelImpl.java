/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.LineLabel;
import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;

public class LineLabelImpl extends ScopedElementImpl implements LineLabel {

	protected final String name;

	protected final List<OnError> onErrors = new ArrayList<OnError>();

	protected final List<Resume> resumes = new ArrayList<Resume>();

	public LineLabelImpl(final String name, final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(module.getProgram(), module, scope, ctx);

		this.name = name;
	}

	@Override
	public void addOnError(final OnError onError) {
		if (!onErrors.contains(onError)) {
			onErrors.add(onError);
		}
	}

	@Override
	public void addResume(final Resume resume) {
		if (!resumes.contains(resume)) {
			resumes.add(resume);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<OnError> getOnErrors() {
		return onErrors;
	}

	@Override
	public List<Resume> getResumes() {
		return resumes;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}

}
