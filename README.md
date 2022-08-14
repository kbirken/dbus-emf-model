Welcome to dbus-emf-model!
==========================

This Eclipse plugin project provides an EMF ecore model for the D-Bus Introspection format
(see http://dbus.freedesktop.org for more details on D-Bus).

NOTE: This project has moved to github.com/kbirken, because the original location
(EclipseLabs / GoogleCode) has been closed in late 2015.

[![Build](https://github.com/kbirken/dbus-emf-model/actions/workflows/build.yml/badge.svg)](https://github.com/kbirken/dbus-emf-model/actions/workflows/build.yml)

## Installation

There is an update site as part of the git repository. Here is the URL (note that this will only serve as P2 repository, it will not work in a browser):

http://kbirken.github.io/dbus-emf-model/releases/

Installation is performed by the following steps:

1. Download _Eclipse Modeling Tools_ distribution for [_Eclipse 2020-09_](https://www.eclipse.org/downloads/packages/release/2020-09/r) and install it. Alternatively, use the [Eclipse Installer](https://www.eclipse.org/downloads/packages/installer).
  - dbus-emf-model requires the Eclipse Modeling Framework (EMF), which is preinstalled in this Eclipse distribution.
  - dbus-emf-model is using Java 8 (but will also work with later Java versions). Please ensure that Eclipse is running with a JDK 8 installation.
2. Start _eclipse.exe_ and create a new workspace.
3. Choose _Help > Install New Software ..._ and add a new update site with the above URL.
4. Install the D-Bus EMF Model feature.
5. Ready!


## Uses of dbus-emf-model

dbus-emf-model is used by the Franca IDL transformation framework, which is also hosted [on github](https://github.com/franca/franca). It is used by the Franca/DBus-connector in order to transform any Franca-related IDL to D-Bus Introspection XML files.


## License

Eclipse Public License 1.0 (EPL 1.0), see license file _epl-v10.html_ enclosed.
