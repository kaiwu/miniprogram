#!/bin/bash
rm -rf target/global-logging
rm -rf target/task-temp-directory
rm -rf target/.history*
rm -rf target/scala-*
rm -rf target/style
find target/ -name classes | xargs rm -rf
find target/ -name streams | xargs rm -rf
find target/ -name *.map | xargs rm -rf
find target/ -type f -name '*.min.css' -exec rename 's/\.min\.css/\.wxss/' '{}' \;
