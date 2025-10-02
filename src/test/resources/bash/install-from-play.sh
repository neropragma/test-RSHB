#!/bin/bash
set -e

GP_EMAIL="testing.testov1991@gmail.com"
GP_PASS="tvbvcsqgqvxrmisd"

APP_PACKAGE="vpn.fast.unlimited.free"
GP_CLI_CONF="$HOME/.config/gplaycli/gplaycli.conf"
CACHE_DIR="$HOME/.cache/gplaycli"
EMULATOR_ID=$(adb devices | grep emulator | awk '{print $1}' | head -n1)
BUNDLETOOL_JAR="./src/test/resources/jar/bundletool-all.jar"
DOWNLOAD_DIR="./downloads"

mkdir -p "$DOWNLOAD_DIR"
mkdir -p "$(dirname $GP_CLI_CONF)"

cat > "$GP_CLI_CONF" <<EOL
[Credentials]
gmail_address = $GP_EMAIL
gmail_password = $GP_PASS
EOL


mkdir -p "$HOME"/.gplaycli
gplaycli -v -c "$GP_CLI_CONF" || true

gplaycli -v -d "$APP_PACKAGE" -f "$DOWNLOAD_DIR" --config "$GP_CLI_CONF"
APK_FILE=$(ls -t "$DOWNLOAD_DIR"/* | head -n1)

EXT="${APK_FILE##*.}"

if [[ "$EXT" == "apk" ]]; then
    adb -s "$EMULATOR_ID" install -r "$APK_FILE"
elif [[ "$EXT" == "apks" ]]; then
    if [ ! -f "$BUNDLETOOL_JAR" ]; then
        exit 1
    fi
    java -jar "$BUNDLETOOL_JAR" install-apks --apks="$APK_FILE"
elif [[ "$EXT" == "xapk" ]]; then
    TMP_DIR=$(mktemp -d)
    unzip "$APK_FILE" -d "$TMP_DIR"

    BASE_APK=$(find "$TMP_DIR" -name "$PACKAGE_NAME*.apk" | head -n1)
    if [ -z "$BASE_APK" ]; then
        echo "$PACKAGE_NAME.apk не найден в XAPK"
        exit 1
    fi
    adb -s "$EMULATOR_ID" install -r "$BASE_APK"

    SPLIT_APKS=$(find "$TMP_DIR" -name "*.apk" ! -name "$PACKAGE_NAME*.apk")
    if [ -n "$SPLIT_APKS" ]; then
        adb -s "$EMULATOR_ID" install-multiple -r $SPLIT_APKS
    fi
else
    exit 1
fi