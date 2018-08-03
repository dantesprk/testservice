#include <QQmlContext>
#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include <QtAndroid>
#include <QAndroidJniObject>
#include <QAndroidJniEnvironment>

int main(int argc, char *argv[])
{
	QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
	QGuiApplication app(argc, argv);

	QQmlApplicationEngine engine;
	engine.load(QUrl(QLatin1String("qrc:/main.qml")));

	QtAndroid::PermissionResult result = QtAndroid::checkPermission("android.permission.WRITE_EXTERNAL_STORAGE");

	if (result == QtAndroid::PermissionResult::Denied)
	{
		QtAndroid::requestPermissionsSync(QStringList() << "android.permission.WRITE_EXTERNAL_STORAGE" );

		result = QtAndroid::checkPermission("android.permission.WRITE_EXTERNAL_STORAGE");

		//if (result == QtAndroid::PermissionResult::Denied)
			//qWarning() << tr("WRITE_EXTERNAL_STORAGE");
	}

	QAndroidJniObject::callStaticMethod<void>("com/kdab/training/MyService",
											  "startMyService",
											  "(Landroid/content/Context;)V",
											  QtAndroid::androidActivity().object());

	return app.exec();
}
