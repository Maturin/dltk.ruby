package org.eclipse.dltk.ruby.ui.tests.text.completion;

import junit.framework.Test;

import org.eclipse.dltk.codeassist.RelevanceConstants;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.tests.model.AbstractModelCompletionTests;
import org.eclipse.dltk.core.tests.model.CompletionTestsRequestor;
import org.eclipse.dltk.ruby.ui.tests.internal.RubyUITestsPlugin;

public class RubyCompletionTests extends AbstractModelCompletionTests {

	private static final int RELEVANCE = (RelevanceConstants.R_DEFAULT
			+ RelevanceConstants.R_INTERESTING + RelevanceConstants.R_CASE + RelevanceConstants.R_NON_RESTRICTED);

	public RubyCompletionTests(String name) {
		super(RubyUITestsPlugin.PLUGIN_ID, name);
	}

	public void setUpSuite() throws Exception {
		PROJECT = setUpScriptProject("completion");

		super.setUpSuite();
	}

	public static Test suite() {
		return new Suite(RubyCompletionTests.class);
	}

	private String makeResult(String[] elements, String[] completions, int[] relevance) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; ++i) {
			buffer.append("element:" + elements[i] + "    completion:" + completions[i]
					+ "    relevance:" + relevance[i]);

			if (i != elements.length - 1) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	private String makeResult(String[] elements) {
		String[] completions = new String[elements.length];
		int[] relevance = new int[elements.length];
		for (int i = 0; i < elements.length; ++i) {
			completions[i] = elements[i];
			relevance[i] = RELEVANCE;
		}
		return makeResult(elements, completions, relevance);
	}

	private String makeResult(String[] elements, int[] relevance) {
		String[] completions = new String[elements.length];
		for (int i = 0; i < elements.length; ++i) {
			completions[i] = elements[i];
		}
		return makeResult(elements, completions, relevance);
	}

	public void testCompletion001() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "new.rb");

		String str = cu.getSource();
		String completeBehind = "Foo.new.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}

	public void testCompletion002() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "inner.rb");

		String str = cu.getSource();
		String completeBehind = "Foo42::";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}
	
	public void testCompletion003() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "c1.rb");

		String str = cu.getSource();
		String completeBehind = "x.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}
	
	public void testCompletion004() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "c2.rb");

		String str = cu.getSource();
		String completeBehind = "x.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}
	
	public void testCompletion005() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "c3.rb");

		String str = cu.getSource();
		String completeBehind = "x.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}
	
	public void testCompletion006() throws ModelException {
		CompletionTestsRequestor requestor = new CompletionTestsRequestor();
		ISourceModule cu = getSourceModule("completion", "src", "c4.rb");

		String str = cu.getSource();
		String completeBehind = "x.";
		int cursorLocation = str.lastIndexOf(completeBehind) + completeBehind.length();
		cu.codeComplete(cursorLocation, requestor);

		assertTrue(requestor.getResults().length() > 0);
	}
	
}
