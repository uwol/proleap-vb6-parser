/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.metamodel.LineLabel;
import io.proleap.vb6.parser.metamodel.Module;
import io.proleap.vb6.parser.metamodel.OnError;
import io.proleap.vb6.parser.metamodel.Resume;
import io.proleap.vb6.parser.metamodel.oop.Scope;

public class LineLabelImpl extends VbScopedElementImpl implements LineLabel {

	protected final String name;

	protected final List<OnError> onErrors = new ArrayList<OnError>();

	protected final List<Resume> resumes = new ArrayList<Resume>();

	public LineLabelImpl(final String name, final Module module, final Scope superScope, final ParseTree ctx) {
		super(module, superScope, ctx);

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
