#!env bash
#
# Correct the copyright declarations
#
# © Copyright 2022 FreeLancer Development Team
# Licensed under the GPL v3+
#
# Written by J. Marrero

echo "-- correcting copyright notice on .java files"

# Do all of this in a subshell
(
cd ..

JAVA_FILES="$(find -type f -name '*.java')"

for file in $JAVA_FILES;
do
	sed -i 's/Copyright (C) 2022 Javier Marrero/Copyright (C) 2022 CUJAE/' $file
done

)

echo "-- done, success!"