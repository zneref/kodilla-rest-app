#!/usr/bin/env bash

run_crud() {
if ./runcrud.sh; then
lynx http://127.0.0.1:8080/crud/v1/task/getTasks;
else
echo "something gone wrong";
fi
}

run_crud