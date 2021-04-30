package InterviewPractice.CodeJavaEx;
/**
 * 
 * @author PhamVanLinh
 *
 */
class OutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126125043506566257L;
	private Long rangeLeft;
	private Long rangeRight;
	private String message;

	public OutOfRangeException(Long rangeLeft, Long rangeRight, String message) {
		super();
		this.rangeLeft = rangeLeft;
		this.rangeRight = rangeRight;
		this.message = message;
	}

	public Long getRangeLeft() {
		return rangeLeft;
	}

	public void setRangeLeft(Long rangeLeft) {
		this.rangeLeft = rangeLeft;
	}

	public Long getRangeRight() {
		return rangeRight;
	}

	public void setRangeRight(Long rangeRight) {
		this.rangeRight = rangeRight;
	}

	@Override
	public String getMessage() {
		return message.concat(" [" + this.rangeLeft + ", " + this.rangeRight + "]");
	}

	@Override
	public Throwable getCause() {
		return new OutOfRangeException(this.rangeLeft, this.rangeRight,
				"Value transfer out of this range ".concat(" [" + this.rangeLeft + ", " + this.rangeRight + "]"));
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
