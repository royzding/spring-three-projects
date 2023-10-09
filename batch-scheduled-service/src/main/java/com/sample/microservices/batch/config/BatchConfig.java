package com.sample.microservices.batch.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.microservices.batch.data.model.Coffee;
import com.sample.microservices.batch.data.model.ManagerEntity;
import com.sample.microservices.batch.data.model.Person;
import com.sample.microservices.batch.listener.JobCompletionNotificationListener;
import com.sample.microservices.batch.listener.MyStepExecutionListener;
import com.sample.microservices.batch.processor.CoffeeItemProcessor;
import com.sample.microservices.batch.processor.MyJpaItemProcessor;
import com.sample.microservices.batch.repository.CoffeeRepository;
import com.sample.microservices.batch.repository.ManagerEntityRepository;
import com.sample.microservices.batch.repository.PersonRepository;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class BatchConfig {

    public static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriver;
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String databaseUsername;
    @Value("${spring.datasource.password}")
    private String databasePassword;

    
    final JobRepository jobRepository;
    final PlatformTransactionManager batchTransactionManager;
    private static final int BATCH_SIZE = 3;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager batchTransactionManager) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
    }
     
//    @Autowired
//    private StepBuilder steps;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	@Lazy
	private ManagerEntityRepository meRepo;

	@Autowired
	@Lazy
	private PersonRepository pRepo;
	
	@Autowired
	CoffeeRepository	coffeeRepo;
	
	/////////FlatFileItemReader Example/////////
	    
	@Value("${file.input}")
	private String fileInput;
	
	@Bean
	public FlatFileItemReader<Coffee> reader() {
	    return new FlatFileItemReaderBuilder<Coffee>().name("coffeeItemReader")
	        .resource(new ClassPathResource(fileInput))
	        .delimited()
	        .names(new String[] { "brand", "origin", "characteristics" })
	        .fieldSetMapper(new BeanWrapperFieldSetMapper<Coffee>() {{
	            setTargetType(Coffee.class);
	         }})
	        .build();
	}
	
	@Bean
	public CoffeeItemProcessor processor() {
	    return new CoffeeItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Coffee> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<Coffee>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	        .sql("INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
	        .dataSource(dataSource)
	        .build();
	}
	
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Coffee> writer) {
	    return new StepBuilder("step1", jobRepository)
	        .<Coffee, Coffee> chunk(10, transactionManager)
	        .reader(reader())
	        .processor(processor())
	        .writer(writer)
	        .build();
	}
		

////////////////////////RepositoryItemReader Example////////////////////////////////////////////
	   
	@Bean
	public RepositoryItemReader<ManagerEntity> reader2() {
		RepositoryItemReader<ManagerEntity> reader = new RepositoryItemReader<>();
		reader.setRepository(meRepo);
		reader.setMethodName("findAll");
		
		Map<String, Direction> sorts = new HashMap<>();
		sorts.put("id", Direction.ASC);
		reader.setSort(sorts);
		
		return reader;
	}

	@Bean
	public RepositoryItemWriter<Person> writer2() {
		RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
		writer.setRepository(pRepo);
		writer.setMethodName("save");
		return writer;
	}    
	
	@Bean
	public MyJpaItemProcessor processor2() {
		return new MyJpaItemProcessor();
	}   

	@Bean
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager, RepositoryItemWriter<Person> writer2) {
	    return new StepBuilder("step2", jobRepository)
	        .<ManagerEntity, Person> chunk(10, transactionManager)
	        .reader(reader2())
	        .processor(processor2())
	        .writer(writer2)
	        .build();
	}

	@Bean
	public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1, Step step2) {
	    return new JobBuilder("importUserJob", jobRepository)
	        .incrementer(new RunIdIncrementer())
	        .listener(listener)
	        .flow(step1)
	        .next(step2)
	        .end()
	        .build();
	}
	

/*	
	@Bean
    public Step stepOne(){
		System.out.println("======================StepOne====================");
       return steps.get("stepOne")
                .tasklet(new MyTaskOne(pRepo))
                .build();
    }
     
    @Bean
    public Step stepTwo(){
    	System.out.println("======================StepTwo====================");
        return steps.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();
    }   

   @Bean
   public Job demoJob(	@Qualifier("stepOne") Step stepOne
						,@Qualifier("stepTwo") Step stepTwo
						//,@Qualifier("stepThree") Step stepThree
   				  ){
       return jobs.get("demoJob")
               .incrementer(new RunIdIncrementer())
               //.listener(new JobCompletionNotificationListener(jdbcTemplate, meRepo))
               .start(stepOne)
               .next(stepTwo)
               //.next(stepThree)
               .next(stepFour())
               .next(stepFive())
               .build();
   }
*/

/*	
	@Bean
	public Step stepFive() {
		
		System.out.println("======================StepFive====================");

		return steps //.get("stepFive")
		.<ManagerEntity, Person>chunk(10)
		.reader(reader5())
		.processor(processor5())
		.writer(writer5())
		.listener(new MyStepExecutionListener())
		.taskExecutor(taskExecutor())
		.throttleLimit(20)
		.build();
	}    

	@Bean
	public TaskExecutor taskExecutor() {
	    return new SimpleAsyncTaskExecutor("spring_batch");
	}     

@Bean
public Step stepFour() {
	
	System.out.println("======================StepFour====================");
	
    return steps.get("step4")
        .<Coffee, Coffee>chunk(10)
        .reader(reader4())
        .processor(processor4())
        .writer(writer4())
        .build();
}
	
*/

   ///////Conditional Steps///////////////////
/*   
   @Bean
   public Job demoJob(	@Qualifier("stepOne") Step stepOne, 
						@Qualifier("stepTwo") Step stepTwo,
						@Qualifier("stepThree") Step stepThree
   				  ){
       return jobs.get("demoJob")
               .incrementer(new RunIdIncrementer())
               .listener(new JobCompletionNotificationListener(jdbcTemplate, meRepo))
               .start(stepOne)
               .on("*").to(stepTwo)
               .from(stepOne).on("FAILED").to(stepThree)
               .from(stepTwo).on("*").to(stepFour())
               .from(stepThree).on("*").to(stepFive())
               .end()
               .build();
   }
*/   
/*
   @Bean
   public Step stepThree(StepBuilderFactory stepBuilderFactory, ItemReader<Account> reader,
                     ItemWriter<Person> writer, ItemProcessor<Account, Person> processor) {
       return stepBuilderFactory.get("stepThree")
               .<Account, Person>chunk(1000)
               .reader(reader)
               .processor(processor)
               .writer(writer)
               .build();
   }

   
    @Bean
    public ItemReader<Account> reader() throws Exception {
        String jpqlQuery = "select a from Account a";

    		JpaPagingItemReader<Account> reader = new JpaPagingItemReader<Account>();
    		reader.setQueryString(jpqlQuery);
    		reader.setEntityManagerFactory(entityManagerFactory().getObject());
    		reader.setPageSize(3);
    		reader.afterPropertiesSet();
    		reader.setSaveState(true);

    		return reader;
    }

    
     // The ItemProcessor is called after a new line is read and it allows the developer
     // to transform the data read
     // In our example it simply return the original object
     //
     // @return
     ///
    @Bean
    public ItemProcessor<Account, Person> processor() {
        return new MyItemProcessor();
    }

    /////
     // Nothing special here a simple JpaItemWriter
     // @return
     ///
    @Bean
    public ItemWriter<Person> writer() {
        JpaItemWriter writer = new JpaItemWriter<Person>();
        writer.setEntityManagerFactory(entityManagerFactory().getObject());

        return writer;
    }


///////////////////////////////////////////////////////////////////////////////
    
    
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }
    
    ///////////DataSource beans//////////////////////////////////
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

		return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setPackagesToScan("com.sample.microservices");
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaProperties(new Properties());
        return lef;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.ORACLE);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(true);

        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        return jpaVendorAdapter;
    }

/////////FlatFileItemReader Example/////////
    
    @Value("${file.input}")
    private String fileInput;

    @Bean
    public FlatFileItemReader<Coffee> reader4() {
        return new FlatFileItemReaderBuilder<Coffee>().name("coffeeItemReader")
            .resource(new ClassPathResource(fileInput))
            .delimited()
            .names(new String[] { "brand", "origin", "characteristics" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper<Coffee>() {{
                setTargetType(Coffee.class);
             }})
            .build();
    }

    @Bean
    public CoffeeItemProcessor processor4() {
        return new CoffeeItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Coffee> writer4(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Coffee>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Step stepFour() {
        return steps.get("step4")
            .<Coffee, Coffee>chunk(10)
            .reader(reader4())
            .processor(processor4())
            .writer(writer4(dataSource()))
            .build();
    }

////////////////////////RepositoryItemReader Example////////////////////////////////////////////
    
    @Bean
    public RepositoryItemReader<ManagerEntity> reader5() {
        RepositoryItemReader<ManagerEntity> reader = new RepositoryItemReader<>();
        reader.setRepository(meRepo);
        reader.setMethodName("findAll");
        
        Map<String, Direction> sorts = new HashMap<>();
        sorts.put("id", Direction.ASC);
        reader.setSort(sorts);

        return reader;
    }
    
    @Bean
    public RepositoryItemWriter<Person> writer5() {
        RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
        writer.setRepository(pRepo);
        writer.setMethodName("save");
        return writer;
    }    
    
    @Bean
    public MyJpaItemProcessor processor5() {
        return new MyJpaItemProcessor();
    }    
    
    @Bean
    public Step stepFive() {
        return steps.get("stepFive")
            .<ManagerEntity, Person>chunk(10)
            .reader(reader5())
            .processor(processor5())
            .writer(writer5())
            .listener(new MyStepExecutionListener())
			.taskExecutor(taskExecutor())
			.throttleLimit(20)
            .build();
    }    
*/
   
    ////////Parallel Steps////////////////
/*

	@Bean
	public Job job() {
	    return jobBuilderFactory.get("job")
	        .start(splitFlow())
	        .next(step4())
	        .build()        //builds FlowJobBuilder instance
	        .build();       //builds Job instance
	}
	
	@Bean
	public Flow splitFlow() {
	    return new FlowBuilder<SimpleFlow>("splitFlow")
	        .split(taskExecutor())
	        .add(flow1(), flow2())
	        .build();
	}
	
	@Bean
	public Flow flow1() {
	    return new FlowBuilder<SimpleFlow>("flow1")
	        .start(step1())
	        .next(step2())
	        .build();
	}
	
	@Bean
	public Flow flow2() {
	    return new FlowBuilder<SimpleFlow>("flow2")
	        .start(step3())
	        .build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
	    return new SimpleAsyncTaskExecutor("spring_batch");
	}     
 */
}