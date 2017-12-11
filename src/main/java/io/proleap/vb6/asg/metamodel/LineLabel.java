/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.List;

import io.proleap.vb6.asg.metamodel.statement.onerror.OnError;
import io.proleap.vb6.asg.metamodel.statement.resume.Resume;

public interface LineLabel extends ScopedElement, Declaration {

	void addOnError(OnError onError);

	void addResume(Resume resume);

	List<OnError> getOnErrors();

	List<Resume> getResumes();
}
