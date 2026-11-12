#!/usr/bin/env bash
# Experimental build: upstream PhotoCraft + Android adapter, not a public release.
set -euo pipefail
cd "$(dirname "$0")"
exec bash build-debug.sh -photocraft -appname=VISUSHOP -packagename=io.github.jean13png.visushop "$@"
