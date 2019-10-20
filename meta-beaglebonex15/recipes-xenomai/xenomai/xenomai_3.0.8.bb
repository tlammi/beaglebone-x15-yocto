DESCRIPTION = "Xenomai user-space"
LICENSE = "GPLv2"
SECTION = "xenomai"
HOMEPAGE = "http://xenomai.org"
PR = "r0"
SRC_URI="git://git.xenomai.org/xenomai.git;branch=stable/v3.0.x;tag=fbc3271096c63b21fe895c66ba20b1d10d72ff48;destsuffix=xenomai-${PV}"

LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"

S = "${WORKDIR}/xenomai-${PV}"

inherit autotools pkgconfig

includedir = "/usr/include/xenomai"


PACKAGES += "${PN}-demos"

FILES_${PN}-demos = "/usr/demo"
FILES_${PN}-dev += "/dev"
FILES_${PN} += "\
	/usr/lib/modechk.wrappers \
	/usr/lib/cobalt.wrappers \
	/usr/lib/dynlist.ld \
"

EXTRA_OECONF += "--enable-smp --with-core=cobalt"

do_install_append(){
	sed -i 's~${WORKDIR}/recipe-sysroot~\${SDKTARGETSYSROOT}~g' ${D}/usr/bin/xeno-config
}

