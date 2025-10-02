#!/bin/bash
set -e

HOSTS_FILE="./hosts_ads_block"

cat > $HOSTS_FILE <<EOL
127.0.0.1 localhost

# Google Ads / AdMob
127.0.0.1 pagead2.googlesyndication.com
127.0.0.1 googleads.g.doubleclick.net
127.0.0.1 ads.google.com
127.0.0.1 adservice.google.com
127.0.0.1 adservice.google.ru

# Tracking / Firebase Ads
127.0.0.1 app-measurement.com
127.0.0.1 adservice.googleusercontent.com
127.0.0.1 g.doubleclick.net

# Unity Ads
127.0.0.1 unityads.unity3d.com
127.0.0.1 adserver.unityads.unity3d.com

# AppLovin
127.0.0.1 applovin.com
127.0.0.1 d.applovin.com
127.0.0.1 r.applovin.com

# IronSource
127.0.0.1 track.atom-data.io
127.0.0.1 analytics-ironsource.com
127.0.0.1 mediation-ironsource.com

# AdColony
127.0.0.1 adcolony.com
127.0.0.1 adc3-launch.adcolony.com

# InMobi
127.0.0.1 inmobi.com
127.0.0.1 ads.inmobi.com

# MoPub
127.0.0.1 ads.mopub.com
127.0.0.1 analytics.mopub.com

# Chartboost
127.0.0.1 chartboost.com
127.0.0.1 live.chartboost.com

# Vungle
127.0.0.1 api.vungle.com
127.0.0.1 ads.api.vungle.com
EOL

adb root || true
adb remount || true

adb push $HOSTS_FILE /system/etc/hosts
adb shell chmod 644 /system/etc/hosts

adb reboot
