SUMMARY = "A hw-accelerated, touch only UI for Venus devices."

include gui-v2.inc

inherit daemontools qt6-cmake start-gui ve_package

DEPENDS += "qtdeclarative-native qttools-native"
DEPENDS += "qtbase qtdeclarative qtmqtt qtsvg"
RDEPENDS:${PN} = "qt5compat qtvirtualkeyboard"

PACKAGES += "start-gui-v2"
DAEMON_PN = "start-gui-v2"
RDEPENDS_${DAEMON_PN} = "${PN}"

DAEMONTOOLS_SCRIPT = ". /etc/profile.d/qt6.sh && exec softlimit -d 768000000 -s 1000000 -a 768000000 ${bindir}/venus-gui-v2"

SRC_URI = " \
	gitsm://github.com/victronenergy/gui-v2.git;branch=main;protocol=ssh;user=git;tag=v${PV} \
    file://0001-fix-building-with-OpenEmbedded-Debian-rules.patch \
"
S = "${WORKDIR}/git"
EXTRA_OECMAKE = "-DNO_CACHEGEN=true -DLOAD_QML_FROM_FILESYSTEM=true"

