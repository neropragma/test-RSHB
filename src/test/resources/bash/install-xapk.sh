#!/bin/bash
set -e

PACKAGE_NAME="vpn.fast.unlimited.free"
APK_FILE="./src/test/resources/apps/vpn.xapk"
EMULATOR_ID=$(adb devices | grep emulator | awk '{print $1}' | head -n1)

TMP_DIR=$(mktemp -d)
unzip "$APK_FILE" -d "$TMP_DIR"

BASE_APK=$(find "$TMP_DIR" -name "$PACKAGE_NAME*.apk" | head -n1)
if [ -z "$BASE_APK" ]; then
    exit 1
fi
adb -s "$EMULATOR_ID" install -r "$BASE_APK"

SPLIT_APKS=$(find "$TMP_DIR" -name "*.apk" ! -name "$PACKAGE_NAME*.apk")
if [ -n "$SPLIT_APKS" ]; then
    adb -s "$EMULATOR_ID" install-multiple -r $SPLIT_APKS
fi
