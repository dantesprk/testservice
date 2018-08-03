QT += qml quick androidextras
CONFIG += c++11

SOURCES += client.cpp
RESOURCES += qml.qrc

QML_IMPORT_PATH =

DISTFILES += \
    android/AndroidManifest.xml \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradlew \
    android/res/values/libs.xml \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew.bat \
    android/src/com/kdab/training/MyService.java

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android
