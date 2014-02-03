/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Original work Copyright (c) 2009-2013 by the Twig Team
 * Modified work Copyright (c) 2013 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.node.expression;

import java.util.List;

import com.mitchellbosecke.pebble.compiler.Compiler;
import com.mitchellbosecke.pebble.node.NodeExpression;

public class NodeExpressionArguments extends NodeExpression {

	/**
	 * An invokation of a function or macro can contain full fledged expressions
	 * in the arguments
	 */
	private List<NodeExpression> args;

	public NodeExpressionArguments(int lineNumber, List<NodeExpression> args) {
		super(lineNumber);
		this.args = args;
	}

	@Override
	public void compile(Compiler compiler) {
		compiler.raw("(");

		int i = 0;
		for (NodeExpression var : args) {
			compiler.subcompile(var, true);

			if (i < (args.size() - 1)) {
				compiler.raw(", ");
			}
		}

		compiler.raw(")");
	}

	public List<NodeExpression> getArgs() {
		return args;
	}

}
