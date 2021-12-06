package com.example.todotodo.core.utilities.result;

public class ErrorDataResult<T> extends DataResult<T> {

	public ErrorDataResult(T data, String message) {
		super(data, message, false);
		// TODO Auto-generated constructor stub
	}
	
	public ErrorDataResult(T data) {
		super(data, false);
		// TODO Auto-generated constructor stub
	}
	
	public ErrorDataResult(String message) {
		super(null,message, false);
		// TODO Auto-generated constructor stub
	}
	
	public ErrorDataResult() {
		super(null, false);
		// TODO Auto-generated constructor stub
	}

}
