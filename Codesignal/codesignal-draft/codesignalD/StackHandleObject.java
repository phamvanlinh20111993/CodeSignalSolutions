import java.util.List;

public class StackHandleObject {
	String operator;
	List<String> operands;
	List<String> variables;
	String keywords;

	String any;

	public StackHandleObject(String operator, List<String> operands, List<String> variables, String keywords) {
		super();
		this.operator = operator;
		this.operands = operands;
		this.variables = variables;
		this.keywords = keywords;
	}

	public StackHandleObject(String operator, String keywords) {
		super();
		this.operator = operator;
		this.keywords = keywords;
	}

	public StackHandleObject(String any) {
		super();
		this.any = any;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<String> getOperands() {
		return operands;
	}

	public void setOperands(List<String> operands) {
		this.operands = operands;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

//	@Override
//	public String toString() {
//		return "StackHandleObject [operator=" + operator + ", operands=" + operands + ", variables=" + variables
//				+ ", keywords=" + keywords + ", any=" + any + "]";
//	}

	@Override
	public String toString() {
		return any;
	}

}
