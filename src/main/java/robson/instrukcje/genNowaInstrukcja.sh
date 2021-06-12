#!/bin/bash
# nie musimy się za bardzo martwić o poprawność plików, bo sami je układamy
# shellcheck disable=SC2044
for f in $(find . -name '*.java'); do
  name=$(basename "$f" .java)
  if [ "$name" != "Instrukcja" ]; then
    printf "if (nazwa.equals(new %s().typ()))\nreturn new %s();\n" "$name" "$name"
  fi
done