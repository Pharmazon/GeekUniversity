FROM tomcat:9-jdk8

MAINTAINER Alexey Shcheglov <as.shcheglov@gmail.com>

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

#RUN apt-get update && apt-get -y upgrade && apt-get -y install nano && apt-get -y install sudo

COPY context.xml /conf/context.xml
COPY tomcat-users.xml /conf/tomcat-users.xml
COPY ./artifact/myapp.war /webapps/myapp.war

EXPOSE 8080

CMD ["catalina.sh", "run"]