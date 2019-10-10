package com.batchprocess.CSVFileReader.config;

import com.batchprocess.CSVFileReader.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory
                , ItemReader<User> itemReader, ItemProcessor<User, User> itemProcessor,
                   ItemWriter<User> itemWriter){
        Step step = stepBuilderFactory.get("ETL-File-Load")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return jobBuilderFactory.get("ETL-LOAD")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<User> flatFileItemReader(@Value("${input}") Resource resource){

//        new FlatFileItemReaderBuilder<User>().name("CSV Reader")
//                .resource(resource)
//                .delimited()
//                .names(new String[] {"id", "name","department"})
//                .lineMapper(lineMapper())
//                .fieldSetMapper( new BeanWrapperFieldSetMapper<User>(){{setTargetType(User.class);}})
//                .build();
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("CSV Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User>  defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"id", "name","dept"});

        beanWrapperFieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;
    }


}
