#!/bin/bash
# nie musimy sie za bardzo martwic o poprawnosc plikow, bo sami je ukladamy
# shellcheck disable=SC2044
for f in $(find . -name '*.java'); do
  name=$(basename "$f" .java)
  if [ "$name" != "Instrukcja" ]; then
    printf "if (nazwa.equals(new %s().typ()))\nreturn new %s();\n" "$name" "$name"
  fi
done
