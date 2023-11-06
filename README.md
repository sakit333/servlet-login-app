# servlet-login-app

Create 3 instances
db -> for RDS connection
tomacat -> install java, tomcat and configure tomcat
jenkins -> install java, git, jenkins

for all these instances and RDS database give all data traffic for both inbound and outbound rules 
instance security group(rds-ec2-n) --> allow all traffic
-------------------------------------------------------------------------------------------------------------

In tomcat config these:

vi apache-tomcat-9.0.80/conf/tomcat-users.xml

<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<role rolename="manager-jmx"/>
<role rolename="manager-status"/>
<user username="admin" password="admin" roles="manager-gui, manager-script, manager-jmx, manager-status"/>

vi apache-tomcat-9.0.80/webapps/manager/META-INF/context.xml
(comment the tag (Cookie Processor till allow tag complete))

----------------------------------------------------------------------------------------------------------
RDS:
db instance and RDS database give all data traffic for both inbound and outbound rules
Create RDS with -> simple database -> select mysql -> free tier
username: admin
password: Adminroot
connect to ec2 instance -> db
-> create database

connect db instance add follow commands:

1) sudo vi /etc/yum.repos.d/MariaDB.repo

[mariadb]
name = MariaDB
baseurl = http://yum.mariadb.org/10.5/centos7-amd64
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
gpgcheck=1

2) sudo yum install MariaDB-server MariaDB-client

3) sudo systemctl start mariadb
   sudo systemctl enable mariadb

4) sudo mysql_secure_installation

5) mysql -h endpoint -P 3306 -u admin -p

6) <to go inside database use sql command> use database;

7) <to show all tables> show tables;

8) <after deployment of project and adding data to get the data use sql query> select * from User;
-----------------------------------------------------------------------------------------------------------
In jenikins-> 

install plugin -> deployment to container
tools -> add maven
job-> 
git repo address
build steps -> invoke top level maven target -> maven -> goals= clean install
post build action -> deploy war/ear to a container -> war/ear files = **/*.war -> context-path = artifact id -> add container -> add credentials => user name= admin, password = admin, description = tomcat -> http://<public-ipv4>:8080/

Build job
