package com.example.todotodo.core.utilities.result;

public class DataSuccessResult<T> extends DataResult<T>{

	public DataSuccessResult(T data, String message) {
		super(data, message, true);
		// TODO Auto-generated constructor stub
	}
	
	public DataSuccessResult(T data) {
		super(data, true);
		// TODO Auto-generated constructor stub
	}
	
	public DataSuccessResult(String message) {
		super(null,message,true);
	}
	
	public DataSuccessResult() {
		super(null,true);
	}

}
