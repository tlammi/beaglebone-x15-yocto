DESCRIPTION = "Linux/Xenomai kernel for BeagleBone X15"
SECTION = "kernel"
LICENSE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

# This will probably cause an error
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

LINUX_VERSION = "4.14.71"

LINUX_VERSION_EXTENSION = "-xeno"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRCREV = "670926d1fbc669719ae53d8b170a2fc425593720"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git"

