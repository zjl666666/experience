package com.basis.collection.reference;

public class VeryBig {

	private String ident;
	
	public VeryBig(String id){
		this.ident=id;
	}
	
    protected void finalize(){
    	System.out.println("id 为=="+this.ident+"被回收了");
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VeryBig other = (VeryBig) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}
    
    
}

