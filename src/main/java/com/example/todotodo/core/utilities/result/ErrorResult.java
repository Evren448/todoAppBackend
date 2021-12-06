package com.example.todotodo.core.utilities.result;

public class ErrorResult extends Result {

	public ErrorResult(String message) {
		super(message, false);
	}

	public ErrorResult() {
		super(false);
	}
}
