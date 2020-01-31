#!/usr/bin/env bash

echo "JAVA_HOME= ${JAVA_HOME}"
echo "ANDROID_SDK_HOME= ${ANDROID_SDK_HOME}"
echo "ANDROID_HOME= ${ANDROID_HOME}"
echo "ANDROID_NDK_HOME= ${ANDROID_NDK_HOME}"

unset ANDROID_NDK_HOME
echo "Gradle version:"
gradlew assembleRelease