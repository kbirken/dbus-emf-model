Welcome to dbus-emf-model!
==========================

This Eclipse plugin project provides an EMF ecore model for the D-Bus Introspection format
(see http://dbus.freedesktop.org for more details on D-Bus).

NOTE: This project has moved to github.com/kbirken, because the original location
(EclipseLabs / GoogleCode) has been closed in late 2015.


## Installation

There is an update site as part of the git repository. Here is the URL:

http://kbirken.github.io/dbus-emf-model/releases/

Installation is performed by the following steps:

1. Download _Eclipse Modeling Tools_ distribution for _Eclipse Indigo_ from [http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/indigor here] and install it.
  - dbus-emf-model requires the Eclipse Modeling Framework (EMF), which is preinstalled in this Eclipse distribution.
  - dbus-emf-model is using Java 7 (but will also work with later Java versions). Please ensure that Eclipse is running with a JDK 7 or JDK 8 installation.
2. Start _eclipse.exe_ and create a new workspace.
3. Choose _Help > Install New Software ..._ and add a new update site with the above URL.
4. Install the D-Bus EMF Model feature.
5. Ready!


## Uses of dbus-emf-model

dbus-emf-model is used by the Franca IDL transformation framework, which is currently hosted on Eclipse Labs: http://code.google.com/a/eclipselabs.org/p/franca/. It is used by the Franca/DBus-connector in order to transform any Franca-related IDL to D-Bus Introspection XML files.


## License

Eclipse Public License 1.0 (EPL 1.0), see license file _epl-v10.html_ enclosed.
