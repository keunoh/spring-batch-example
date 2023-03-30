package com.example.batch.batchprocessing;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CompositeItemWriter<T> implements ItemWriter<T> {

    ItemWriter<T> itemWriter;

    public CompositeItemWriter(ItemWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
    }

    @Override
    public void write(Chunk<? extends T> items) throws Exception {
        //Add business logic here
        itemWriter.write(items);
    }

    public void setDelegate(ItemWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
    }
}
