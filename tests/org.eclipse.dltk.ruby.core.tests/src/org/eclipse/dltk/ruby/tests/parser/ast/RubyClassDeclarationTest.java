/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.tests.parser.ast;

import org.eclipse.dltk.ruby.ast.RubyClassDeclaration;
import org.eclipse.dltk.ruby.tests.Activator;
import org.eclipse.dltk.ruby.tests.parser.AbstractASTTest;
			
public class RubyClassDeclarationTest extends AbstractASTTest {

	public RubyClassDeclarationTest(String name) {
		super(Activator.PLUGIN_ID, name);
	}

	protected Class getExpectedClass () {
		return RubyClassDeclaration.class;	
	}
	
	public void test0 () throws Exception {
		String text = "class Foo; end";
		checkNode (text, 0, text.length());
	}
	
	public void test1 () throws Exception {
		String text = "class ::A\n end";
		checkNode (text, 0, text.length());
	}
	
	public void test2 () throws Exception {
		String text = "class ::D::E::F::D\n end";
		checkNode (text, 0, text.length());
	}
	
	public void test4 () throws Exception {
		String text = "class Bar < Foo \n end";
		checkNode (text, 0, text.length());
	}
	
	public void test5 () throws Exception {
		String text = "class ::Preved < Foo \n end";
		checkNode (text, 0, text.length());
	}
	
	public void test6 () throws Exception {
		String text = "class ::Preved < ::Foo\n end";
		checkNode (text, 0, text.length());
	}
	
	public void test7 () throws Exception {
		String text = "class Preved::DBV < Foo::Coo ; end";
		checkNode (text, 0, text.length());
	}
	
}