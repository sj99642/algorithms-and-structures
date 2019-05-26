cmake_minimum_required(VERSION 3.0.0)
project(CStructures VERSION 0.1.0)

include(CTest)
enable_testing()

add_library(CStructures SHARED CStructures.c src/LinkedList.c src/Queue.c src/Stack.c)
include_directories(headers)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
