package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.lexer.yacc.SourcePosition;

/** 
 *
 */
public class DLTKRubyWarnings implements IDLTKRubyWarnings {

	private final IProblemReporter problemReporter;

	public DLTKRubyWarnings(IProblemReporter problemReporter) {
		this.problemReporter = problemReporter;
	}

	public void warn(ISourcePosition position, String message) {
		DefaultProblem problem = new DefaultProblem(position.getFile(),
				message, IProblem.Unclassified, new String[0],
				ProblemSeverities.Warning, position.getStartOffset(), position
						.getEndOffset(), position.getStartLine());
		problemReporter.reportProblem(problem);
	}

	public boolean isVerbose() {
		return false;
	}

	public void warning(ISourcePosition position, String message) {
		if (isVerbose()) {
			DefaultProblem problem = new DefaultProblem(position.getFile(),
					message, IProblem.Unclassified, new String[0],
					ProblemSeverities.Warning, position.getStartOffset(),
					position.getEndOffset(), position.getStartLine());
			problemReporter.reportProblem(problem);
		}
	}

	public void error(ISourcePosition position, String message) {
		DefaultProblem problem = new DefaultProblem(position.getFile(),
				message, IProblem.Unclassified, new String[0],
				ProblemSeverities.Error, position.getStartOffset(), position
						.getEndOffset(), position.getStartLine());
		problemReporter.reportProblem(problem);
	}

	public void warn(String message) {
		warn(new SourcePosition(), message);
	}

	public void warning(String message) {
		warning(new SourcePosition(), message);
	}
}
