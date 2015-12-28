package com.jens.ServerLibrary.Utils;

public class DataSource {
	
	protected Object[] source;
	
	public DataSource(Object... source) {
		this.source = source;
	}
	
	public Object[] getSource() {
		return source;
	}
	
	public Object getSource(int position){
		return source[position - 1];
	}

}
