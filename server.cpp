#include <QFile>
#include <QTimer>
#include <QDateTime>
#include <QTextStream>
#include <QStandardPaths>
#include <QAndroidService>

int main(int argc, char *argv[])
{
    QAndroidService app(argc, argv);
	QTimer t;

	QObject::connect(&t, &QTimer::timeout, []
	{
		QFile infoFile(QStandardPaths::writableLocation(QStandardPaths::DownloadLocation) + QString("/tmp.txt"));

		if (infoFile.open(QIODevice::WriteOnly | QIODevice::Append))
		{
			QTextStream out(&infoFile);
			out << QDateTime::currentDateTime().toString() << "\n";
		}
	});

	t.start(10000);

    return app.exec();
}
