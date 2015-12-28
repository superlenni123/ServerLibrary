package com.jens.ServerLibrary.Utils;

public class DataSource {
	
	protected Object[] source;
	
	/**
	 * Initilizes a new Datasource (Object Array) to
	 * store Massdata.
	 * @param source Object Array that should be saved into a Source
	 */
	
	public DataSource(Object... source) {
		this.source = source;
	}
	
	/**
	 * Returns the complete saved Object Array.
	 * @return Object Array
	 */
	
	public Object[] getSource() {
		return source;
	}
	
	/**
	 * Returns the Object on the specified Positon
	 * in the Source.
	 * @param position gives the specified postion of the Object in the Source
	 * @return Object out of the Array
	 */
	
	public Object getSource(int position){
		return source[position - 1];
	}

}
