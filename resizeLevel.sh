#!/bin/sh

LEVEL_NAME="$1.tmx"
TSX_NAME="$1.tsx"
PNG_NAME="$1.png"
DIR=$2

echo "Generating levels"

if [ ! -d "$DIR/mdpi" ]; then
    mkdir -p $DIR/mdpi
fi

if [ ! -d "$DIR/tv" ]; then
    mkdir -p $DIR/tv
fi

sed -e 's/tilewidth="[0-9]*"/tilewidth="16"/g' \
    -e 's/tileheight="[0-9]*"/tileheight="16"/g' \
    -e "s/source=\"[A-Za-z0-9\/]*.tsx\"/source=\"mdpi\/level\/$TSX_NAME\"/g" \
    $LEVEL_NAME > $DIR/mdpi/$LEVEL_NAME

echo "  Generated $DIR/mdpi/$LEVEL_NAME"

sed -e 's/tilewidth="[0-9]*"/tilewidth="16"/g' \
    -e 's/tileheight="[0-9]*"/tileheight="16"/g' \
    -e 's/height="[0-9]*"/height="160"/g' \
    -e 's/width="[0-9]*"/width="160"/g' \
    -e "s/source=\"[A-Za-z0-9\/]*.png\"/source=\"mdpi\/level\/$PNG_NAME\"/g" \
    $TSX_NAME > $DIR/mdpi/$TSX_NAME

echo "  Generated $DIR/mdpi/$TSX_NAME"

sed -e 's/tilewidth="[0-9]*"/tilewidth="24"/g' \
    -e 's/tileheight="[0-9]*"/tileheight="24"/g' \
    -e "s/source=\"[A-Za-z0-9\/]*.tsx\"/source=\"tv\/level\/$TSX_NAME\"/g" \
    $LEVEL_NAME > $DIR/tv/$LEVEL_NAME

echo "  Generated $DIR/tv/$LEVEL_NAME"

sed -e 's/tilewidth="[0-9]*"/tilewidth="24"/g' \
    -e 's/tileheight="[0-9]*"/tileheight="24"/g' \
    -e 's/height="[0-9]*"/height="240"/g' \
    -e 's/width="[0-9]*"/width="240"/g' \
    -e "s/source=\"[A-Za-z0-9\/]*.png\"/source=\"tv\/level\/$PNG_NAME\"/g" \
    $TSX_NAME > $DIR/tv/$TSX_NAME

echo "  Generated $DIR/tv/$TSX_NAME"
