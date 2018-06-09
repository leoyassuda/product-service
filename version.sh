#!/bin/bash
export OLD_SVN_VERSIONS=$((0))
export GIT_VERSION="$(git rev-list HEAD | wc -l)"
export VERSION=$(($GIT_VERSION + $OLD_SVN_VERSIONS))
echo $VERSION