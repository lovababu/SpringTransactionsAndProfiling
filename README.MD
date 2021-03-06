## Developed by @PLovababu
## About the Application.

Spring 3.x application describes the AOP annotation based, Spring testing framework and Spring embedded database feature.
And also it gives the idea about Lombok and Logback frameworks.

##KeyPoints:

   1. <code>AppConfig.java</code>
      1. @PropertySource ,A convenient and declarative mechanism for adding a Property file.
      2. <code>
              @Bean
              public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
                  return new PropertySourcesPlaceholderConfigurer();
              }
         </code>

         static Bean configuration to Resolving ${...} placeholders in a Spring Bean. This is mandatory to work with
         <code>@Value() </code> annotation.
      3. <code>@EnableTransactionManagement</code> , It enables the Annotation based transaction management. <code>@Transactional</code>
      4. <code>@EnableAspectJAutoProxy</code>, Enables support for handling components marked with AspectJ's <code>@Aspect</code> annotation.
         Mandatory to advice the target objects.
      5. <code>@Slf4j</code>, Lombok declarative utility annotation to get the logger instance,
         instead of programmatically creating logger instance.
      6. To know about configuring EmbeddedDatabase refer AppConfig.java @Line 68 - 76

   2. <code>TimeProfiler.java</code>
      1. Aspect class, <code>@Aspect</code> annotation mark this class as Advice.
      2. <code>@PointCut</code>, This is set of one or more joint points where and advice should be executed.
         You can specify PointCuts using expressions or patterns.
         In this example all methods defined in SampleServiceImpl.java class will be advised by timeProfile() method.

## Observation after running successfully.

1. You can find the log like below. Refer <code>TimeProfiler.java @Around </code> annotated method.
   This tells you the time taken by the method to execute in milli seconds, this is nothing but profiling java methods.

   ====================== TimeProfiler Start =======================

   StopWatch  'com.lova.spring.trxns.profiler.TimeProfiler': running time (millis) = 180
   -----------------------------------------
   ms     %     Task name
   -----------------------------------------
   00180  100%  execution(SampleService.get(..))

   ====================== TimeProfiler End =======================

2. Some thing like below logs. It tells embedded data base initiating and executng .sql we specified with the db script.

May 02, 2015 9:36:07 PM org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory initDatabase \n
INFO: <b>Creating embedded database 'H2-Test-DB'</b> \n
May 02, 2015 9:36:07 PM org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript \n
INFO: <b>Executing SQL script from class path resource [dbscript/my-schema.sql]</b> \n
May 02, 2015 9:36:07 PM org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript \n
INFO: Done executing SQL script from class path resource [dbscript/my-schema.sql] in 11 ms. \n
May 02, 2015 9:36:07 PM org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript \n
INFO: <b>Executing SQL script from class path resource [dbscript/my-test-data.sql] </b> \n
May 02, 2015 9:36:07 PM org.springframework.jdbc.datasource.init.ResourceDatabasePopulator executeSqlScript \n
INFO: Done executing SQL script from class path resource [dbscript/my-test-data.sql] in 12 ms. \n

## Tech Stack.
1. Spring 3.2.10 and Transaction.
2. Spring Embedded database , H2 Type.
3. Spring AOP annotation based.
4. Hibernate ORM 4.2.10-Final.
5. Lombok
6. Gradle build tool.
7. Used IntelliJ IDEA for development (Optional for you.)

## PreRequisites.
1. JAVA version >= 1.7 required.
2. Gradle must be installed and set the GRADLE_HOME
   https://gradle.org/docs/current/userguide/installation.html

## How to RUN.
 get into the root directory (To run below command you must be connected to internet.)

 ./gradlew clean test
   --To run the test cases.

 ./gradlew clean build
   --To prepare the runnable jar. This task also executes the Test cases under test package.

 For eclipse lovers.

 ./gradlew eclipse
   --Creates the .project and .classpath files, so that you can import to eclipse.

##How to run from IDE.
 Open the file <code>SampleAppTest.java</code> and Right click run as JUnit application.

## The one who dont know gradle.
 1. Download the jars defined in build.gradle files dependencies {} section and set those jars in project class path.
 2. Remove the build.gradle file and files related to gradle and Run the <code>SampleAppTest.java</code> as JUnit application.
