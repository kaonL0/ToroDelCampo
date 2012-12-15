package com.alnaiyr.generator;

public interface FactoryPattern<S> {

    /**
     * Gets parameters if factory is a factory that the pattern can configure.
     * 
     * @param factory
     * @return
     */
    public S getParameters(Factory<S> factory);

}
