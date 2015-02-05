//: annotations/database/Uniqueness.java
// Sample of nested annotations
package com.basis.annotation.database;

public @interface Uniqueness {
	Constraints constraints() default @Constraints(unique = true);
} // /:~
