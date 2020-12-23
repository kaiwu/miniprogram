#!/bin/bash
find target/ -name classes | xargs rm -rf
find target/ -name streams | xargs rm -rf
find target/ -name *.map | xargs rm -rf
rm -rf target/global-logging
rm -rf target/task-temp-directory
rm -f target/.history*
rm -rf target/scala-*
