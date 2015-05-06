# Welcome to dbus-emf-model! #

This Eclipse plugin project provides an EMF ecore model for the D-Bus Introspection format (see http://dbus.freedesktop.org for more details on D-Bus).

# Installation #

There is an update site as part of the git repository. Here is the URL:

http://dbus-emf-model.eclipselabs.org.codespot.com/git/update_site/releases/

Installation is performed by the following steps:
  1. Download _Eclipse Modeling Tools_ distribution for _Eclipse Indigo_ from [here](http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/indigor) and install it.
    * dbus-emf-model requires the Eclipse Modeling Framework (EMF), which is preinstalled in this Eclipse distribution.
    * dbus-emf-model is using Java 6. Please ensure that Eclipse is running with a JDK1.6.x installation.
  1. Start _eclipse.exe_ and create a new workspace.
  1. Choose _Help > Install New Software ..._ and add a new update site with the above URL.
  1. Install the D-Bus EMF Model feature.
  1. Ready!


# Uses of dbus-emf-model #

dbus-emf-model is used by the Franca IDL transformation framework, which is also hosted on Eclipse Labs: http://code.google.com/a/eclipselabs.org/p/franca/. It is used by the Franca/DBus-connector in order to transform any Franca-related IDL to D-Bus Introspection XML files.
