package com.springboot.valid_exception.common;

public class Constants {
	public enum ExceptionClass{
		PRODUCT("product");	//상수 하나 선언
		
		private String exceptionClass;
		
		ExceptionClass(String exceptionClass){
			this.exceptionClass = exceptionClass;
		}
		
		public String getExceptionClass() {
			return exceptionClass;
		}
		
		@Override
		public String toString() {
			return getExceptionClass() + " Exception. ";
		}
	}

}
