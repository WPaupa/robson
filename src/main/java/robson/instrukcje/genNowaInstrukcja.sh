#!/bin/bash
# nie musimy siÄ™ za bardzo martwiÄ‡ o poprawnoĹ›Ä‡ plikĂłw, bo sami je ukĹ‚adamy
# shellcheck disable=SC2044
for f in $(find . -name '*.java'); do
  name=$(basename "$f" .java)
  if [ "$name" != "Instrukcja" ]; then
    printf "if (nazwa.equals(new %s().typ()))\nreturn new %s();\n" "$name" "$name"
  fi
done
