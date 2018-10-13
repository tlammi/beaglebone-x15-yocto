# Initializes the build
TOP_DIR=$(pwd)
git submodule update --recursive
cd poky
source oe-init-build-env
cd ${TOP_DIR}
ln -fs ../../../bblayers.conf poky/build/conf/bblayers.conf
ln -fs ../../../local.conf poky/build/conf/local.conf
