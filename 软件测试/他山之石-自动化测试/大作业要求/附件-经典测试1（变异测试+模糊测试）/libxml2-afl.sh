#!/bin/bash

# Configure location globals
export BENCH_DIR="/root/qrx/fun-sig-fuzz/bench"
export SUBJECT="$BENCH_DIR/libxml2-afl"
# Configure compilation globals
export CC="$AFL/afl-gcc"
export CXX="$AFL/afl-g++"
# Show configuration results
echo "SUBJECT=$SUBJECT"
echo "CC=$CC"
echo "CXX=$CXX"


# Parameter checking
if [ $# -lt 2 ]; then
  echo "<CAMPAIGN>: <START_IDX> <REPEAT>"
  exit 1
fi
# Configure how many times to run and where (index) to start
START_IDX=$1
REPEAT=$2
# Compute where to stop
END_IDX=$((START_IDX+REPEAT-1))

# Prepare initial seeds for fuzzing
IN_DIR="$SUBJECT/in"
if [ -d $IN_DIR ]; then
  rm -rf $IN_DIR
fi
mkdir $IN_DIR
cp $SUBJECT/test/*.xml $IN_DIR; rm $(find $IN_DIR -size +100c)

# Instrument subject programs
pushd $SUBJECT || exit 1
  ./autogen.sh
  ./configure --disable-shared
  make clean
  make xmllint
popd || exit 1

# Start fuzzing campaign.
STARTUP_DUR=10
DUR=$((24*60*60))
TIMEOUT="$((STARTUP_DUR+DUR))"s
for idx in $(seq "$START_IDX" "$END_IDX"); do

  # Prepare out directories for fuzzing
  OUT_DIR="$SUBJECT/outs/out-$idx"
  if [ -d "$OUT_DIR" ]; then
    rm -rf "$OUT_DIR"
  fi
  mkdir -p "$OUT_DIR"

  # Run fuzzing
  timeout $TIMEOUT "$AFL"/afl-fuzz -i "$IN_DIR" -o "$OUT_DIR" $SUBJECT/xmllint --valid --recover @@

done
