/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;

public class ParentshipBuildingVisitor extends ASTVisitor {

	private final Map parents = new HashMap();
	
	private final ArrayList stack = new ArrayList();
	
	private void push(ASTNode node) {
		stack.add(node);
	}
	
	private ASTNode peek() {
		return (ASTNode) stack.get(stack.size() - 1);
	}
	
	private void pop() {
		stack.remove(stack.size() - 1);
	}

	public boolean visitGeneral(ASTNode node) throws Exception {
		if (!stack.isEmpty())
			parents.put(node, peek());
		push(node);
		return true;
	}
	
	public void endvisitGeneral(ASTNode node) throws Exception {
		Assert.isTrue(node == peek());
		pop();
	}

	public Map getParents() {
		return parents;
	}
	
}
