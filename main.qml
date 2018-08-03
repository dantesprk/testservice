import QtQuick 2.11
import QtQuick.Controls 2.2
import QtQuick.Layouts 1.3

ApplicationWindow {
    visible: true
    width: 640
    height: 480
    title: qsTr("Hello World")

	MouseArea{
		anchors.fill: parent
		onClicked: Qt.quit()
	}
}
