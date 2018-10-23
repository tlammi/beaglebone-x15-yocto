#!/bin/bash

set -e


# Make sure that we are in correct directory
cd "$(dirname $0)"
DEPLOY_PATH="poky/build/tmp/deploy/images/am57xx-evm/"

MLO_IMAGE="MLO"
UBOOT_IMAGE="u-boot.img"
KERNEL_IMAGE="zImage"
FDT_IMAGE="zImage-am57xx-beagle-x15-revc.dtb"
ROOTFS_IMAGE="core-image-minimal-am57xx-evm.tar.xz"

SD_CARD_PATH="/media/toni/"
SD_CARD_BOOT_DIR="BOOT/"
SD_CARD_ROOTFS_DIR="rootfs/"


cp -L ${DEPLOY_PATH}${MLO_IMAGE}  ${SD_CARD_PATH}${SD_CARD_BOOT_DIR}
cp -L ${DEPLOY_PATH}${UBOOT_IMAGE}  ${SD_CARD_PATH}${SD_CARD_BOOT_DIR}
cp -L ${DEPLOY_PATH}${KERNEL_IMAGE}  ${SD_CARD_PATH}${SD_CARD_BOOT_DIR}
cp -L ${DEPLOY_PATH}${FDT_IMAGE}  ${SD_CARD_PATH}${SD_CARD_BOOT_DIR}

rm -rf ${SD_CARD_PATH}${SD_CARD_ROOTFS_DIR}*
tar -xhJvf ${DEPLOY_PATH}${ROOTFS_IMAGE} -C ${SD_CARD_PATH}${SD_CARD_ROOTFS_DIR}

