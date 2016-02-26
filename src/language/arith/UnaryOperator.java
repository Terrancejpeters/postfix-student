package language.arith;

import language.Operand;
import language.Operator;

public abstract class UnaryOperator<T> implements Operator<T> {
	protected Operand<T> op0;

	public final int getNumberOfArguments() {
		return 1;
	}
	
	public void setOperand(int position, Operand<T> operand) {
		if (position > 0){
			throw new IllegalArgumentException("Cannot take more than one operand");
		}
		if(operand == null)
			throw new NullPointerException("Could not set null operand.");
		else{
			if (op0 != null){
				throw new IllegalStateException("Position 0 has been previously set.");
			}
			op0 = operand;
		}
	}
}
