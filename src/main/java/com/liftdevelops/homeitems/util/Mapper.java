package com.liftdevelops.homeitems.util;

public interface Mapper <A, B> {

    B mapTo(A a);
    A mapFrom(B b);

}
