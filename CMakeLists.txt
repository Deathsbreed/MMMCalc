cmake_minimum_required(VERSION 2.6)
project(MMMCalc)

if(CMAKE_BUILD_TYPE STREQUAL "")
    set(CMAKE_BUILD_TYPE RelWithDebInfo)
endif()

set(CMAKE_C_FLAGS "-std=gnu99 -Wall -Wextra -Werror -Wfatal-errors -Wmissing-declarations -pedantic-errors")
set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS} -g -O0")
set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS} -O3")
set(CMAKE_C_FLAGS_RELWITHDEBINFO "${CMAKE_C_FLAGS} -g -O3")
set(CMAKE_C_FLAGS_MIN_SIZEREL "${CMAKE_C_FLAGS} -Os")

set(SRCS
    src/main.c)

add_executable(mmmcalc ${SRCS})
