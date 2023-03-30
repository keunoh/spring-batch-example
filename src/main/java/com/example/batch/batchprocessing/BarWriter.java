package com.example.batch.batchprocessing;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class BarWriter implements ItemWriter<Bar> {
    @Override
    public void write(Chunk<? extends Bar> chunk) throws Exception {
        //write bars
    }
}
