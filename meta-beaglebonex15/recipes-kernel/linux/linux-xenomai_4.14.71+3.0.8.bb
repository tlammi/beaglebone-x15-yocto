
require recipes-kernel/linux/linux-yocto.inc

# board specific branches


LINUX_VERSION = "4.14.71"
SRC_URI = "git://git.xenomai.org/ipipe-arm.git;branch=stable/4.14.71-arm;tag=b7600fd089fdcbd5aae5385b42498259924ca2fb;destsuffix=linux"
SRC_URI += "git://git.xenomai.org/xenomai.git;branch=stable/v3.0.x;tag=fbc3271096c63b21fe895c66ba20b1d10d72ff48;destsuffix=xenomai"

SRC_URI += "file://defconfig"
SRC_URI += "file://00001_add_support_for_beaglboard.patch"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"


S = "${WORKDIR}/linux"
KCONF_BSP_AUDIT_LEVEL = "2"

KERNEL_DEVICETREE_qemuarm = "versatile-pb.dtb"


KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
COMPATIBLE_MACHINE = "am57xx-evm"

# Functionality flags
#KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
#KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
#KERNEL_FEATURES_append_qemuall=" cfg/virtio.scc"
#KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
#KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
#KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"


do_prepare_kernel(){

	linux_src="${S}"

	${WORKDIR}/xenomai/scripts/prepare-kernel.sh --arch=arm --linux=${linux_src}
}

addtask do_prepare_kernel after do_patch before do_configure

