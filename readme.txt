This is a sample poc web application built for recruitment process purpose.

to start this app using tomcat(I used tomcat 8), point the war folder under target directory 
to tomcat server modules as an external web module. 
Open servers view -> double click on tomcat server icon ->
 select modules tab (besides Overview tab) ->click on Add external web module
 -> path: /<app name to access (it can be any name)>
   Document Base: point to application war folder(home directory) under target folder
  -> start server in Run or debug mode  
  
--  Configured spring task scheduler for getInterviewEvaluationsFromQueueAndSave() of IntervieweeEvaluationService.java
   in IntervieweeEvaluationConfiguration.java  for every 30 seconds. this cron job time 
   is configured in application.properties controlled by 
   IntervieweeEvaluationSchedulerController.java


Added a few flows to post and retrieve details like technologies,interviewee details etc

Used Java,Spring, Rest, Hibernate with maven technologies
used Jaxb for xml implementation
Junits are added with Mockito framework
Spring Junit Integration tests are added

used mysql db :
{ Used "recruitment" database for actual functionality
 Used "recruitmenttest" database for integration tests}
 
 database details :
 
 database : recruitment
table: jobprofile
create table jobprofile(id int, name varchar(20) NOT NULL, PRIMARY KEY(ID));

technologies
create table technologies(id int,name varchar(30),description varchar(200),jobprofileid int, primary key(id), foreign key(jobprofileid) references jobprofile(id));

interviewee
create table interviewee(id int,name varchar(20) not null,mblnum bigint,jprofileid int not null, primary key(id), check (mblnum>0))

certificate
mysql>create table certificate(id int,name varchar(25) not null, primary key(id));
mysql>alter table certificate add unique(name);

intervieweecertificate
mysql> create table intervieweecertificate(intervieweeid int, certificateid int, primary key(intervieweeid, certificateid));

rating
create table rating(name varchar(10),code int not null, primary key(name));
alter table rating add unique(code);

intervieweeevaluation
mysql> create table intervieweeevaluation(evalid int,evalname varchar(30),intervieweeid int,skillid int,techrating int, personalrating int,interviewtype varchar(30),interviewround int not null);
mysql> alter table intervieweeevaluation add primary key(evalid,intervieweeid,interviewround);
mysql> alter table intervieweeevaluation modify interviewtype varchar(30) not null;
mysql> alter table intervieweeevaluation modify evalname varchar(30) not null;
mysql> alter table intervieweeevaluation modify intervieweeid int not null;
mysql> alter table intervieweeevaluation modify skillid int not null;
mysql> alter table intervieweeevaluation modify techrating int not null;
mysql> alter table intervieweeevaluation modify personalrating int not null;

personaltraits
mysql> create table personaltraits(skillid int,name varchar(20));
mysql> alter table personaltraits add primary key(skillid);
mysql> alter table personaltraits add unique(name);
mysql> alter table personaltraits modify name varchar(20) not null;
 