/**
 * 
 */
package com.basis.gof.visitor;

/**
 * @Description: 被访问者
 * @author zjl
 * @date 2014年12月25日 上午10:43:11
 */
public interface Visitable {
    
	/**
	 * 接收访问者
	 * @param visitor
	 */
	void accept(Visitor visitor);
}
