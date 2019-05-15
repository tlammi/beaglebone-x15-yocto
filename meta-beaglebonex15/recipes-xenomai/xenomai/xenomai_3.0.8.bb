DESCRIPTION = "Xenomai user-space"
LICENSE = "GPLv2"
SECTION = "xenomai"
HOMEPAGE = "http://xenomai.org"
PR = "r0"


DEPENDS = "virtual/kernel"

#S = "${TMPDIR}/work-shared/${MACHINE}/xenomai"
XENOMAI_SOURCE = "${TMPDIR}/work-shared/${MACHINE}/xenomai"

B = "${WORKDIR}/xenomai_build"

includedir = "/usr/include/xenomai"

#Fixes QA Issues: non debug package contains .debug directory
FILES_${PN}-dbg += "/usr/xenomai/bin/regression/posix/.debug"
FILES_${PN}-dbg += "/usr/xenomai/bin/regression/native/.debug"
FILES_${PN}-dbg += "/usr/xenomai/bin/regression/native+posix/.debug"
FILES_${PN}-dbg += "/usr/xenomai/demo/.debug/*"

# Fixes QA Error - Non -dev package contains symlink .so
FILES_${PN}-dev += "/usr/lib/*.so"

#Add directories to package for shipping
FILES_${PN} += "/dev"
FILES_${PN} += "/usr/xenomai/bin/*"
FILES_${PN} += "/usr/xenomai/lib/*.so.*"
FILES_${PN} += "/usr/xenomai/lib/xenomai/*.o"
FILES_${PN} += "/usr/xenomai/sbin/*"
FILES_${PN} += "/usr/xenomai/include/*"
FILES_${PN} += "/usr/xenomai/demo/*"
FILES_${PN} += "/usr/xenomai/etc/*"

#INSANE_SKIP_${PN} += "ldflags"

do_configure_prepend() {
	#cd ${XENOMAI_SOURCE}

	#./scripts/bootstrap
}

do_configure() {

	mkdir -p ${WORKDIR}/xenomai_config

	cd ${WORKDIR}/xenomai_config
	
	mkdir -p ${B}

	${XENOMAI_SOURCE}/configure --with-core=cobalt --enable-smp  --host=arm-poky-linux-gnueabi

}

do_compile() {
	cd ${WORKDIR}/xenomai_config
	make DESTDIR="${B}" install
}

do_install() {
	
	mkdir -p ${D}/dev
	mkdir -p ${D}/usr/xenomai/bin
	mkdir -p ${D}/usr/xenomai/lib
	mkdir -p ${D}/usr/xenomai/sbin
	mkdir -p ${D}/usr/xenomai/include
	mkdir -p ${D}/usr/xenomai/demo
	mkdir -p ${D}/usr/xenomai/etc
	mkdir -p ${D}/usr/xenomai/lib/xenomai/

	cp -R ${B}/dev ${D}/dev
	cp -R ${B}/usr/xenomai/bin/* ${D}/usr/xenomai/bin/
	cp -R ${B}/usr/xenomai/lib/*.so.* ${D}/usr/xenomai/lib/
	cp -R ${B}/usr/xenomai/sbin/* ${D}/usr/xenomai/sbin/
	cp -R ${B}/usr/xenomai/include/* ${D}/usr/xenomai/include/
	cp -R ${B}/usr/xenomai/demo/* ${D}/usr/xenomai/demo/
	cp -R ${B}/usr/xenomai/etc/* ${D}/usr/xenomai/etc/
	cp -R ${B}/usr/xenomai/lib/xenomai/*.o ${D}/usr/xenomai/lib/
}


#addtask do_bootstrap before do_configure
