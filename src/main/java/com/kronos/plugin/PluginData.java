/**
 * 
 */
package com.kronos.plugin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

import com.kronos.Kronos;
import com.kronos.io.ResourceIdentifier;
import com.kronos.io.config.ConfigFile;

/**
 * 
 */
public class PluginData {

	String name;
	static Plugin plugin;

	String[] pluginargs = new String[] { "-noargs" };
	public static Logger l = getKronosLogger();

	public PluginData(String name, Plugin plugin) {
		this.name = name;
		PluginData.plugin = plugin;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	public static void log(String str) {
		l.debug("Pluginmsg: {}", str);
	}

	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(Plugin plugin) {
		PluginData.plugin = plugin;
	}

	public static Logger getKronosLogger() {
		return LogManager.getLogger();
	}

	public static ResourceIdentifier getIdentifierOut() {
		return Kronos.kronos_out;
	}

	public static ResourceIdentifier getIdentifierRid() {
		return Kronos.kronos_rid;
	}

	public String[] getPluginargs() {
		return pluginargs;
	}

	/**
	 * @param pluginargs the pluginargs to set
	 */
	public void setPluginargs(String[] pluginargs) {
		this.pluginargs = pluginargs;
	}

	public static ConfigFile createConfig(String path, String name) {
		if (name.endsWith(".json")) {
			return new ConfigFile(path, name.replace(".json", ""), getIdentifierOut());
		} else {
			return new ConfigFile(path, name, getIdentifierOut());
		}
	}

	/**
	 * @param level
	 * @param t
	 * @see org.apache.logging.log4j.Logger#catching(org.apache.logging.log4j.Level,
	 *      java.lang.Throwable)
	 */
	public void catching(Level level, Throwable t) {
		l.catching(level, t);
	}

	/**
	 * @param t
	 * @see org.apache.logging.log4j.Logger#catching(java.lang.Throwable)
	 */
	public void catching(Throwable t) {
		l.catching(t);
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void debug(Marker marker, Message msg) {
		l.debug(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void debug(Marker marker, Message msg, Throwable t) {
		l.debug(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void debug(Marker marker, MessageSupplier msgSupplier) {
		l.debug(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void debug(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.debug(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void debug(Marker marker, CharSequence message) {
		l.debug(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void debug(Marker marker, CharSequence message, Throwable t) {
		l.debug(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void debug(Marker marker, Object message) {
		l.debug(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Marker marker, Object message, Throwable t) {
		l.debug(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void debug(Marker marker, String message) {
		l.debug(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void debug(Marker marker, String message, Object... params) {
		l.debug(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void debug(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.debug(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void debug(Marker marker, String message, Throwable t) {
		l.debug(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void debug(Marker marker, Supplier<?> msgSupplier) {
		l.debug(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void debug(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.debug(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.message.Message)
	 */
	public void debug(Message msg) {
		l.debug(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void debug(Message msg, Throwable t) {
		l.debug(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void debug(MessageSupplier msgSupplier) {
		l.debug(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void debug(MessageSupplier msgSupplier, Throwable t) {
		l.debug(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.CharSequence)
	 */
	public void debug(CharSequence message) {
		l.debug(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void debug(CharSequence message, Throwable t) {
		l.debug(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		l.debug(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void debug(Object message, Throwable t) {
		l.debug(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String)
	 */
	public static void debug(String message) {
		l.debug(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object[])
	 */
	public void debug(String message, Object... params) {
		l.debug(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void debug(String message, Supplier<?>... paramSuppliers) {
		l.debug(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void debug(String message, Throwable t) {
		l.debug(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.util.Supplier)
	 */
	public void debug(Supplier<?> msgSupplier) {
		l.debug(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void debug(Supplier<?> msgSupplier, Throwable t) {
		l.debug(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0) {
		l.debug(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1) {
		l.debug(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.debug(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.debug(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.debug(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.debug(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.debug(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#debug(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object)
	 */
	public static void debug(String message, Object p0) {
		l.debug(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1) {
		l.debug(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2) {
		l.debug(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3) {
		l.debug(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.debug(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.debug(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.debug(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#debug(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void error(Marker marker, Message msg) {
		l.error(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void error(Marker marker, Message msg, Throwable t) {
		l.error(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void error(Marker marker, MessageSupplier msgSupplier) {
		l.error(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void error(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.error(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void error(Marker marker, CharSequence message) {
		l.error(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void error(Marker marker, CharSequence message, Throwable t) {
		l.error(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void error(Marker marker, Object message) {
		l.error(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void error(Marker marker, Object message, Throwable t) {
		l.error(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void error(Marker marker, String message) {
		l.error(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void error(Marker marker, String message, Object... params) {
		l.error(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void error(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.error(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void error(Marker marker, String message, Throwable t) {
		l.error(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void error(Marker marker, Supplier<?> msgSupplier) {
		l.error(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void error(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.error(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.message.Message)
	 */
	public void error(Message msg) {
		l.error(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void error(Message msg, Throwable t) {
		l.error(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void error(MessageSupplier msgSupplier) {
		l.error(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void error(MessageSupplier msgSupplier, Throwable t) {
		l.error(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(java.lang.CharSequence)
	 */
	public void error(CharSequence message) {
		l.error(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void error(CharSequence message, Throwable t) {
		l.error(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(java.lang.Object)
	 */
	public void error(Object message) {
		l.error(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void error(Object message, Throwable t) {
		l.error(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String)
	 */
	public static void error(String message) {
		l.error(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object[])
	 */
	public void error(String message, Object... params) {
		l.error(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void error(String message, Supplier<?>... paramSuppliers) {
		l.error(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void error(String message, Throwable t) {
		l.error(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.util.Supplier)
	 */
	public void error(Supplier<?> msgSupplier) {
		l.error(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void error(Supplier<?> msgSupplier, Throwable t) {
		l.error(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0) {
		l.error(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1) {
		l.error(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.error(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.error(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.error(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.error(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.error(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#error(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object)
	 */
	public static void error(String message, Object p0) {
		l.error(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1) {
		l.error(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2) {
		l.error(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3) {
		l.error(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.error(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.error(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.error(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.error(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#error(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void fatal(Marker marker, Message msg) {
		l.fatal(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void fatal(Marker marker, Message msg, Throwable t) {
		l.fatal(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void fatal(Marker marker, MessageSupplier msgSupplier) {
		l.fatal(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void fatal(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.fatal(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void fatal(Marker marker, CharSequence message) {
		l.fatal(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void fatal(Marker marker, CharSequence message, Throwable t) {
		l.fatal(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void fatal(Marker marker, Object message) {
		l.fatal(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Marker marker, Object message, Throwable t) {
		l.fatal(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void fatal(Marker marker, String message) {
		l.fatal(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void fatal(Marker marker, String message, Object... params) {
		l.fatal(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void fatal(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.fatal(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void fatal(Marker marker, String message, Throwable t) {
		l.fatal(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void fatal(Marker marker, Supplier<?> msgSupplier) {
		l.fatal(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void fatal(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.fatal(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.message.Message)
	 */
	public void fatal(Message msg) {
		l.fatal(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void fatal(Message msg, Throwable t) {
		l.fatal(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void fatal(MessageSupplier msgSupplier) {
		l.fatal(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void fatal(MessageSupplier msgSupplier, Throwable t) {
		l.fatal(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.CharSequence)
	 */
	public void fatal(CharSequence message) {
		l.fatal(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void fatal(CharSequence message, Throwable t) {
		l.fatal(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
		l.fatal(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable t) {
		l.fatal(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String)
	 */
	public void fatal(String message) {
		l.fatal(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object[])
	 */
	public static void fatal(String message, Object... params) {
		l.fatal(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void fatal(String message, Supplier<?>... paramSuppliers) {
		l.fatal(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void fatal(String message, Throwable t) {
		l.fatal(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.util.Supplier)
	 */
	public void fatal(Supplier<?> msgSupplier) {
		l.fatal(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void fatal(Supplier<?> msgSupplier, Throwable t) {
		l.fatal(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0) {
		l.fatal(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1) {
		l.fatal(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.fatal(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.fatal(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.fatal(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.fatal(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.fatal(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.fatal(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.fatal(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#fatal(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.fatal(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object)
	 */
	public void fatal(String message, Object p0) {
		l.fatal(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1) {
		l.fatal(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2) {
		l.fatal(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3) {
		l.fatal(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.fatal(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.fatal(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.fatal(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.fatal(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.fatal(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#fatal(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.fatal(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#getLevel()
	 */
	public Level getLevel() {
		return l.getLevel();
	}

	/**
	 * @param <MF>
	 * @return
	 * @see org.apache.logging.log4j.Logger#getMessageFactory()
	 */
	public <MF extends MessageFactory> MF getMessageFactory() {
		return l.getMessageFactory();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#getFlowMessageFactory()
	 */
	public FlowMessageFactory getFlowMessageFactory() {
		return l.getFlowMessageFactory();
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void info(Marker marker, Message msg) {
		l.info(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void info(Marker marker, Message msg, Throwable t) {
		l.info(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void info(Marker marker, MessageSupplier msgSupplier) {
		l.info(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void info(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.info(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void info(Marker marker, CharSequence message) {
		l.info(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void info(Marker marker, CharSequence message, Throwable t) {
		l.info(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void info(Marker marker, Object message) {
		l.info(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void info(Marker marker, Object message, Throwable t) {
		l.info(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void info(Marker marker, String message) {
		l.info(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void info(Marker marker, String message, Object... params) {
		l.info(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void info(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.info(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void info(Marker marker, String message, Throwable t) {
		l.info(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void info(Marker marker, Supplier<?> msgSupplier) {
		l.info(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void info(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.info(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.message.Message)
	 */
	public void info(Message msg) {
		l.info(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void info(Message msg, Throwable t) {
		l.info(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void info(MessageSupplier msgSupplier) {
		l.info(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void info(MessageSupplier msgSupplier, Throwable t) {
		l.info(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(java.lang.CharSequence)
	 */
	public void info(CharSequence message) {
		l.info(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void info(CharSequence message, Throwable t) {
		l.info(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(java.lang.Object)
	 */
	public void info(Object message) {
		l.info(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void info(Object message, Throwable t) {
		l.info(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String)
	 */
	public void info(String message) {
		l.info(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String,
	 *      java.lang.Object[])
	 */
	public void info(String message, Object... params) {
		l.info(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void info(String message, Supplier<?>... paramSuppliers) {
		l.info(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void info(String message, Throwable t) {
		l.info(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.util.Supplier)
	 */
	public void info(Supplier<?> msgSupplier) {
		l.info(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void info(Supplier<?> msgSupplier, Throwable t) {
		l.info(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0) {
		l.info(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1) {
		l.info(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.info(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.info(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.info(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.info(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.info(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#info(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object)
	 */
	public void info(String message, Object p0) {
		l.info(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1) {
		l.info(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2) {
		l.info(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3) {
		l.info(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.info(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.info(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.info(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.info(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#info(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return l.isDebugEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isDebugEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isDebugEnabled(Marker marker) {
		return l.isDebugEnabled(marker);
	}

	/**
	 * @param level
	 * @return
	 * @see org.apache.logging.log4j.Logger#isEnabled(org.apache.logging.log4j.Level)
	 */
	public boolean isEnabled(Level level) {
		return l.isEnabled(level);
	}

	/**
	 * @param level
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isEnabled(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker)
	 */
	public boolean isEnabled(Level level, Marker marker) {
		return l.isEnabled(level, marker);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		return l.isErrorEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isErrorEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isErrorEnabled(Marker marker) {
		return l.isErrorEnabled(marker);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isFatalEnabled()
	 */
	public boolean isFatalEnabled() {
		return l.isFatalEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isFatalEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isFatalEnabled(Marker marker) {
		return l.isFatalEnabled(marker);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return l.isInfoEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isInfoEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isInfoEnabled(Marker marker) {
		return l.isInfoEnabled(marker);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return l.isTraceEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isTraceEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isTraceEnabled(Marker marker) {
		return l.isTraceEnabled(marker);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		return l.isWarnEnabled();
	}

	/**
	 * @param marker
	 * @return
	 * @see org.apache.logging.log4j.Logger#isWarnEnabled(org.apache.logging.log4j.Marker)
	 */
	public boolean isWarnEnabled(Marker marker) {
		return l.isWarnEnabled(marker);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void log(Level level, Marker marker, Message msg) {
		l.log(level, marker, msg);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, Message msg, Throwable t) {
		l.log(level, marker, msg, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void log(Level level, Marker marker, MessageSupplier msgSupplier) {
		l.log(level, marker, msgSupplier);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.log(level, marker, msgSupplier, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.CharSequence)
	 */
	public void log(Level level, Marker marker, CharSequence message) {
		l.log(level, marker, message);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, CharSequence message, Throwable t) {
		l.log(level, marker, message, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.Object)
	 */
	public void log(Level level, Marker marker, Object message) {
		l.log(level, marker, message);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.Object, java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, Object message, Throwable t) {
		l.log(level, marker, message, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String)
	 */
	public void log(Level level, Marker marker, String message) {
		l.log(level, marker, message);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object[])
	 */
	public void log(Level level, Marker marker, String message, Object... params) {
		l.log(level, marker, message, params);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void log(Level level, Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.log(level, marker, message, paramSuppliers);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, String message, Throwable t) {
		l.log(level, marker, message, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, org.apache.logging.log4j.util.Supplier)
	 */
	public void log(Level level, Marker marker, Supplier<?> msgSupplier) {
		l.log(level, marker, msgSupplier);
	}

	/**
	 * @param level
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void log(Level level, Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.log(level, marker, msgSupplier, t);
	}

	/**
	 * @param level
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void log(Level level, Message msg) {
		l.log(level, msg);
	}

	/**
	 * @param level
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void log(Level level, Message msg, Throwable t) {
		l.log(level, msg, t);
	}

	/**
	 * @param level
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void log(Level level, MessageSupplier msgSupplier) {
		l.log(level, msgSupplier);
	}

	/**
	 * @param level
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void log(Level level, MessageSupplier msgSupplier, Throwable t) {
		l.log(level, msgSupplier, t);
	}

	/**
	 * @param level
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.CharSequence)
	 */
	public void log(Level level, CharSequence message) {
		l.log(level, message);
	}

	/**
	 * @param level
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void log(Level level, CharSequence message, Throwable t) {
		l.log(level, message, t);
	}

	/**
	 * @param level
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.Object)
	 */
	public void log(Level level, Object message) {
		l.log(level, message);
	}

	/**
	 * @param level
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void log(Level level, Object message, Throwable t) {
		l.log(level, message, t);
	}

	/**
	 * @param level
	 * @param message
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String)
	 */
	public void log(Level level, String message) {
		l.log(level, message);
	}

	/**
	 * @param level
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void log(Level level, String message, Object... params) {
		l.log(level, message, params);
	}

	/**
	 * @param level
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void log(Level level, String message, Supplier<?>... paramSuppliers) {
		l.log(level, message, paramSuppliers);
	}

	/**
	 * @param level
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void log(Level level, String message, Throwable t) {
		l.log(level, message, t);
	}

	/**
	 * @param level
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void log(Level level, Supplier<?> msgSupplier) {
		l.log(level, msgSupplier);
	}

	/**
	 * @param level
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void log(Level level, Supplier<?> msgSupplier, Throwable t) {
		l.log(level, msgSupplier, t);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0) {
		l.log(level, marker, message, p0);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1) {
		l.log(level, marker, message, p0, p1);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
		l.log(level, marker, message, p0, p1, p2);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.log(level, marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.log(level, marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4,
			Object p5) {
		l.log(level, marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4,
			Object p5, Object p6) {
		l.log(level, marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4,
			Object p5, Object p6, Object p7) {
		l.log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4,
			Object p5, Object p6, Object p7, Object p8) {
		l.log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param level
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4,
			Object p5, Object p6, Object p7, Object p8, Object p9) {
		l.log(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0) {
		l.log(level, message, p0);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1) {
		l.log(level, message, p0, p1);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2) {
		l.log(level, message, p0, p1, p2);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3) {
		l.log(level, message, p0, p1, p2, p3);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.log(level, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.log(level, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.log(level, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.log(level, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.log(level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param level
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#log(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.log(level, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param level
	 * @param marker
	 * @param format
	 * @param params
	 * @see org.apache.logging.log4j.Logger#printf(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String, java.lang.Object[])
	 */
	public void printf(Level level, Marker marker, String format, Object... params) {
		l.printf(level, marker, format, params);
	}

	/**
	 * @param level
	 * @param format
	 * @param params
	 * @see org.apache.logging.log4j.Logger#printf(org.apache.logging.log4j.Level,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void printf(Level level, String format, Object... params) {
		l.printf(level, format, params);
	}

	/**
	 * @param <T>
	 * @param level
	 * @param t
	 * @return
	 * @see org.apache.logging.log4j.Logger#throwing(org.apache.logging.log4j.Level,
	 *      java.lang.Throwable)
	 */
	public <T extends Throwable> T throwing(Level level, T t) {
		return l.throwing(level, t);
	}

	/**
	 * @param <T>
	 * @param t
	 * @return
	 * @see org.apache.logging.log4j.Logger#throwing(java.lang.Throwable)
	 */
	public <T extends Throwable> T throwing(T t) {
		return l.throwing(t);
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void trace(Marker marker, Message msg) {
		l.trace(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void trace(Marker marker, Message msg, Throwable t) {
		l.trace(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void trace(Marker marker, MessageSupplier msgSupplier) {
		l.trace(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void trace(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.trace(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void trace(Marker marker, CharSequence message) {
		l.trace(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void trace(Marker marker, CharSequence message, Throwable t) {
		l.trace(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void trace(Marker marker, Object message) {
		l.trace(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Marker marker, Object message, Throwable t) {
		l.trace(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void trace(Marker marker, String message) {
		l.trace(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void trace(Marker marker, String message, Object... params) {
		l.trace(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void trace(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.trace(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void trace(Marker marker, String message, Throwable t) {
		l.trace(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void trace(Marker marker, Supplier<?> msgSupplier) {
		l.trace(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void trace(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.trace(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.message.Message)
	 */
	public void trace(Message msg) {
		l.trace(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void trace(Message msg, Throwable t) {
		l.trace(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void trace(MessageSupplier msgSupplier) {
		l.trace(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void trace(MessageSupplier msgSupplier, Throwable t) {
		l.trace(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.CharSequence)
	 */
	public void trace(CharSequence message) {
		l.trace(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void trace(CharSequence message, Throwable t) {
		l.trace(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.Object)
	 */
	public void trace(Object message) {
		l.trace(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void trace(Object message, Throwable t) {
		l.trace(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String)
	 */
	public void trace(String message) {
		l.trace(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object[])
	 */
	public void trace(String message, Object... params) {
		l.trace(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void trace(String message, Supplier<?>... paramSuppliers) {
		l.trace(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void trace(String message, Throwable t) {
		l.trace(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.util.Supplier)
	 */
	public void trace(Supplier<?> msgSupplier) {
		l.trace(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void trace(Supplier<?> msgSupplier, Throwable t) {
		l.trace(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0) {
		l.trace(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1) {
		l.trace(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.trace(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.trace(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.trace(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.trace(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.trace(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.trace(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.trace(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#trace(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.trace(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object)
	 */
	public void trace(String message, Object p0) {
		l.trace(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1) {
		l.trace(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2) {
		l.trace(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3) {
		l.trace(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.trace(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.trace(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.trace(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.trace(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#trace(java.lang.String,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceEntry()
	 */
	public EntryMessage traceEntry() {
		return l.traceEntry();
	}

	/**
	 * @param format
	 * @param params
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceEntry(java.lang.String,
	 *      java.lang.Object[])
	 */
	public EntryMessage traceEntry(String format, Object... params) {
		return l.traceEntry(format, params);
	}

	/**
	 * @param paramSuppliers
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceEntry(org.apache.logging.log4j.util.Supplier[])
	 */
	public EntryMessage traceEntry(Supplier<?>... paramSuppliers) {
		return l.traceEntry(paramSuppliers);
	}

	/**
	 * @param format
	 * @param paramSuppliers
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceEntry(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public EntryMessage traceEntry(String format, Supplier<?>... paramSuppliers) {
		return l.traceEntry(format, paramSuppliers);
	}

	/**
	 * @param message
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceEntry(org.apache.logging.log4j.message.Message)
	 */
	public EntryMessage traceEntry(Message message) {
		return l.traceEntry(message);
	}

	/**
	 * 
	 * @see org.apache.logging.log4j.Logger#traceExit()
	 */
	public void traceExit() {
		l.traceExit();
	}

	/**
	 * @param <R>
	 * @param result
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceExit(java.lang.Object)
	 */
	public <R> R traceExit(R result) {
		return l.traceExit(result);
	}

	/**
	 * @param <R>
	 * @param format
	 * @param result
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceExit(java.lang.String,
	 *      java.lang.Object)
	 */
	public <R> R traceExit(String format, R result) {
		return l.traceExit(format, result);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#traceExit(org.apache.logging.log4j.message.EntryMessage)
	 */
	public void traceExit(EntryMessage message) {
		l.traceExit(message);
	}

	/**
	 * @param <R>
	 * @param message
	 * @param result
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceExit(org.apache.logging.log4j.message.EntryMessage,
	 *      java.lang.Object)
	 */
	public <R> R traceExit(EntryMessage message, R result) {
		return l.traceExit(message, result);
	}

	/**
	 * @param <R>
	 * @param message
	 * @param result
	 * @return
	 * @see org.apache.logging.log4j.Logger#traceExit(org.apache.logging.log4j.message.Message,
	 *      java.lang.Object)
	 */
	public <R> R traceExit(Message message, R result) {
		return l.traceExit(message, result);
	}

	/**
	 * @param marker
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message)
	 */
	public void warn(Marker marker, Message msg) {
		l.warn(marker, msg);
	}

	/**
	 * @param marker
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.message.Message, java.lang.Throwable)
	 */
	public void warn(Marker marker, Message msg, Throwable t) {
		l.warn(marker, msg, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void warn(Marker marker, MessageSupplier msgSupplier) {
		l.warn(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.MessageSupplier, java.lang.Throwable)
	 */
	public void warn(Marker marker, MessageSupplier msgSupplier, Throwable t) {
		l.warn(marker, msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence)
	 */
	public void warn(Marker marker, CharSequence message) {
		l.warn(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.CharSequence, java.lang.Throwable)
	 */
	public void warn(Marker marker, CharSequence message, Throwable t) {
		l.warn(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.Object)
	 */
	public void warn(Marker marker, Object message) {
		l.warn(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Marker marker, Object message, Throwable t) {
		l.warn(marker, message, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String)
	 */
	public void warn(Marker marker, String message) {
		l.warn(marker, message);
	}

	/**
	 * @param marker
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object[])
	 */
	public void warn(Marker marker, String message, Object... params) {
		l.warn(marker, message, params);
	}

	/**
	 * @param marker
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, org.apache.logging.log4j.util.Supplier[])
	 */
	public void warn(Marker marker, String message, Supplier<?>... paramSuppliers) {
		l.warn(marker, message, paramSuppliers);
	}

	/**
	 * @param marker
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Throwable)
	 */
	public void warn(Marker marker, String message, Throwable t) {
		l.warn(marker, message, t);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier)
	 */
	public void warn(Marker marker, Supplier<?> msgSupplier) {
		l.warn(marker, msgSupplier);
	}

	/**
	 * @param marker
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      org.apache.logging.log4j.util.Supplier, java.lang.Throwable)
	 */
	public void warn(Marker marker, Supplier<?> msgSupplier, Throwable t) {
		l.warn(marker, msgSupplier, t);
	}

	/**
	 * @param msg
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.message.Message)
	 */
	public void warn(Message msg) {
		l.warn(msg);
	}

	/**
	 * @param msg
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void warn(Message msg, Throwable t) {
		l.warn(msg, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.util.MessageSupplier)
	 */
	public void warn(MessageSupplier msgSupplier) {
		l.warn(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.util.MessageSupplier,
	 *      java.lang.Throwable)
	 */
	public void warn(MessageSupplier msgSupplier, Throwable t) {
		l.warn(msgSupplier, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.CharSequence)
	 */
	public void warn(CharSequence message) {
		l.warn(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.CharSequence,
	 *      java.lang.Throwable)
	 */
	public void warn(CharSequence message, Throwable t) {
		l.warn(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		l.warn(message);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void warn(Object message, Throwable t) {
		l.warn(message, t);
	}

	/**
	 * @param message
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String)
	 */
	public void warn(String message) {
		l.warn(message);
	}

	/**
	 * @param message
	 * @param params
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String,
	 *      java.lang.Object[])
	 */
	public void warn(String message, Object... params) {
		l.warn(message, params);
	}

	/**
	 * @param message
	 * @param paramSuppliers
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String,
	 *      org.apache.logging.log4j.util.Supplier[])
	 */
	public void warn(String message, Supplier<?>... paramSuppliers) {
		l.warn(message, paramSuppliers);
	}

	/**
	 * @param message
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public void warn(String message, Throwable t) {
		l.warn(message, t);
	}

	/**
	 * @param msgSupplier
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.util.Supplier)
	 */
	public void warn(Supplier<?> msgSupplier) {
		l.warn(msgSupplier);
	}

	/**
	 * @param msgSupplier
	 * @param t
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.util.Supplier,
	 *      java.lang.Throwable)
	 */
	public void warn(Supplier<?> msgSupplier, Throwable t) {
		l.warn(msgSupplier, t);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0) {
		l.warn(marker, message, p0);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1) {
		l.warn(marker, message, p0, p1);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2) {
		l.warn(marker, message, p0, p1, p2);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
		l.warn(marker, message, p0, p1, p2, p3);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.warn(marker, message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.warn(marker, message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6) {
		l.warn(marker, message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7) {
		l.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8) {
		l.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param marker
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#warn(org.apache.logging.log4j.Marker,
	 *      java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5,
			Object p6, Object p7, Object p8, Object p9) {
		l.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param message
	 * @param p0
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object)
	 */
	public void warn(String message, Object p0) {
		l.warn(message, p0);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1) {
		l.warn(message, p0, p1);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2) {
		l.warn(message, p0, p1, p2);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3) {
		l.warn(message, p0, p1, p2, p3);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
		l.warn(message, p0, p1, p2, p3, p4);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
		l.warn(message, p0, p1, p2, p3, p4, p5);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
		l.warn(message, p0, p1, p2, p3, p4, p5, p6);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7) {
		l.warn(message, p0, p1, p2, p3, p4, p5, p6, p7);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8) {
		l.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
	}

	/**
	 * @param message
	 * @param p0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param p5
	 * @param p6
	 * @param p7
	 * @param p8
	 * @param p9
	 * @see org.apache.logging.log4j.Logger#warn(java.lang.String, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
			Object p7, Object p8, Object p9) {
		l.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}

	/**
	 * @param level
	 * @param marker
	 * @param fqcn
	 * @param location
	 * @param message
	 * @param throwable
	 * @see org.apache.logging.log4j.Logger#logMessage(org.apache.logging.log4j.Level,
	 *      org.apache.logging.log4j.Marker, java.lang.String,
	 *      java.lang.StackTraceElement, org.apache.logging.log4j.message.Message,
	 *      java.lang.Throwable)
	 */
	public void logMessage(Level level, Marker marker, String fqcn, StackTraceElement location, Message message,
			Throwable throwable) {
		l.logMessage(level, marker, fqcn, location, message, throwable);
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atTrace()
	 */
	public LogBuilder atTrace() {
		return l.atTrace();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atDebug()
	 */
	public LogBuilder atDebug() {
		return l.atDebug();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atInfo()
	 */
	public LogBuilder atInfo() {
		return l.atInfo();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atWarn()
	 */
	public LogBuilder atWarn() {
		return l.atWarn();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atError()
	 */
	public LogBuilder atError() {
		return l.atError();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#atFatal()
	 */
	public LogBuilder atFatal() {
		return l.atFatal();
	}

	/**
	 * @return
	 * @see org.apache.logging.log4j.Logger#always()
	 */
	public LogBuilder always() {
		return l.always();
	}

	/**
	 * @param level
	 * @return
	 * @see org.apache.logging.log4j.Logger#atLevel(org.apache.logging.log4j.Level)
	 */
	public LogBuilder atLevel(Level level) {
		return l.atLevel(level);
	}

}
