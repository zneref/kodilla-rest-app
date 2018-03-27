#!/usr/bin/env bash

export CATALINA_HOME=/opt/tomcat

stop_tomcat()
{
  sudo $CATALINA_HOME/bin/catalina.sh stop
}

start_tomcat()
{
  sudo $CATALINA_HOME/bin/catalina.sh start
  end
}

rename() {
  rm ./build/libs/crud.war
  if mv ./build/libs/tasks-0.0.1-SNAPSHOT.war ./build/libs/crud.war; then
     echo "Successfully renamed file"
  else
     echo "Cannot rename file"
     fail
  fi
}

copy_file() {
  if cp ./build/libs/crud.war $CATALINA_HOME/webapps; then
     start_tomcat
  else
     fail
  fi
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if gradle build; then
   rename
   copy_file
else
   stop_tomcat
   fail
fi