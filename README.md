# DumpDemoForHeliumBalloon

This is a Demo-Plugin for HeliumBalloon to demonstrate how you can create and use dumps in HeliumBalloon by using the "balloon dumpconfig" function.

Install the HeliumBalloon plugin from https://www.spigotmc.org/resources/heliumballoon.95902

To learn how to use dumps, do the following:
1. Take care that no other plugin using the HeliumBalloon API exists on your server.
2. Start you minecraft server with default HeliumBalloon configuration.
3. Enter "balloon dumpconfig" on the console.
4. Copy the output and append it to this (!) file (the text starts with "HeliumBalloon: {", and ends wit "}").
5. Goto HeliumBalloon config and set "loadLocalConfig" to false.
6. Restart your minecraft server.

As a result you can see:
A. Because of "localLocalConfig" the default configuration is not loaded.
B. Because you have copied the dump into this file, the configuration gets loaded from here.
C. Finally you will have the same configuration as before, but loaded from a different place.

To rollback this demo, do the folowing:
1. Remove this plugin from your minecraft server.
2. Switch "loadLocalConfig" back to "true" in your HeliumBalloon config.
