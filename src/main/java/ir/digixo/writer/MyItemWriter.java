package ir.digixo.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class MyItemWriter implements ItemWriter<Long> {

    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {

        System.out.println("Inside item writer, Items : ");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
