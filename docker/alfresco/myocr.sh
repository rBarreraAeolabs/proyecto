#!/usr/bin/env bash
#set -o xtrace # Uncomment for debugging/troubleshooting

SOURCE=$1
TARGET=$2
URL='http://ocr:8080/'

curl -s -S -X POST -H "Content-Type: multipart/form-data" -F "archivo=@${SOURCE}" "${URL}" -o "${TARGET}"