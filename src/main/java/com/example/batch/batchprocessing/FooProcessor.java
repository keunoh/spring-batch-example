package com.example.batch.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

public class FooProcessor implements ItemProcessor<Foo, Bar> {
    @Override
    public Bar process(Foo foo) throws Exception {
        //Perform simple transformation, convert a Foo to a Bar
        return new Bar(foo);
    }
}
