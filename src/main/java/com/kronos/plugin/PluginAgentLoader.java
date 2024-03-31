/**
 * 
 */
package com.kronos.plugin;

import java.io.File;
import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * 
 */
public class PluginAgentLoader {

	public static void attach(File plugin, String[] args) {
		try {
			VirtualMachine vm = VirtualMachine.attach(args[0]);
			vm.loadAgent(plugin.getAbsolutePath());
			vm.detach();
		} catch (AttachNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AgentLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AgentInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void attach(File plugin) {
		try {
			VirtualMachine vm = VirtualMachine.attach(Long.toString(ProcessHandle.current().pid()));
			vm.loadAgent(plugin.getAbsolutePath());
			vm.detach();
		} catch (AttachNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AgentLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AgentInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
