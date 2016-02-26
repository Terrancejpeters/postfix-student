package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple
 * arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator() {
		this.stack = new LinkedStack<Operand<Integer>>(); // TODO initialize
		// your LinkedStack
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		// TODO use all of the things you've built so far to
		// implement the algorithm for postfix expression evaluation

		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch (type) {
			case OPERAND:
				if (token.getOperand() == null){
					throw new IllegalPostfixExpressionException();
				}
				stack.push(token.getOperand());
				// TODO What do we do when we see an operand?
				break;
			case OPERATOR:
				if (token.getOperator() == null){
					throw new IllegalPostfixExpressionException();
				}
				if (token.getOperator().toString() == "neg") {
					token.getOperator().setOperand(0, stack.pop());
				} else {
					token.getOperator().setOperand(1, stack.pop());
					token.getOperator().setOperand(0, stack.pop());
				}
				Operand<Integer> temp = token.getOperator().performOperation();
				stack.push(temp);
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}
		}
		Integer rval = new Integer(stack.pop().getValue());
		// If there is not just 1 number left, and therefore an Illegal Postfix
		// Expression
		if (stack.size() != 0) {
			throw new IllegalPostfixExpressionException();
		}
		// TODO What do we return?
		while (!stack.isEmpty()) {
			stack.pop();
		}
		return rval;
	}

}
