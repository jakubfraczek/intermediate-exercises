package sda.code.intermediate.part1.exercises.patterns;

public class InvalidBuilderState extends RuntimeException {

	private static final long serialVersionUID = 3992517656138461008L;

	public InvalidBuilderState(String msg) {
		super(msg);
	}

	public InvalidBuilderState(NumberFormatException e) {
		super(e);
	}

	public InvalidBuilderState(RuntimeException e) {
		super(e);
	}

}
