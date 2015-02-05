//: annotations/ExtractInterface.java
// APT-based annotation processing.
package com.basis.annotation.apt;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
	public String value();
} // /:~
