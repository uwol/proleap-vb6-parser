/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import io.proleap.vb6.asg.metamodel.Module;
import io.proleap.vb6.asg.metamodel.Scope;
import io.proleap.vb6.asg.metamodel.call.Call;
import io.proleap.vb6.asg.metamodel.call.MembersCall;
import io.proleap.vb6.asg.metamodel.impl.ScopedElementImpl;
import io.proleap.vb6.asg.metamodel.type.Type;

public class MembersCallImpl extends ScopedElementImpl implements MembersCall {

	protected final List<Call> subCalls = new ArrayList<Call>();

	public MembersCallImpl(final Module module, final Scope scope, final ParserRuleContext ctx) {
		super(module.getProgram(), module, scope, ctx);
	}

	@Override
	public void addSubCall(final Call call) {
		subCalls.add(call);
	}

	@Override
	public boolean equals(final Object obj) {
		final boolean result;

		if (super.equals(obj)) {
			result = true;
		} else if (obj instanceof MembersCall) {
			final MembersCall otherMembersCall = (MembersCall) obj;

			if (subCalls.size() > otherMembersCall.getSubCalls().size()) {
				result = false;
			} else {
				boolean equalSubCalls = true;

				for (int i = 0; i < subCalls.size(); i++) {
					final Call ownSubCall = subCalls.get(i);
					final Call otherSubCall = otherMembersCall.getSubCalls().get(i);

					equalSubCalls = equalSubCalls && ownSubCall.equals(otherSubCall);
				}

				result = equalSubCalls;
			}
		} else {
			result = false;
		}

		return result;
	}

	@Override
	public CallType getCallType() {
		return getLastSubCall().getCallType();
	}

	@Override
	public Call getLastSubCall() {
		return subCalls.get(subCalls.size() - 1);
	}

	@Override
	public String getName() {
		return getLastSubCall().getName();
	}

	@Override
	public List<Call> getSubCalls() {
		return subCalls;
	}

	@Override
	public Type getType() {
		return getLastSubCall().getType();
	}

	@Override
	public String toString() {
		return subCalls.toString();
	}

	@Override
	public Call unwrap() {
		return getLastSubCall().unwrap();
	}
}
